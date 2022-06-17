package com.dsg.wemakeprice.controller;

import com.dsg.wemakeprice.dto.ApiRequest;
import com.dsg.wemakeprice.dto.ApiResponse;
import com.dsg.wemakeprice.service.ApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/client/api")
@RequiredArgsConstructor
public class ClientApiController {

    private final ApiService apiService;

    /**
     * 1. 입점을 원하는 회사는 신청서를 제출할 수 있습니다. - 신청서 제출 api
     * 2. 입점을 원하는 회사는 제출된 신청서를 확인 할 수 있습니다. - 신청서 조회 api
     * 3. 신청서는 승인이 완료된 신청서를 포함하여 사업자등록번호당 1개만 신청할 수 있습니다. => pk로
     * 4. 관리자는 신청된 신청서 목록을 확인 후 승인 할 수 있습니다. - 신청서 조회 api + 신청서 승인 api
     * 5. 신청서가 승인될 시에 신청서에 저장된 회사 담당자 메일로 메일 발송을 합니다.
     */

    // 1. 입점을 원하는 회사는 신청서를 제출할 수 있습니다. - 신청서 제출 api
    @PostMapping("/submit")
    public ResponseEntity<?> submit(@RequestBody ApiRequest apiRequest) {
        log.info("client submit start, ApiRequest: {}", apiRequest);
        ApiResponse response = apiService.submit(apiRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 2. 입점을 원하는 회사는 제출된 신청서를 확인 할 수 있습니다. - 신청서 조회 api
    @GetMapping("/search")
    public ResponseEntity<?> search(
            @RequestParam("companyRegistrationNumber") String companyRegistrationNumber
    ) {
        log.info("client search start, companyRegistrationNumber: {}", companyRegistrationNumber);
        return new ResponseEntity<>(apiService.search(companyRegistrationNumber), HttpStatus.OK);
    }

}
