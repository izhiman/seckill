package cn.izhiman.seckill.redis;

import cn.izhiman.seckill.redis.keys.RedisKeyBuilder;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author zhiman
 * @date 2019/9/22
 */
@Service
public class RedisService {

    @Autowired
    private RedisConfig redisConfig;
    @Lazy
    @Autowired
    private JedisPool jedisPool;

    public <T> T get(RedisKeyBuilder builder, String key, Class<T> clz) {
        return get(builder.build(key), clz);
    }

    public <T> T get(String key, Class<T> clz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return str2Obj(jedis.get(key), clz);
        } finally {
            releaseConnection(jedis);
        }
    }

    public <T> boolean set(RedisKeyBuilder builder, String key, T val) {
        return set(builder.build(key), val, builder.expireSec());
    }

    public <T> boolean set(String key, T val, int expireSec) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String value = obj2Str(val);
            if (StringUtils.isEmpty(value)) {
                return false;
            }
            if (expireSec > 0) {
                jedis.setex(key, expireSec, value);
            } else {
                jedis.set(key, value);
            }
            return true;
        } finally {
            releaseConnection(jedis);
        }
    }

    public boolean exist(RedisKeyBuilder builder, String key) {
        if (builder == null || StringUtils.isEmpty(key)) {
            return false;
        }
        return exist(builder.build(key));
    }

    public boolean exist(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } finally {
            releaseConnection(jedis);
        }
    }

    public <T> Long increase(RedisKeyBuilder builder, String key) {
        return increase(builder.build(key));
    }

    public <T> Long increase(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.incr(key);
        } finally {
            releaseConnection(jedis);
        }
    }

    public <T> Long decrease(RedisKeyBuilder builder, String key) {
        return decrease(builder.build(key));
    }

    public <T> Long decrease(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.decr(key);
        } finally {
            releaseConnection(jedis);
        }
    }

    @Bean
    public JedisPool jedisPoolFactory() {
        JedisPoolConfig jpConfig = new JedisPoolConfig();
        jpConfig.setMaxTotal(redisConfig.getPoolMaxActive());
        jpConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
        jpConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);
        return new JedisPool(
                jpConfig,
                redisConfig.getHost(),
                redisConfig.getPort(),
                redisConfig.getTimeout() * 1000,
                redisConfig.getPassword());
    }

    private <T> String obj2Str(T val) {
        if (val == null) {
            return null;
        }
        Class clz = val.getClass();
        if (clz == int.class
                || clz == long.class
                || clz == float.class
                || clz == double.class
                || clz == char.class
                || clz == boolean.class
                || val instanceof Integer
                || val instanceof Long
                || val instanceof Float
                || val instanceof Double
                || val instanceof Character
                || val instanceof Boolean
                ) {
            return String.valueOf(val);
        } else if (val instanceof String) {
            return (String) val;
        } else {
            return JSON.toJSONString(val);
        }
    }


    private <T> T str2Obj(String str, Class<T> clz) {
        if (StringUtils.isEmpty(str) || clz == null) {
            return null;
        }
        if (clz == int.class || clz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (clz == float.class || clz == Float.class) {
            return (T) Float.valueOf(str);
        } else if (clz == double.class || clz == Double.class) {
            return (T) Double.valueOf(str);
        } else if (clz == char.class || clz == Character.class) {
            return (T) Character.valueOf(str.charAt(0));
        } else if (clz == long.class || clz == Long.class) {
            return (T) Long.valueOf(str);
        } else if (clz == boolean.class || clz == Boolean.class) {
            return (T) Boolean.valueOf(str);
        } else if (clz == String.class) {
            return (T) str;
        } else {
            return JSON.toJavaObject(JSON.parseObject(str), clz);
        }
    }

    private void releaseConnection(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
