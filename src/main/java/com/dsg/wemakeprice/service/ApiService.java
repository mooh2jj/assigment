package com.dsg.wemakeprice.service;

import com.dsg.wemakeprice.dto.ApiRequest;
import com.dsg.wemakeprice.dto.ApiResponse;

import java.util.List;

public interface ApiService {

    // 1. 신청서 제출
    ApiResponse submit(ApiRequest apiRequest);

    // 2. 신청서 조회
    ApiResponse search(String companyRegistrationNumber);

    // 3. 제출된 신청서 목록
    List<ApiResponse> list(String companyRegistrationNumber, String companyName);

    // 4. 제출된 신청서 승인
    ApiResponse.ApproveDto approve(String companyRegistrationNumber);
}
