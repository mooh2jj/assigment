package com.dsg.wemakeprice.entity;

import com.dsg.wemakeprice.dto.ApiRequest;
import com.dsg.wemakeprice.type.RegisterStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company {

    @Id
    private String companyRegistrationNumber;

    private String companyName;
    private String companyAddress;

    @Enumerated(EnumType.STRING)
    private RegisterStatus registerStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Manager manager;

    private LocalDateTime approvedDatetime;

    @Builder
    public Company(String companyRegistrationNumber, String companyName, String companyAddress, Manager manager, RegisterStatus registerStatus, LocalDateTime approvedDatetime) {
        this.companyRegistrationNumber = companyRegistrationNumber;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.manager = manager;
        this.registerStatus = registerStatus;
        this.approvedDatetime = approvedDatetime;
    }

    // admin 승인 메서드
    public void approve() {
        this.registerStatus = RegisterStatus.APPROVAL;
        this.approvedDatetime = LocalDateTime.now();
    }

    public static Company of(ApiRequest apiRequest, Manager manager) {
        return Company.builder()
                .companyRegistrationNumber(apiRequest.getCompanyRegistrationNumber())
                .companyName(apiRequest.getCompanyName())
                .companyAddress(apiRequest.getCompanyAddress())
                .registerStatus(RegisterStatus.REQUEST)
                .manager(manager)
                .approvedDatetime(LocalDateTime.now())
                .build();
    }
}
