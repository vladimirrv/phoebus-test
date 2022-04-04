package com.phoebussoftware.technicalTest.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO {
  Long accountId;
  CustomerDTO customerDTO;
  @NotNull(message = "Account number shouldn't be empty")
  Integer accountNumber;
}
