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

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        jdbcTemplate.execute("DROP TABLE SIMULATION IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE SIMULATION(id INT PRIMARY KEY AUTO_INCREMENT, yaml VARCHAR2(1000), timestamp TIMESTAMP ,results VARCHAR2(1000))");
        int res = jdbcTemplate.update("INSERT into SIMULATION (yaml, timestamp, results) VALUES (?,?,?)",  "ExProperty", now(), "res");

        if (res > 0) {
            System.out.println("Insert success");
        }

        jdbcTemplate.query("SELECT * from SIMULATION", (rs, rowNum) ->
                new SimulationDB(rs.getString("yaml"), rs.getTimestamp("timestamp"), rs.getString("results")))
        .forEach(simulationDB -> System.out.println(simulationDB.toString()));

    }

}
