package cn.izhiman.seckill.controller;

import cn.izhiman.seckill.domain.User;
import cn.izhiman.seckill.redis.RedisService;
import cn.izhiman.seckill.redis.keys.UserRedisKey;
import cn.izhiman.seckill.result.Result;
import cn.izhiman.seckill.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhiman
 * @date 2019/9/22
 */
@Controller
@Slf4j
public class SampleController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;

    @GetMapping("thymeleaf/{id}")
    public String template(Model model, @PathVariable String id) {
        model.addAttribute("name", redisService.get(UserRedisKey.idInfix, id, String.class));
        return "hello";
    }

    @GetMapping("get/{id}")
    @ResponseBody
    public Result<User> getDataFromDB(@PathVariable Integer id) {
        userService.insert();
        return Result.onSuccess(userService.getById(id));
    }

    @GetMapping("set/{key}/{val}")
    @ResponseBody
    public Result<Boolean> set(@PathVariable("key") Integer id,
                               @PathVariable("val") String val) {
        System.out.println(id);
        User user = new User();
        user.setId(id);
        user.setName(val);
        return Result.onSuccess(redisService.set(UserRedisKey.idInfix, id.toString(), user));
    }
}
