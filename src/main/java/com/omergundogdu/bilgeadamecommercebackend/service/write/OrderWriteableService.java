package com.omergundogdu.bilgeadamecommercebackend.service.write;

import com.omergundogdu.bilgeadamecommercebackend.dto.request.OrderRequest;

public interface OrderWriteableService {
    void createOrder(OrderRequest request);
}

