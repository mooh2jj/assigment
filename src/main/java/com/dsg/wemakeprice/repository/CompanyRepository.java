package com.dsg.wemakeprice.repository;

import com.dsg.wemakeprice.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, String> {

    Page<Company> findBy(Pageable pageable);
}
