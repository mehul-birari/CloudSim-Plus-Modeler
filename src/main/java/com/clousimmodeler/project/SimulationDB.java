package com.clousimmodeler.project;

import java.sql.Timestamp;

public class SimulationDB {

    private int id;
    private String yaml;
    private Timestamp timestamp;
    private String results;

    protected SimulationDB() {}

    protected SimulationDB(String yaml, Timestamp timestamp, String results) {
        this.yaml = yaml;
        this.timestamp = timestamp;
        this.results = results;
    }

    public String getYaml() {
        return this.yaml;
    }

    public void setYaml() {
        this.yaml = yaml;
    }

    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp() {
        this.timestamp = timestamp;
    }

    public String getResults() {
        return this.results;
    }

    public void setResults() {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Yaml: " + yaml + " Timestamp: " + timestamp + " Results: " + results;
    }
}
