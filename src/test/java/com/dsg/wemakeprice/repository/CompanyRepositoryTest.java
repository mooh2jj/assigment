package com.dsg.wemakeprice.repository;

import com.dsg.wemakeprice.entity.Company;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;

    private Company company;

    @BeforeEach
    void setup() {
        company = Company.builder()
                .companyRegistrationNumber("1234")
                .companyName("dsgCompany")
                .approvedDatetime(LocalDateTime.now())
                .build();
    }

    @Test
    void findAll() {
        LongStream.rangeClosed(1,20).forEach(i -> {
            Company company = Company.builder()
                    .companyRegistrationNumber("1234_"+i)
                    .companyName("dsgCompany_"+i)
                    .approvedDatetime(LocalDateTime.now())
                    .build();

            companyRepository.save(company);
        });

        List<Company> all = companyRepository.findAll();
        log.info("all: {}", all);

        assertThat(all.size()).isEqualTo(20);
    }
}