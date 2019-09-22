package cn.izhiman.seckill.controller;

import cn.izhiman.seckill.redis.RedisConfig;
import cn.izhiman.seckill.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhiman
 * @date 2019/9/22
 */
@Controller
public class HealthChecker {

    @GetMapping("/are_you_ok")
    @ResponseBody
    public Result<String> isHealth() {
        return Result.onSuccess("ok!");
    }
}
