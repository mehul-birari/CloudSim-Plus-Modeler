package com.clousimmodeler.project;

import com.clousimmodeler.project.beans.OutputBean;
import org.cloudbus.cloudsim.cloudlets.CloudletSimple;
import org.cloudsimplus.automation.CloudSimulation;
import org.cloudsimplus.automation.YamlCloudScenario;
import org.cloudsimplus.automation.YamlCloudScenarioReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class CloudSimRunner {

    private static Logger logger  = LoggerFactory.getLogger(CloudSimRunner.class);

    public static List<OutputBean> cloudAutomationRunner() {

        logger.info("cloudAutomationRunner() method | start");
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
                    outputBean.setStatus(cloudlet.getStatus().name());
                    outputBean.setDc_id(cloudlet.getVm().getHost().getDatacenter().getId());
                    outputBean.setHost_id(cloudlet.getVm().getHost().getId());
                    outputBean.setHost_pes(cloudlet.getVm().getHost().getWorkingPesNumber());
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
            logger.error("Error when trying to load the simulation scenario from the YAML file: "+e.getMessage());
        }

        logger.info("cloudAutomationRunner() method | end");
        return outputBeanList;
    }
}
