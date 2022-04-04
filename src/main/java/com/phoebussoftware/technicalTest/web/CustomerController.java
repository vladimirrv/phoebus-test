package com.phoebussoftware.technicalTest.web;

import com.phoebussoftware.technicalTest.DTO.AccountDTO;
import com.phoebussoftware.technicalTest.DTO.CustomerDTO;
import com.phoebussoftware.technicalTest.model.AccountEntity;
import com.phoebussoftware.technicalTest.model.CustomerEntity;
import com.phoebussoftware.technicalTest.service.AccountService;
import com.phoebussoftware.technicalTest.service.CustomerService;
import org.apache.tomcat.util.json.ParseException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Integer customerId) {
        return ResponseEntity.ok(CustomerDTO.builder().build());
    }

    @GetMapping("/account/{customerId}")
    public ResponseEntity<List<AccountDTO>> getAccountsByCustomerId(
            @PathVariable Integer customerId) {

        List<AccountDTO> accountDTOS = List.of(AccountDTO.builder().build());
        return new ResponseEntity<>(accountDTOS, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CustomerDTO createCustomer(@Valid @RequestBody CustomerDTO customerDto) throws ParseException {
        CustomerEntity customer = convertToEntity(customerDto);
        CustomerEntity customerCreated = customerService.createCustomer(customer);
        return convertToDto(customerCreated);
    }

    private CustomerDTO convertToDto(CustomerEntity customerEntity) {
        CustomerDTO customerDTO = modelMapper.map(customerEntity, CustomerDTO.class);
        return customerDTO;
    }

    private CustomerEntity convertToEntity(CustomerDTO customerDto) throws ParseException {
        CustomerEntity customerEntity = modelMapper.map(customerDto, CustomerEntity.class);

        if (customerEntity.getCustomerId() != null) {
            CustomerEntity oldAccount = customerService.getCustomerById(customerDto.getCustomerId());
            customerEntity.setAccountEntities(oldAccount.getAccountEntities());
            customerEntity.setDataOfBirth(oldAccount.getDataOfBirth());
            customerEntity.setForeName(oldAccount.getForeName());
            customerEntity.setSurName(oldAccount.getSurName());
        }
        return customerEntity;
    }


}
