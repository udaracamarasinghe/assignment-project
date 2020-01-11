package com.uca.assignment.dbone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uca.assignment.dbone.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}
