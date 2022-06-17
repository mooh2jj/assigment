package com.dsg.wemakeprice.repository;

import com.dsg.wemakeprice.dto.ApiResponse;
import com.dsg.wemakeprice.dto.QApiResponse;
import com.dsg.wemakeprice.type.RegisterStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.dsg.wemakeprice.entity.QCompany.company;
import static com.dsg.wemakeprice.entity.QManager.manager;

@Repository
public class ApiRepository {

    private final JPAQueryFactory queryFactory;

    public ApiRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    public ApiResponse search(String companyRegistrationNumber) {
        return queryFactory
                .select(new QApiResponse(
                        company.companyRegistrationNumber,
                        company.companyName,
                        company.companyAddress,
                        company.registerStatus,
                        manager.managerName,
                        manager.managerEmail,
                        company.approvedDatetime))
                .from(company)
                .leftJoin(company.manager, manager)
                .where(
                        company.companyRegistrationNumber.eq(companyRegistrationNumber)
                ).fetchOne();
    }

    public List<ApiResponse> list(String companyRegistrationNumber, String companyName) {
        return queryFactory
                .select(new QApiResponse(
                        company.companyRegistrationNumber,
                        company.companyName,
                        company.companyAddress,
                        company.registerStatus,
                        manager.managerName,
                        manager.managerEmail,
                        company.approvedDatetime))
                .from(company)
                .leftJoin(company.manager, manager)
                .where(company.companyRegistrationNumber.eq(companyRegistrationNumber)
                        .and(company.companyName.eq(companyName))
                ).fetch();
    }

    public List<ApiResponse> list() {
        return queryFactory
                .select(new QApiResponse(
                        company.companyRegistrationNumber,
                        company.companyName,
                        company.companyAddress,
                        company.registerStatus,
                        manager.managerName,
                        manager.managerEmail,
                        company.approvedDatetime))
                .from(company)
                .leftJoin(company.manager, manager)
                .fetch();
    }

    public Optional<ApiResponse> findAllIsStatusApproval(String companyRegistrationNumber) {
        return queryFactory
                .select(new QApiResponse(
                        company.companyRegistrationNumber,
                        company.companyName,
                        company.companyAddress,
                        company.registerStatus,
                        manager.managerName,
                        manager.managerEmail,
                        company.approvedDatetime))
                .from(company)
                .leftJoin(company.manager, manager)
                .where(company.companyRegistrationNumber.eq(companyRegistrationNumber)
                        .and(company.registerStatus.eq(RegisterStatus.APPROVAL)))
                .stream().findFirst();
    }
}
