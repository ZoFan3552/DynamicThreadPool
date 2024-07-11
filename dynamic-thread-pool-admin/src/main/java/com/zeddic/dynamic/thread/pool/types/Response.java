package com.zeddic.dynamic.thread.pool.types;

import lombok.*;
import sun.dc.pr.PRError;

import java.io.Serializable;

/**
 * @author: zeddic
 * @description: 传回前端的响应类
 * @date: 2024/7/11 下午4:15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> implements Serializable {
    private static final long serialVersionUID = Long.MIN_VALUE;
    private String code;
    private String info;
    private T data;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public enum Code{
        SUCCESS("0000" ,"调用成功"),
        UN_ERROR("0001" , "调用失败"),
        ILLEGAL_PARAMETER("0002" , "非法参数");
        private String code;
        private String info;
    }
}
