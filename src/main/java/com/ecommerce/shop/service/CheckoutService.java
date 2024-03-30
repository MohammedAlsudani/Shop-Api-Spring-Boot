package com.ecommerce.shop.service;


import com.ecommerce.shop.dto.Purchase;
import com.ecommerce.shop.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
