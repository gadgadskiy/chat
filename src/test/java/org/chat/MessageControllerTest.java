package org.chat;

import org.chat.db.GlobalCache;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MessageControllerTest {
    @Test
    public void testSendMessage() {
        GlobalCache globalCache = mock(GlobalCache.class);
        MessageData messageData = mock(MessageData.class);
        Mockito.when(messageData.getText()).thenReturn("Hello");
        MessageController messageController = new MessageController(globalCache);

        String result = messageController.sendMessage(messageData);

        verify(globalCache).update(eq("Hello\n"));
        assertEquals("Hello", result);
    }
}