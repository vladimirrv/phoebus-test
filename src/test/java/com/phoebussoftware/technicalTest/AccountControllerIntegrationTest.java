package com.phoebussoftware.technicalTest;

import com.phoebussoftware.technicalTest.model.AccountEntity;
import com.phoebussoftware.technicalTest.repository.AccountRepository;
import com.phoebussoftware.technicalTest.service.AccountService;
import com.phoebussoftware.technicalTest.service.CustomerService;
import com.phoebussoftware.technicalTest.web.AccountController;
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

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class AccountControllerIntegrationTest {

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private AccountService accountService;

    @Autowired
    AccountController accountController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenPostRequestToAccountAndValidAccount_thenCorrectResponse() throws Exception {
        MediaType jsonUtf8 = new MediaType(MediaType.APPLICATION_JSON);
        String account = "{\"accountNumber\": \"12345\"}";

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountNumber(12345);

        given(accountService.createAccount(accountEntity)).willReturn(accountEntity);

        mockMvc.perform(MockMvcRequestBuilders.post("/account")
                        .content(account)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountNumber").value("12345"))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(jsonUtf8));
    }
}
