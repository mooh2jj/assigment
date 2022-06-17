package com.dsg.wemakeprice.dto;

import com.dsg.wemakeprice.entity.Company;
import com.dsg.wemakeprice.entity.Manager;
import com.dsg.wemakeprice.type.RegisterStatus;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ToString
public class ApiResponse {

    private String companyRegistrationNumber;

    private String companyName;
    private String companyAddress;

    private RegisterStatus registerStatus;

    private String managerName;
    private String managerEmail;

    private LocalDateTime approvedDatetime;

    @Builder
    public ApiResponse(String companyRegistrationNumber, String companyName, String companyAddress, RegisterStatus registerStatus, String managerName, String managerEmail, LocalDateTime approvedDatetime) {
        this.companyRegistrationNumber = companyRegistrationNumber;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.registerStatus = registerStatus;
        this.managerName = managerName;
        this.managerEmail = managerEmail;
        this.approvedDatetime = approvedDatetime;
    }

    public static ApiResponse mapToResponse(Manager savedManager, Company savedCompany) {
        return ApiResponse.builder()
                .companyName(savedCompany.getCompanyName())
                .companyRegistrationNumber(savedCompany.getCompanyRegistrationNumber())
                .companyAddress(savedCompany.getCompanyAddress())
                .managerName(savedManager.getManagerName())
                .managerEmail(savedManager.getManagerEmail())
                .registerStatus(savedCompany.getRegisterStatus())
                .approvedDatetime(savedCompany.getApprovedDatetime())
                .build();
    }
}
