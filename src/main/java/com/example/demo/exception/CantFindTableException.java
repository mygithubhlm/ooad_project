package com.example.demo.exception;

/**
 * Created by think on 2017/6/20.
 */
public class CantFindTableException extends MyException {
    public CantFindTableException(){}
    public CantFindTableException(String service,Class s){
        this.service = service;
        this.message = "can't find such a "+s.getName()+" in your select";
    }
}
