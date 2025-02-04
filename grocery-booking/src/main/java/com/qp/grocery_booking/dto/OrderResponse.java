package com.qp.grocery_booking.dto;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderResponse {

    private Long id;
    private List<Long> failedItems;

}
