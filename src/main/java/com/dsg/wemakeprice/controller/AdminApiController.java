package com.dsg.wemakeprice.controller;

import com.dsg.wemakeprice.service.ApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin/api/")
@RequiredArgsConstructor
public class AdminApiController {

    private final ApiService apiService;

    // 3. 제출된 신청서 조회 api
    @GetMapping("/list")
    public ResponseEntity<?> list(
            @RequestParam("companyRegistrationNumber") String companyRegistrationNumber,
            @RequestParam("companyName") String companyName
    ) {
        log.info("admin list start, companyRegistrationNumber: {}, companyName: {}", companyRegistrationNumber, companyName);
        return new ResponseEntity<>(apiService.list(companyRegistrationNumber, companyName), HttpStatus.OK);
    }
}
