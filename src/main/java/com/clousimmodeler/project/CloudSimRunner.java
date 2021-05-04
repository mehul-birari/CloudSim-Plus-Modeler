package com.clousimmodeler.project;

import com.esotericsoftware.yamlbeans.YamlException;
import org.cloudsimplus.automation.CloudSimulation;
import org.cloudsimplus.automation.YamlCloudScenario;
import org.cloudsimplus.automation.YamlCloudScenarioReader;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class CloudSimRunner {

    public static String cloudAutomationRunner() {

        String output = "";
        try {

            ByteArrayOutputStream test = new ByteArrayOutputStream();
            PrintStream PS = new PrintStream(test);
            PrintStream old = System.out;
            System.setOut(PS);

            //Loads a YAML file containing 1 or more simulation scenarios.
            final YamlCloudScenarioReader reader = new YamlCloudScenarioReader("output.yml");
            //Gets the list or parsed scenarios.
            final List<YamlCloudScenario> simulationScenarios = reader.getScenarios();
            //For each existing scenario, creates and runs it in CloudSim Plus, printing results.
            List cloudletFinished = new ArrayList();
            for (YamlCloudScenario scenario : simulationScenarios) {
                CloudSimulation cloudSimulation = new CloudSimulation(scenario);
                cloudSimulation.run();
                cloudSimulation.getBrokers().forEach(datacenterBroker -> cloudletFinished.add(datacenterBroker.getCloudletFinishedList()));
            }

            System.out.println(cloudletFinished);

            System.setOut(old);
            output = test.toString();

        } catch (Exception e) {
            System.err.println("Error when trying to load the simulation scenario from the YAML file: "+e.getMessage());
        }

        return output;
    }
}
