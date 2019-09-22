package cn.izhiman.seckill.redis.utils;

/**
 * @author zhiman
 * @date 2019/9/22
 */
public class UserRedisKey extends AbstractRedisKeyBuilder {
    private UserRedisKey(String prefix) {
        super(prefix);
    }

    public static UserRedisKey idInfix = new UserRedisKey("id");
    public static UserRedisKey nameInfix = new UserRedisKey("name");
}
