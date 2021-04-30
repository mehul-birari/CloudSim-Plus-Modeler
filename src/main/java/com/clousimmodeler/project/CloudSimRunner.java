package com.clousimmodeler.project;

import com.esotericsoftware.yamlbeans.YamlException;
import org.cloudsimplus.automation.CloudSimulation;
import org.cloudsimplus.automation.YamlCloudScenario;
import org.cloudsimplus.automation.YamlCloudScenarioReader;

import java.io.FileNotFoundException;
import java.util.List;

public class CloudSimRunner {

    public static void main(String[] args) {

        try {
            //Loads a YAML file containing 1 or more simulation scenarios.
            final YamlCloudScenarioReader reader = new YamlCloudScenarioReader("src/main/resources/CloudEnvironment1.yml");
            //Gets the list or parsed scenarios.
            final List<YamlCloudScenario> simulationScenarios = reader.getScenarios();
            //For each existing scenario, creates and runs it in CloudSim Plus, printing results.
            for (YamlCloudScenario scenario : simulationScenarios) {
                new CloudSimulation(scenario).run();
            }
        } catch (FileNotFoundException | YamlException e) {
            System.err.println("Error when trying to load the simulation scenario from the YAML file: "+e.getMessage());
        }
    }
}
