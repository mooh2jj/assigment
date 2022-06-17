package com.dsg.wemakeprice.service;

import com.dsg.wemakeprice.dto.ApiRequest;
import com.dsg.wemakeprice.dto.ApiResponse;
import com.dsg.wemakeprice.entity.Company;
import com.dsg.wemakeprice.entity.Manager;
import com.dsg.wemakeprice.repository.ApiRepository;
import com.dsg.wemakeprice.repository.CompanyRepository;
import com.dsg.wemakeprice.repository.ManagerRepository;
import com.dsg.wemakeprice.type.RegisterStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApiServiceImpl implements ApiService {

    private final CompanyRepository companyRepository;
    private final ManagerRepository managerRepository;
    private final ApiRepository apiRepository;

    @Override
    @Transactional
    public ApiResponse submit(ApiRequest apiRequest) {

        Manager manager = Manager.builder()
                .managerEmail(apiRequest.getManagerEmail())
                .managerName(apiRequest.getManagerName())
                .build();

        Manager savedManager = managerRepository.save(manager);

        Company company = Company.builder()
                .companyRegistrationNumber(apiRequest.getCompanyRegistrationNumber())
                .companyName(apiRequest.getCompanyName())
                .companyAddress(apiRequest.getCompanyAddress())
                .registerStatus(RegisterStatus.REQUEST)
                .manager(savedManager)
                .approvedDatetime(LocalDateTime.now())
                .build();

        Company savedCompany = companyRepository.save(company);


        return ApiResponse.mapToResponse(savedManager, savedCompany);
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse search(String companyRegistrationNumber) {

        ApiResponse apiResponse = apiRepository.search(companyRegistrationNumber);
        log.info("apiResponse: {}", apiResponse);

        return apiResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApiResponse> list(String companyRegistrationNumber, String companyName) {
        List<ApiResponse> responseList = apiRepository.list(companyRegistrationNumber, companyName);
        log.info("responseList: {}", responseList);

        return responseList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApiResponse> list() {
        List<ApiResponse> responseList = apiRepository.list();
        log.info("responseList: {}", responseList);

        return responseList;
    }

    @Override
    @Transactional
    public ApiResponse.ApproveDto approve(String adminId, String companyRegistrationNumber) {

        Company company = companyRepository.findById(companyRegistrationNumber)
        .orElseThrow(() -> new RuntimeException("존재하지 않는 company입니다."));
        // 승인
        company.approve();

        return ApiResponse.ApproveDto.builder()
                .adminId(adminId)
                .adminName("adminName")
                .approvedDatetime(company.getApprovedDatetime())
                .build();
    }


}
