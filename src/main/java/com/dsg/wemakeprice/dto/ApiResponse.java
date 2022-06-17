package com.dsg.wemakeprice.dto;

import com.dsg.wemakeprice.entity.Company;
import com.dsg.wemakeprice.entity.Manager;
import com.dsg.wemakeprice.type.RegisterStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime approvedDatetime;

    @Builder
    @QueryProjection
    public ApiResponse(String companyRegistrationNumber, String companyName, String companyAddress, RegisterStatus registerStatus, String managerName, String managerEmail, LocalDateTime approvedDatetime) {
        this.companyRegistrationNumber = companyRegistrationNumber;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.registerStatus = registerStatus;
        this.managerName = managerName;
        this.managerEmail = managerEmail;
        this.approvedDatetime = approvedDatetime;
    }

    @Data
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ApproveDto {

        private String adminId;
        private String adminName;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime approvedDatetime;

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
