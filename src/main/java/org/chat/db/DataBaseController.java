package org.chat.db;

import java.util.Map;
import java.util.stream.Collectors;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.IAerospikeClient;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@Profile("db")
@AllArgsConstructor
public class DataBaseController {
    private final GlobalCache globalCache;
    private final IAerospikeClient client;
    private final Key key = new Key("test", "history", "example");

    @Scheduled(fixedDelay = 60_000)
    public void saveHistoryToDataBase() {
        if (globalCache.get().isEmpty()) {
            return;
        }
        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        Bin bin = new Bin(timeStamp, globalCache.get());
        client.put(null, key, bin);
        globalCache.clear();
        log.info("History is saved to database");
    }

    @RequestMapping("/getHistory")
    @ResponseBody
    public String getHistory() {
        Record record = client.get(null, key);
        if (record == null || record.bins == null) {
            return "History is empty";
        }
        return record.bins.entrySet()
                          .stream()
                          .sorted(Map.Entry.comparingByKey())
                          .map(e -> e.getValue().toString())
                          .collect(Collectors.joining());
    }

    @RequestMapping("/clearHistory")
    @ResponseBody
    public String clearHistory() {
        client.delete(null, key);
        return "History is removed";
    }
}