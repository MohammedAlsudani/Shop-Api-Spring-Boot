package com.ecommerce.shop.dao;

import com.ecommerce.shop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByEmail(String email);
}
