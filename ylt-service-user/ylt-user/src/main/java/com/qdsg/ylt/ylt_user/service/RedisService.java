package com.qdsg.ylt.ylt_user.service;

public interface RedisService {
    Boolean set(final String key, Object value);
    Object get(final String key);
    void delCacheByKey(String key);
}
