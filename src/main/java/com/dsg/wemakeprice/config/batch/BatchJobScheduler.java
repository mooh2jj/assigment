package com.dsg.wemakeprice.config.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class BatchJobScheduler {

    private final JobLauncher jobLauncher;

    private final Job notificationAlarmJob;

    // 30분 마다 배치 진행 -> 메일 알림 보냄
    @Scheduled(cron = "0 0/30 * * * *")
    public void runJob() {

        Map<String, JobParameter> parameters = new HashMap<>();
        parameters.put("timestamp", new JobParameter(System.currentTimeMillis()));

        try {
            jobLauncher.run(notificationAlarmJob, new JobParameters(parameters));
        } catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
