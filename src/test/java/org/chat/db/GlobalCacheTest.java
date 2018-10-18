package org.chat.db;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GlobalCacheTest {
    @Test
    public void testGlobalCache() {
        GlobalCache cache = new GlobalCache();

        assertEquals("", cache.get());

        cache.update("Hello ");
        cache.update("world!");

        assertEquals("Hello world!", cache.get());

        cache.clear();

        assertEquals("", cache.get());
    }
}