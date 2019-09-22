package cn.izhiman.seckill.redis.utils;

/**
 * @author zhiman
 * @date 2019/9/22
 */
public interface RedisKeyBuilder {

    int expireSec();

    String getPrefix();

    String build(String str);
}
