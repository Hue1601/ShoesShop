package org.example.shoesshopbe.common;

import lombok.Getter;

@Getter
public class Response {
    public static final int CODE_SUCCESS = 0;
    public static final int CODE_NOT_FOUND= 1;
    public static final int CODE_ALREADY_EXIST =2;
    public static final int CODE_BAD_REQUEST =3;

    protected Integer code;
    private Object data;
    public static Response build(){
        return new Response();
    };

    public Response ok(){
        this.code = CODE_SUCCESS;
        return this;
    }

    public Response code(Integer code){
        this.code = code;
        return this;
    }

    public Response data(Object data){
        this.data = data;
        return this;
    }
}
