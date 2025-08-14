package org.example.shoesshopbe.common;

import lombok.Getter;

@Getter
public class DataTableResponse {
    public static final int CODE_SUCCESS = 0;
    public static final int CODE_NOT_FOUND = 400;
    protected Integer code;
    private Object items;
    private Long totalRows;

    public DataTableResponse ok(){
        this.code = CODE_SUCCESS;
        return this;
    }

    public DataTableResponse code(int code){
        this.code = code;
        return this;
    }

    public DataTableResponse items(Object items){
        this.items = items;
        return this;
    }

    public DataTableResponse totalRows(Long totalRows){
        this.totalRows = totalRows;
        return this;
    }

    public static DataTableResponse build(){
        return new DataTableResponse();
    }
}
