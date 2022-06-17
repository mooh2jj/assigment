package com.dsg.wemakeprice.repository;

import com.dsg.wemakeprice.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, String> {

    List<Company> findByCompanyRegistrationNumberAndCompanyName(String companyRegistrationNumber, String companyName);
}
