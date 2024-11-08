package employment.infomation.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author guoxinghong
 * @description：
 * @date 2024/8/12
 */
public class GuavaCache {

    private static final Cache<String, Integer> initCallCache = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.HOURS)
            .maximumSize(10000).build();


    public static void putToken(String key, Integer value) {
        initCallCache.put(key, value);
    }

    public static Integer getUserId(String key) {
        return initCallCache.getIfPresent(key);
    }

    public static void remove(String key) {
        initCallCache.invalidate(key);
    }



}
