package com.dsg.wemakeprice.component.mail;

import com.dsg.wemakeprice.dto.ApiResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class MailMessageGenerator {

    public String toMessage(ApiResponse apiResponse) {
        return String.format(
                "%s 님, 관리하신 회사 신청서가 승인됨을 알림 드립니다.\n", apiResponse.getManagerName())
                +
                String.format(
                        "승인시간: %s\n", formatterString(apiResponse.getApprovedDatetime())
                )
                +
                String.format(
                        "회사명: %s, 사업자등록번호: %s, 회사주소: %s\n",
                        apiResponse.getCompanyName(),
                        apiResponse.getCompanyRegistrationNumber(),
                        apiResponse.getCompanyAddress()
                );
    }

    private String formatterString(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return time.format(formatter);
    }
}
