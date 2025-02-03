package com.qp.grocery_booking.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ResponseData {
    String message;
    HttpStatus status;
    Object data;
}
