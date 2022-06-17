package com.dsg.wemakeprice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/batch")
@RequiredArgsConstructor
public class BatchController {

    // 수동 배치 진행
    private final JobLauncher jobLauncher;
    private final Job notificationAlarmJob;

    @GetMapping("/job")
    public String startJob() throws Exception {
        log.info("Starting the batch job");
        log.info("job: {}",notificationAlarmJob);

        Map<String, JobParameter> parameters = new HashMap<>();
        parameters.put("timestamp", new JobParameter(System.currentTimeMillis()));
        JobExecution jobExecution = jobLauncher.run(notificationAlarmJob, new JobParameters(parameters));
        log.info("Batch job: {}", jobExecution.getStatus());

        return "Batch job "+ jobExecution.getStatus();

    }
}