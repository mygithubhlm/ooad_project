package com.example.demo.exception;

/**
 * Created by think on 2017/6/20.
 */
public class TimeException extends MyException{
    public TimeException(){

    }
    public TimeException(String service){
        this.service = service;
        this.message = "time is not right";
    }
}
