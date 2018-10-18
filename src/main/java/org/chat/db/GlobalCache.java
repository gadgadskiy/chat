package org.chat.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GlobalCache {
    @Value("${messages.cache}")
    private StringBuilder cache = new StringBuilder();

    public void update(String message) {
        cache.append(message);
    }

    public String get() {
        return cache.toString();
    }

    public void clear() {
        cache = new StringBuilder();
    }
}