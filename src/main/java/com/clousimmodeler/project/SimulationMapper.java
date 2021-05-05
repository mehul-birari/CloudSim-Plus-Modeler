package com.clousimmodeler.project;

import com.clousimmodeler.project.beans.Simulation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

public class SimulationMapper {

    static JdbcTemplate jdbcTemplate;
    static Logger logger  = LoggerFactory.getLogger(SimulationMapper.class);

    public JdbcTemplate getJdbcTemplate(){
        return  jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    public static void createDB(){
        jdbcTemplate.execute("DROP TABLE SIMULATION IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE SIMULATION(id INT PRIMARY KEY AUTO_INCREMENT, yaml VARCHAR2, timestamp TIMESTAMP ,results VARCHAR2)");

    }

    public static boolean store(String yamlData, String result){
        int res = jdbcTemplate.update("INSERT into SIMULATION (yaml, timestamp, results) VALUES (?,?,?)",  yamlData, now(), result);

        if (res > 0) {
            logger.info("Insert success");
            return true;
        }
        return false;
    }

    public static List<Simulation> showResults(){
        List<Simulation> results = new ArrayList<>(jdbcTemplate.query("SELECT * from SIMULATION",
                (rs, rowNum) -> new Simulation(rs.getString("yaml"),
                        rs.getTimestamp("timestamp"),
                        rs.getString("results"))));
        return results;
    }
}
