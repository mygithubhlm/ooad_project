package com.example.demo.exception;

/**
 * Created by think on 2017/6/20.
 */
public class TimeConvertException extends MyException{
    public TimeConvertException(){

    }
    public TimeConvertException(String service){
        this.service = service;
        this.message = "time format is not right";
    }
}
