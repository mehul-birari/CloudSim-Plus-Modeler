package com.clousimmodeler.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import static java.time.LocalTime.now;

@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;
    SimulationMapper simulationMapper = new SimulationMapper();

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        simulationMapper.setJdbcTemplate(jdbcTemplate);
        SimulationMapper.createDB();
    }

}
