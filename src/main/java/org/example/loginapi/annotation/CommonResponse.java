package org.example.loginapi.annotation;


import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.example.loginapi.constant.ResponseConstant;
import org.example.loginapi.constant.ResponseStatusCodes;
import org.example.loginapi.model.dto.ErrorResponseDTO;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses({
        @ApiResponse(responseCode = ResponseStatusCodes.BAD_REQUEST, content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
                , description = ResponseConstant.BAD_REQUEST),
        @ApiResponse(responseCode = ResponseStatusCodes.UNAUTHORIZED, content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
                , description = ResponseConstant.UNAUTHORIZED),
        @ApiResponse(responseCode = ResponseStatusCodes.FORBIDDEN, content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
                , description = ResponseConstant.FORBIDDEN),
        @ApiResponse(responseCode = ResponseStatusCodes.NOT_FOUND, content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
                , description = ResponseConstant.NOT_FOUND),
        @ApiResponse(responseCode = ResponseStatusCodes.INTERNAL_SERVER_ERROR, content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
                , description = ResponseConstant.INTERNAL_SERVER_ERROR),
})
public @interface CommonResponse {
}