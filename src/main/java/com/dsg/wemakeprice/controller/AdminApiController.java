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

    // 3. 1) 제출된 신청서 조회 api, param o
    @GetMapping("/list/query")
    public ResponseEntity<?> listQuery(
            @RequestParam(value = "companyRegistrationNumber") String companyRegistrationNumber,
            @RequestParam(value = "companyName") String companyName
    ) {
        log.info("admin list/query start, companyRegistrationNumber: {}, companyName: {}",
                companyRegistrationNumber, companyName);
        return new ResponseEntity<>(apiService.list(companyRegistrationNumber, companyName), HttpStatus.OK);
    }

    // 3. 2) 제출된 신청서 조회 api, param x
    @GetMapping("/list")
    public ResponseEntity<?> list() {
        log.info("admin list start");
        return new ResponseEntity<>(apiService.list(), HttpStatus.OK);
    }

    // 4. 제출된 신청서 승인 api
    @PutMapping("/approve")
    public ResponseEntity<?> approve(
            @RequestParam("companyRegistrationNumber") String companyRegistrationNumber
    ) {
        log.info("admin approve start, companyRegistrationNumber: {}", companyRegistrationNumber);
        return new ResponseEntity<>(apiService.approve(companyRegistrationNumber), HttpStatus.OK);
    }
}
