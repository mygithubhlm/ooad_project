package com.example.demo.exception;

/**
 * Created by think on 2017/6/20.
 */
public class MyException extends RuntimeException {
    protected String service;
    protected String message;
    public MyException(){

    }
    public MyException(String service,String message){
        this.service = service;
        this.message = message;
    }

    @Override
    public void printStackTrace() {
        System.out.println("Wrong: "+this.message+" in "+this.service);
    }
}
