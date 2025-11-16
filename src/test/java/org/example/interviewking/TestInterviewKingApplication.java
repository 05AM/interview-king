package org.example.interviewking;

import org.springframework.boot.SpringApplication;

public class TestInterviewKingApplication {

    public static void main(String[] args) {
        SpringApplication.from(InterviewKingApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
