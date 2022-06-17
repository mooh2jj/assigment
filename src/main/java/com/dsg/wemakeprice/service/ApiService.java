package com.dsg.wemakeprice.service;

import com.dsg.wemakeprice.dto.ApiRequest;
import com.dsg.wemakeprice.dto.ApiResponse;

import java.util.List;

public interface ApiService {

    // 1. 신청서 제출
    ApiResponse submit(ApiRequest apiRequest);

    // 2. 신청서 조회
    List<ApiResponse> search(String companyRegistrationNumber);
}
