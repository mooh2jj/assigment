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

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApiServiceImpl implements ApiService {

    private final CompanyRepository companyRepository;
    private final ManagerRepository managerRepository;
    private final ApiRepository apiRepository;

    @Override
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
                .build();

        Company savedCompany = companyRepository.save(company);


        return ApiResponse.mapToResponse(savedManager, savedCompany);
    }

    @Override
    public List<ApiResponse> search(String companyRegistrationNumber) {

        List<ApiResponse> responseList = apiRepository.search(companyRegistrationNumber);
        log.info("responseList: {}", responseList);

        return responseList;
    }


}
