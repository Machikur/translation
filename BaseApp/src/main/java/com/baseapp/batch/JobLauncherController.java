package com.baseapp.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class JobLauncherController {

    private final Job job;
    private final JobLauncher jobLauncher;

    @Scheduled(cron = "0,20,40 * * * * *")
    public void runJob() {
        try {
            jobLauncher.run(job, new JobParameters());
        } catch (Exception s) {
            log.error(s.getMessage(), s.getClass());
        }
    }

}
