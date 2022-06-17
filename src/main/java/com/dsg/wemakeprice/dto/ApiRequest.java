package com.dsg.wemakeprice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ApiRequest {

    @NotNull
    private String companyName;

    @NotNull
    private String companyRegistrationNumber;

    private String companyAddress;

    @NotNull
    private String managerName;

    @NotNull
    private String managerEmail;

}
