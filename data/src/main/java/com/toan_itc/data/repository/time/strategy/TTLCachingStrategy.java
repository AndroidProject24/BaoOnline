package com.toan_itc.data.repository.time.strategy;

import com.toan_itc.data.repository.time.CachingStrategy;

import java.util.concurrent.TimeUnit;
public class TTLCachingStrategy implements CachingStrategy<Long> {

    private final long ttlMillis;

    public TTLCachingStrategy(long ttl, TimeUnit timeUnit) {
        this.ttlMillis = timeUnit.toMillis(ttl);
    }


    @Override
    public boolean isValid(Long value) {
        return (value + ttlMillis) > System.currentTimeMillis();
    }
}
