package com.phoebussoftware.technicalTest.service;

import com.phoebussoftware.technicalTest.model.CustomerEntity;
import org.springframework.validation.annotation.Validated;

public interface CustomerService {

    CustomerEntity createCustomer(CustomerEntity customer);

    CustomerEntity getCustomerById(Long id);
}
