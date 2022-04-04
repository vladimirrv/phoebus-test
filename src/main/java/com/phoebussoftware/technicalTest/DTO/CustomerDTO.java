package com.phoebussoftware.technicalTest.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {
  Long customerId;
  @NotEmpty(message = "Fore name shouldn't be empty")
  String foreName;
  @NotEmpty(message = "Sure name shouldn't be empty")
  String surName;
  @NotNull(message = "Date of Birth shouldn't be empty")
  @JsonFormat
         (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  Date dataOfBirth;
  @JsonProperty("accounts")
  List<AccountDTO> accountDTOS;
}
