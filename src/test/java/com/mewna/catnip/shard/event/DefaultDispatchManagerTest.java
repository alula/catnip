package com.mewna.catnip.shard.event;

import com.mewna.catnip.Catnip;
import com.mewna.catnip.shard.DiscordEvent;
import com.mewna.catnip.util.rx.RxHelpers;
import io.reactivex.Scheduler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DefaultDispatchManagerTest {
    private final Scheduler scheduler = RxHelpers.forkJoinScheduler();
    
    private DispatchManager dispatchManager() {
        final var mock = Mockito.mock(Catnip.class);
        when(mock.rxScheduler()).thenReturn(scheduler);
        
        final var dispatchManager = new DefaultDispatchManager();
        dispatchManager.catnip(mock);
        return dispatchManager;
    }
    
    @Test
    void simpleRun() throws InterruptedException {
        final var dispatchManager = dispatchManager();
        final var event = new Object();
        final boolean[] wasExecuted = { false };
    
        dispatchManager.createConsumer("simpleRun").handler(o -> wasExecuted[0] = true);
        dispatchManager.dispatchEvent("simpleRun", event);
        Thread.sleep(350L);
        assertTrue(wasExecuted[0], "Not dispatched");
    }
    
    
    @Test
    void testConsistency() throws InterruptedException {
        final var dispatchManager = dispatchManager();
        final var event = new Object();
        final var amount = 100000;
        final AtomicInteger counted = new AtomicInteger();
    
        dispatchManager.createConsumer("testConsistency").handler(o -> counted.incrementAndGet());
    
        for(int i = 0; i < amount; i++) {
            dispatchManager.dispatchEvent("testConsistency", event);
        }
        
        Thread.sleep(20L);
        assertEquals(counted.get(), amount, "Not all events were dispatched");
    }
}