package com.dsg.wemakeprice.service;

import com.dsg.wemakeprice.dto.ApiRequest;
import com.dsg.wemakeprice.dto.ApiResponse;

public interface ApiService {

    // 1. 신청서 제출
    ApiResponse submit(ApiRequest apiRequest);
}
