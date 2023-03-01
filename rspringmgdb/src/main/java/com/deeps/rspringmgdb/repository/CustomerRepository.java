package com.deeps.rspringmgdb.repository;

import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.deeps.rspringmgdb.dto.CustomerDto;
import com.deeps.rspringmgdb.entity.Customer;

import reactor.core.publisher.Flux;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer,String> {
    Flux<CustomerDto> findByAgeBetween(Range<Double> ageRange);
}
