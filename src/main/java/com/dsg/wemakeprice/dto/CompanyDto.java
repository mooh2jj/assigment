package com.dsg.wemakeprice.dto;

import com.dsg.wemakeprice.type.RegisterStatus;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ToString
public class CompanyDto {

    private String companyRegistrationNumber;

    private String companyName;
    private String companyAddress;

    private RegisterStatus registerStatus;

    private LocalDateTime approvedDatetime;

    @Builder

    public CompanyDto(String companyRegistrationNumber, String companyName, String companyAddress, RegisterStatus registerStatus, LocalDateTime approvedDatetime) {
        this.companyRegistrationNumber = companyRegistrationNumber;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.registerStatus = registerStatus;
        this.approvedDatetime = approvedDatetime;
    }
}
