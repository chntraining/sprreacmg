package com.deeps.rspringmgdb.utils;

import org.springframework.beans.BeanUtils;

import com.deeps.rspringmgdb.dto.CustomerDto;
import com.deeps.rspringmgdb.entity.Customer;

public class AppUtils {
	public static CustomerDto entityToDto(Customer Customer) {
        CustomerDto CustomerDto = new CustomerDto();
        BeanUtils.copyProperties(Customer, CustomerDto);
        return CustomerDto;
    }

    public static Customer dtoToEntity(CustomerDto CustomerDto) {
        Customer Customer = new Customer();
        BeanUtils.copyProperties(CustomerDto, Customer);
        return Customer;
    }
}
