package com.zyj.spring.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zyj.spring.boot.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}