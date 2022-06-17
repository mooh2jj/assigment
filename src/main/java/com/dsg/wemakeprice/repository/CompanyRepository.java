package com.dsg.wemakeprice.repository;

import com.dsg.wemakeprice.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, String> {
}
