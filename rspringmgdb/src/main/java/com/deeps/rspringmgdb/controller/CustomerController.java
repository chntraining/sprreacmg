package com.deeps.rspringmgdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deeps.rspringmgdb.dto.CustomerDto;
import com.deeps.rspringmgdb.service.CustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	 @Autowired
	    private CustomerService service;

	    @GetMapping
	    public Flux<CustomerDto> getCustomers(){
	        return service.getCustomers();
	    }

	    @GetMapping("/{id}")
	    public Mono<CustomerDto> getCustomer(@PathVariable String id){
	        return service.getCustomer(id);
	    }

	    @GetMapping("/customer-range")
	    public Flux<CustomerDto> getCustomerBetweenRange(@RequestParam("min") double min, @RequestParam("max")double max){
	        return service.getCustomerInRange(min,max);
	    }

	    @PostMapping("/saveCustomer")
	    public Mono<CustomerDto> saveCustomer(@RequestBody Mono<CustomerDto> CustomerDtoMono){
	        System.out.println("controller method called ...");
	        return service.saveCustomer(CustomerDtoMono);
	    }

	    @PutMapping("/update/{id}")
	    public Mono<CustomerDto> updateCustomer(@RequestBody Mono<CustomerDto> CustomerDtoMono,@PathVariable String id){
	        return service.updateCustomer(CustomerDtoMono,id);
	    }

	    @DeleteMapping("/delete/{id}")
	    public Mono<Void> deleteCustomer(@PathVariable String id){
	        return service.deleteCustomer(id);
	    }
}
