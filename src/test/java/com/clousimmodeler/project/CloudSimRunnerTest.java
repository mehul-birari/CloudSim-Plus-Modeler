package com.clousimmodeler.project;

import cloudreports.models.*;
import com.clousimmodeler.project.beans.FormDataBean;
import com.clousimmodeler.project.beans.OutputBean;
import com.esotericsoftware.yamlbeans.YamlException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import org.cloudbus.cloudsim.cloudlets.CloudletSimple;
import org.cloudsimplus.automation.CloudSimulation;
import org.cloudsimplus.automation.YamlCloudScenario;
import org.cloudsimplus.automation.YamlCloudScenarioReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CloudSimRunnerTest {

    String yaml = "";
    List<OutputBean> outputBeanList;
    FormDataBean formDataBean ;

    @BeforeEach
    void setUp() {
        formDataBean =  new FormDataBean();
        DatacenterRegistry datacenterRegistry = new DatacenterRegistry();
        CustomerRegistry customerRegistry = new CustomerRegistry();
        CloudletRegistry cloudletRegistry = new CloudletRegistry();

        cloudletRegistry.setFileSize(1024);
        cloudletRegistry.setOutputSize(1024);

        VmRegistry vmRegistry = new VmRegistry();
        HostRegistry hostRegistry = new HostRegistry();

        formDataBean.setDatacenterRegistryList(Collections.singletonList(datacenterRegistry));
        formDataBean.setCustomerRegistryList(Collections.singletonList(customerRegistry));
        formDataBean.setCloudletRegistryList(Collections.singletonList(cloudletRegistry));
        formDataBean.setVmRegistryList(Collections.singletonList(vmRegistry));
        formDataBean.setHostRegistryList(Collections.singletonList(hostRegistry));


    }

    @Test
    void cloudAutomationRunner() throws FileNotFoundException, YamlException {
        final YamlCloudScenarioReader reader = new YamlCloudScenarioReader("src/main/resources/CloudEnvironment1.yml");
        yaml = reader.toString();

        outputBeanList =  CloudSimRunner.cloudAutomationRunner(yaml,"src/main/resources/CloudEnvironment1.yml");
        Assertions.assertEquals(outputBeanList.size(), 12);
    }

    @Test
    void yamlGenerate() throws JsonProcessingException {
        String yamlString = new SimulationServiceImpl().yamlGenerate(formDataBean);
        Assertions.assertEquals(yamlString.contains("vmm: \"Xen\""), true);
    }


    @Test
    void consoleOuput(){
        ByteArrayOutputStream test = new ByteArrayOutputStream();
        PrintStream PS = new PrintStream(test);
        PrintStream old = System.out;
        System.setOut(PS);

        System.out.print("Console Output");

        System.setOut(old);
        String output = test.toString();
        Assertions.assertEquals(output,"Console Output");
    }


    @Test
    void cloudSimOutput() throws FileNotFoundException, YamlException {
        final YamlCloudScenarioReader reader = new YamlCloudScenarioReader("src/main/resources/CloudEnvironment1.yml");
        //Gets the list or parsed scenarios.
        final List<YamlCloudScenario> simulationScenarios = reader.getScenarios();
        //For each existing scenario, creates and runs it in CloudSim Plus, printing results.
        List<List<CloudletSimple>> cloudletFinished = new ArrayList<>();
        for (YamlCloudScenario scenario : simulationScenarios) {
            CloudSimulation cloudSimulation = new CloudSimulation(scenario);
            cloudSimulation.run();
            cloudSimulation.getBrokers().forEach(datacenterBroker -> cloudletFinished.add(datacenterBroker.getCloudletFinishedList()));
        }
        Assertions.assertEquals(cloudletFinished.size(),2);
    }


    @Test
    void saveAsYAML() throws IOException {
        final YamlCloudScenarioReader reader = new YamlCloudScenarioReader("output.yml");
        //Gets the list or parsed scenarios.
        final List<YamlCloudScenario> simulationScenarios = reader.getScenarios();
        Assertions.assertEquals(simulationScenarios.size(),1);

    }
}