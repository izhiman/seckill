package cn.izhiman.seckill.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * 信息码映射
 *
 * @author zhiman
 * @date 2019/9/22
 */
@AllArgsConstructor
public class CodeMessage {
    @Getter
    private int code;
    @Getter
    private String message;
    /** 通用异常 */
    public final static CodeMessage SUCCESS = new CodeMessage(0, "SUCCESS");
    public final static CodeMessage FAIL = new CodeMessage(1, "UNKNOWN EXCEPTION");
    /** 登录模块异常 5001xx */
    /** 商品模块异常 5002xx */
    /** 订单模块异常 5003xx */
    /** 秒杀模块异常 5004xx */
}
