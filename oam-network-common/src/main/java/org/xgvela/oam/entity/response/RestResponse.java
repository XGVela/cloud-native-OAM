package org.xgvela.oam.entity.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestResponse<T> {

    private Integer status;
    private String message;
    private T data;

    public static RestResponse success(){
        return RestResponse.builder().status(ResultCode.SUCCESS.getCode()).message(ResultCode.SUCCESS.getMessage()).build();
    }

    public static RestResponse success(Object data){
        return RestResponse.builder().status(ResultCode.SUCCESS.getCode()).message(ResultCode.SUCCESS.getMessage()).data(data).build();
    }

    public static RestResponse fail(){
        RestResponse restResponse = RestResponse.builder().status(ResultCode.FAILED.getCode()).message(ResultCode.FAILED.getMessage()).build();
        return restResponse;
    }

    public static RestResponse customerResponse(Integer code, String message, Object data){
        RestResponse restResponse = RestResponse.builder().status(code).message(message).data(data).build();
        return restResponse;
    }

}
