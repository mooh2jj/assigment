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
            @RequestHeader(value = "adminId") String adminId,
            @RequestParam(value = "companyRegistrationNumber") String companyRegistrationNumber,
            @RequestParam(value = "companyName") String companyName
    ) {
        log.info("admin list/query start, adminId: {}, companyRegistrationNumber: {}, companyName: {}",
                adminId, companyRegistrationNumber, companyName);
        return new ResponseEntity<>(apiService.list(companyRegistrationNumber, companyName), HttpStatus.OK);
    }

    // 3. 2) 제출된 신청서 조회 api, param x
    @GetMapping("/list")
    public ResponseEntity<?> list(
            @RequestHeader(value = "adminId") String adminId
    ) {
        log.info("admin list start, adminId: {}", adminId);
        return new ResponseEntity<>(apiService.list(), HttpStatus.OK);
    }

    // 4. 제출된 신청서 승인 api
    @PutMapping("/approve")
    public ResponseEntity<?> approve(
            @RequestHeader(value = "adminId") String adminId,
            @RequestParam("companyRegistrationNumber") String companyRegistrationNumber
    ) {
        log.info("admin approve start, adminId: {}, companyRegistrationNumber: {}", adminId, companyRegistrationNumber);
        return new ResponseEntity<>(apiService.approve(adminId, companyRegistrationNumber), HttpStatus.OK);
    }
}
