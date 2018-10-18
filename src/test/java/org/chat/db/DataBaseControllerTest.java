package org.chat.db;

import java.util.Map;

import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.github.srini156.aerospike.client.MockAerospikeClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class DataBaseControllerTest {
    private final Key key = new Key("test", "history", "example");
    private DataBaseController dataBaseController;
    private GlobalCache globalCache;
    private MockAerospikeClient client;
    private MockMvc mvc;

    @Before
    public void setUp() {
        globalCache = mock(GlobalCache.class);
        client = new MockAerospikeClient();
        dataBaseController = new DataBaseController(globalCache, client);

        mvc = MockMvcBuilders.standaloneSetup(dataBaseController).build();
    }

    @Test
    public void testGetHistoryRequest() throws Exception {
        mvc.perform(post("/getHistory")
                            .accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk());
    }

    @Test
    public void testClearHistoryRequest() throws Exception {
        mvc.perform(post("/clearHistory")
                            .accept(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk());
    }

    @Test
    public void testSaveHistory() {
        when(globalCache.get()).thenReturn("text");

        dataBaseController.saveHistoryToDataBase();

        verify(globalCache).clear();
        Map<String, Object> bins = client.get(null, key).bins;
        assertEquals(1, bins.size());
        assertTrue(bins.containsValue("text"));
    }

    @Test
    public void testSaveHistoryIfEmpty() {
        when(globalCache.get()).thenReturn("");

        dataBaseController.saveHistoryToDataBase();

        verify(globalCache, never()).clear();
        assertNull(client.get(null, key));
    }

    @Test
    public void testGetAndClearHistory() {
        String history = dataBaseController.getHistory();
        assertEquals("History is empty", history);

        Bin bin1 = new Bin("456", " world!");
        Bin bin2 = new Bin("123", "Hello");
        client.put(null, key, bin1, bin2);

        history = dataBaseController.getHistory();
        assertEquals("Hello world!", history);

        dataBaseController.clearHistory();

        history = dataBaseController.getHistory();
        assertEquals("History is empty", history);
    }
}