package cn.izhiman.seckill.controller;

import cn.izhiman.seckill.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhiman
 * @date 2019/9/22
 */
@RestController
public class HealthChecker {

    @GetMapping("/are_you_ok")
    public Result<String> isHealth() {
        return Result.onSuccess("ok!");
    }
}
