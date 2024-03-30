package com.ecommerce.shop.dto;


import com.ecommerce.shop.entity.Address;
import com.ecommerce.shop.entity.Customer;
import com.ecommerce.shop.entity.Order;
import com.ecommerce.shop.entity.OrderItem;
import lombok.Data;
import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
