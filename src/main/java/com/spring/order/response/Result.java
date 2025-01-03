package com.spring.order.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Result <T>{
    private int count;
    private T data;
}
