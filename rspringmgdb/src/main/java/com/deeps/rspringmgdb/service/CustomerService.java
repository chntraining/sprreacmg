package com.deeps.rspringmgdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deeps.rspringmgdb.dto.CustomerDto;
import com.deeps.rspringmgdb.repository.CustomerRepository;
import com.deeps.rspringmgdb.utils.AppUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {
	@Autowired
    private CustomerRepository repository;


    public Flux<CustomerDto> getCustomers(){
        return repository.findAll().map(AppUtils::entityToDto);
    }

    public Mono<CustomerDto> getCustomer(String id){
        return repository.findById(id).map(AppUtils::entityToDto);
    }

    public Flux<CustomerDto> getCustomerInRange(double min,double max){
        return repository.findByAgeBetween(Range.closed(min,max));
    }

    public Mono<CustomerDto> saveCustomer(Mono<CustomerDto> CustomerDtoMono){
        System.out.println("service method called ...");
      return  CustomerDtoMono.map(AppUtils::dtoToEntity)
                .flatMap(repository::insert)
                .map(AppUtils::entityToDto);
    }

    public Mono<CustomerDto> updateCustomer(Mono<CustomerDto> CustomerDtoMono,String id){
       return repository.findById(id)
                .flatMap(p->CustomerDtoMono.map(AppUtils::dtoToEntity)
                //.doOnNext(e-> e.setId(id))
                .flatMap(repository::save)
                .map(AppUtils::entityToDto));

    }

    public Mono<Void> deleteCustomer(String id){
        return repository.deleteById(id);
    }
}
