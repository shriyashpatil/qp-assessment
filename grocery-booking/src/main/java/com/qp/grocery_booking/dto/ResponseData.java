package com.qp.grocery_booking.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseData {
    String message;
    String status;
    Object data;
}
