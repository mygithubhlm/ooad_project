package com.example.demo.exception;

/**
 * Created by think on 2017/6/20.
 */
public class NoSuchEntityException extends MyException {
    public NoSuchEntityException(){

    }
    public NoSuchEntityException(String serice, Class s){
        this.service = serice;
        this.message = "the "+s.getName()+" you use doesn't exist";
    }
}
