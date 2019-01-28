package com.spring.boot.service;

import java.util.List;

import com.spring.boot.model.Customer;

public interface CustomersInterface {

	public List<Customer> searchCity(Integer pageNumber, Integer pageSize, String searchContent);

}
