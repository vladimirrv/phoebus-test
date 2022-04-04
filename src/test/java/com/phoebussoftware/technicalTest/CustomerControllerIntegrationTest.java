package com.phoebussoftware.technicalTest;

import com.phoebussoftware.technicalTest.model.AccountEntity;
import com.phoebussoftware.technicalTest.model.CustomerEntity;
import com.phoebussoftware.technicalTest.repository.CustomerRepository;
import com.phoebussoftware.technicalTest.service.AccountService;
import com.phoebussoftware.technicalTest.service.CustomerService;
import com.phoebussoftware.technicalTest.web.CustomerController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class CustomerControllerIntegrationTest {

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private AccountService accountService;

    @Autowired
    CustomerController customerController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenPostRequestToCustomerAndValidCustomer_thenCorrectResponse() throws Exception {
        MediaType json = new MediaType(MediaType.APPLICATION_JSON);
        String customer = "{\"surName\": \"TestSurname\",\"foreName\": \"TestForeName\",\"dataOfBirth\": \"16-01-2013\"}";

        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
        formatter.setTimeZone(TimeZone.getTimeZone("PST"));

        String dateInString = "16-01-2013";
        Date date = formatter.parse(dateInString);

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setSurName("TestSurname");
        customerEntity.setForeName("TestForeName");
        customerEntity.setDataOfBirth(date);

        given(customerService.createCustomer(any(CustomerEntity.class))).willReturn(customerEntity);

        mockMvc.perform(MockMvcRequestBuilders.post("/customer")
                        .content(customer)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.surName").value("TestSurname"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.foreName").value("TestForeName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dataOfBirth").value("16-01-2013"))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(json))
                .andDo(print());
    }
}
