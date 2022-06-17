package com.dsg.wemakeprice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class NotificationAlarmDto {
    private String email;
    private String name;
    private ApiResponse apiResponse;

    @Builder
    public NotificationAlarmDto(String email, String name, ApiResponse apiResponse) {
        this.email = email;
        this.name = name;
        this.apiResponse = apiResponse;
    }
}
