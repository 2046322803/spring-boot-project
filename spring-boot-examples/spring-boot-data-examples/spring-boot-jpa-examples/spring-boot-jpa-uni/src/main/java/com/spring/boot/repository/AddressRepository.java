package com.spring.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}