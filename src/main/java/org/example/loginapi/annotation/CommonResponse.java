package org.example.loginapi.annotation;


import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.example.loginapi.constant.ResponeConstant;
import org.example.loginapi.entity.response.ErrorResponseDTO;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses({
//        @ApiResponse(code = 200, response = )
        @ApiResponse(code = 400, response = ErrorResponseDTO.class, message = ResponeConstant.BAD_REQUEST),
        @ApiResponse(code = 401, response = ErrorResponseDTO.class, message = ResponeConstant.UNAUTHORIZED),
        @ApiResponse(code = 403, response = ErrorResponseDTO.class, message = ResponeConstant.FORBIDDEN),
        @ApiResponse(code = 404, response = ErrorResponseDTO.class, message = ResponeConstant.NOT_FOUND),
        @ApiResponse(code = 408, response = ErrorResponseDTO.class, message = ResponeConstant.REQUEST_TIME_OUT),
        @ApiResponse(code = 500, response = ErrorResponseDTO.class, message = ResponeConstant.INTERNAL_SERVER_ERROR),
        @ApiResponse(code = 501, response = ErrorResponseDTO.class, message = ResponeConstant.NOT_IMPLEMENTED),
        @ApiResponse(code = 502, response = ErrorResponseDTO.class, message = ResponeConstant.BAD_GATEWAY),
        @ApiResponse(code = 503, response = ErrorResponseDTO.class, message = ResponeConstant.SERVICE_UNAVAILABLE),
})
public @interface CommonResponse {
}