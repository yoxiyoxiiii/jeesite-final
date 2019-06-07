package com.jeesite.modules.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadLocalContext {

    private final static ThreadLocal threadLocal = new ThreadLocal();
    static {
        Map<String,Object> map = new ConcurrentHashMap<>();
        threadLocal.set(map);
    }
    public static void set(String name,Object value) {
        Map<String, Object> map = (Map<String, Object>) threadLocal.get();
        map.put(name,value);
    }

    public static Object get(String name) {
        Map<String, Object> map = (Map<String, Object>) threadLocal.get();
        return map.get(name);
    }
}
