package com.uca.assignment.dbtwo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uca.assignment.dbtwo.entities.DBTwoCustomer;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
public interface DBTwoCustomerRepository extends JpaRepository<DBTwoCustomer, String> {

}
