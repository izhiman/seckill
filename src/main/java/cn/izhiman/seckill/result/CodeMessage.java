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
    /**
     * 通用异常
     */
    public final static CodeMessage SUCCESS = new CodeMessage(0, "SUCCESS");
    public final static CodeMessage FAIL = new CodeMessage(1, "UNKNOWN EXCEPTION");
    public final static CodeMessage INVALID_PARAMS = new CodeMessage(2, "invalid parameters: %s");
    /**
     * 登录模块异常 5001xx
     */
    public final static CodeMessage INVALID_PWD = new CodeMessage(500101, "invalid password !");
    public final static CodeMessage INVALID_MOBILE = new CodeMessage(500102, "invalid mobile !");
    public final static CodeMessage ILLEGAL_USER = new CodeMessage(500103, "illegal user !");
    public final static CodeMessage ACC_PWD_MISMATCH = new CodeMessage(500104, "account or password error !");

    /** 商品模块异常 5002xx */
    /** 订单模块异常 5003xx */
    /**
     * 秒杀模块异常 5004xx
     */

    /**
     * 改为构造者模式
     *
     * @param obj
     * @return
     */
    public CodeMessage formatMessage(Object... obj) {
        String msg = String.format(this.message, obj);
        return new CodeMessage(this.getCode(), msg);
    }
}
