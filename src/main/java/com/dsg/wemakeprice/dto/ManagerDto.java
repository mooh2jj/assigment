package com.dsg.wemakeprice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@ToString
public class ManagerDto {

    private String managerName;
    private String managerEmail;

    @Builder
    public ManagerDto(String managerName, String managerEmail) {
        this.managerName = managerName;
        this.managerEmail = managerEmail;
    }
}
