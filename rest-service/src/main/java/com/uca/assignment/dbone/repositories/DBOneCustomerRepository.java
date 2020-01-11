package com.uca.assignment.dbone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uca.assignment.dbone.entities.DBOneCustomer;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
public interface DBOneCustomerRepository extends JpaRepository<DBOneCustomer, String> {

}
