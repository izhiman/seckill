package cn.izhiman.seckill.result;

import lombok.Data;
import lombok.Getter;

/**
 * @author zhiman
 * @date 2019/9/22
 */
@Getter
public class Result<T> {
    private int code;
    private String message;
    private T data;

    private Result(T data) {
        this.data = data;
        this.code = CodeMessage.SUCCESS.getCode();
        this.message = CodeMessage.SUCCESS.getMessage();
    }

    private Result(CodeMessage cm) {
        if (cm != null) {
            this.code = cm.getCode();
            this.message = cm.getMessage();
        }
    }

    /**
     * 成功时调用
     *
     * @param data 用户数据
     * @param <T>  数据类型
     * @return result对象
     */
    public static <T> Result<T> onSuccess(T data) {
        return new Result<T>(data);
    }

    /**
     * 成功时调用
     *
     * @param cm  用户数据
     * @param <T> 数据类型
     * @return result对象
     */
    public static <T> Result<T> onFail(CodeMessage cm) {
        return new Result<T>(cm);
    }
}
