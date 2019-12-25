package com.yzl.springbootrestfulcrud.exception;

/**
 * @Author yzl
 * @Create 2019/12/20
 */
public class IndexOverException extends RuntimeException {

    public IndexOverException() {
        super("自定义异常");
    }
}
