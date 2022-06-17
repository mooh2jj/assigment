package com.dsg.wemakeprice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiRequest {

    private String companyName;
    private String companyRegistrationNumber;
    private String companyAddress;
    private String managerName;
    private String managerEmail;

}
