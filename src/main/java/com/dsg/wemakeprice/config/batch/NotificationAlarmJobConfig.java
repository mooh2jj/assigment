package com.dsg.wemakeprice.config.batch;

import com.dsg.wemakeprice.component.mail.MailMessageGenerator;
import com.dsg.wemakeprice.component.mail.MailSendService;
import com.dsg.wemakeprice.dto.ApiResponse;
import com.dsg.wemakeprice.dto.NotificationAlarmDto;
import com.dsg.wemakeprice.entity.Company;
import com.dsg.wemakeprice.repository.ApiRepository;
import com.dsg.wemakeprice.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;

@Slf4j
@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class NotificationAlarmJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final CompanyRepository companyRepository;
    private final ApiRepository apiRepository;

    private final MailSendService mailSendService;
    private final MailMessageGenerator messageGenerator;

    private static final int CHUNK_SIZE = 10;

    @Bean("notificationAlarmJob")
    public Job notificationAlarmJob(Step notificationAlarmStep) {
        return jobBuilderFactory.get("notificationAlarmJob")
                .incrementer(new RunIdIncrementer())
                .start(notificationAlarmStep)
                .build();
    }

    @JobScope
    @Bean("notificationAlarmStep")
    public Step notificationAlarmStep(ItemReader<Company> notificationAlarmReader,
                                      ItemProcessor<Company, NotificationAlarmDto> notificationAlarmProcessor,
                                      ItemWriter<NotificationAlarmDto> notificationAlarmWriter) {
        return stepBuilderFactory.get("notificationAlarmStep")
                .<Company, NotificationAlarmDto>chunk(CHUNK_SIZE)
                .reader(notificationAlarmReader)
                .processor(notificationAlarmProcessor)
                .writer(notificationAlarmWriter)
                .build();
    }

    @StepScope
    @Bean
    public RepositoryItemReader<Company> notificationAlarmReader() {
        return new RepositoryItemReaderBuilder<Company>()
                .name("notificationAlarmReader")
                .repository(companyRepository)
                .methodName("findBy")
                .pageSize(CHUNK_SIZE)
                .arguments(List.of())
                .sorts(Collections.singletonMap("companyRegistrationNumber", Sort.Direction.ASC))
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<Company, NotificationAlarmDto> notificationAlarmProcessor() {
        return company -> {

            log.info("company: {}", company);
            ApiResponse apiResponse = apiRepository.findAllIsStatusApproval(company.getCompanyRegistrationNumber())
                    .orElseGet(ApiResponse::new);
            log.info("apiResponse: {}", apiResponse);

            NotificationAlarmDto notifiDto = NotificationAlarmDto.builder()
                    .email(apiResponse.getManagerEmail())
                    .name(apiResponse.getManagerName())
                    .apiResponse(apiResponse)
                    .build();
                log.info("notifiDto: {}", notifiDto);

            return notifiDto;       // null 일수 있음.
        };
    }

    @StepScope
    @Bean
    public ItemWriter<NotificationAlarmDto> notificationAlarmWriter() {
        return items -> items.forEach(
                item -> {
                    if(item.getApiResponse().getManagerEmail() != null){
                        String toMessage = messageGenerator.toMessage(item.getApiResponse());
                        log.info("sendMail: {}", toMessage);
                        mailSendService.sendMail(item.getEmail(), "신청 승인 회사 알림", toMessage);
                        log.info("==== chunk is finished");
                    }
                }
        );
    }
}
