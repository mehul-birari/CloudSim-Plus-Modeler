package com.clousimmodeler.project;

import org.cloudbus.cloudsim.cloudlets.Cloudlet;
import org.cloudbus.cloudsim.cloudlets.CloudletSimple;
import org.cloudsimplus.automation.CloudSimulation;
import org.cloudsimplus.automation.YamlCloudScenario;
import org.cloudsimplus.automation.YamlCloudScenarioReader;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class CloudSimRunner {

    public static List<OutputBean> cloudAutomationRunner() {

        List<OutputBean> outputBeanList = new ArrayList<>();
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
            List<List<CloudletSimple>> cloudletFinished = new ArrayList<>();
            for (YamlCloudScenario scenario : simulationScenarios) {
                CloudSimulation cloudSimulation = new CloudSimulation(scenario);
                cloudSimulation.run();
                cloudSimulation.getBrokers().forEach(datacenterBroker -> cloudletFinished.add(datacenterBroker.getCloudletFinishedList()));
            }

            System.setOut(old);
            output = test.toString();

            for(List<CloudletSimple> cloudlets: cloudletFinished){
                for(CloudletSimple cloudlet: cloudlets){
                    OutputBean outputBean = new OutputBean();
                    outputBean.setCloudlet_id(cloudlet.getId());
                    outputBean.setStatus(cloudlet.getStatus().toString());
                    outputBean.setDc_id(cloudlet.getLastTriedDatacenter().getId());
//                    outputBean.setHost_id(cloudlet.);
//                    outputBean.setHost_pes();
                    outputBean.setVm_id(cloudlet.getVm().getId());
                    outputBean.setVm_pes(cloudlet.getVm().getNumberOfPes());
                    outputBean.setCloudletLen(cloudlet.getLength());
                    outputBean.setCloudletLen(cloudlet.getNumberOfPes());
                    outputBean.setStartTime(cloudlet.getExecStartTime());
                    outputBean.setFinishTime(cloudlet.getFinishTime());
                    outputBean.setExecTime(cloudlet.getActualCpuTime());
                    outputBeanList.add(outputBean);
                }
            }

        } catch (Exception e) {
            System.err.println("Error when trying to load the simulation scenario from the YAML file: "+e.getMessage());
        }

        return outputBeanList;
    }
}
