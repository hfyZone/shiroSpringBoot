package zone.hfy.shirospringboot.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * @author YoungNet
 * @date 2022/1/11 14:15
 */
public class RedisCacheManager implements CacheManager {

    /**
     参数s是 认证或授权参数名称
     * */
    @Override
        public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
        System.out.println(cacheName);
        return new RedisCache<K,V>(cacheName);
    }
}
