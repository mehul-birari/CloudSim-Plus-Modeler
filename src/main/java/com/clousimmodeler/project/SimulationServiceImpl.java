package com.clousimmodeler.project;

import cloudreports.models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.clousimmodeler.project.CloudSimRunner.cloudAutomationRunner;


public class SimulationServiceImpl implements SimulationService {

    public List<OutputBean> generate(FormDataBean formDataBean) throws IOException {

        CustomerRegistry customerRegistry = new CustomerRegistry();
        customerRegistry.setCloudlets(formDataBean.getCloudletRegistryList());
        customerRegistry.setVms(formDataBean.getVmRegistryList());

        formDataBean.getCustomerRegistryList().add(customerRegistry);

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));
        String yamlDatacenter = "datacenters:\n"+mapper.writeValueAsString(formDataBean.datacenterRegistryList);
        String yamlCustomer = "\ncustomers:\n"+mapper.writeValueAsString(formDataBean.customerRegistryList);
//        System.out.println(yamlDatacenter+yamlCustomer);

        try{
            FileWriter myWriter = new FileWriter("output.yml");
            myWriter.write(yamlDatacenter+yamlCustomer);
            myWriter.close();
        }catch (Exception e){
            System.out.println(e);
        }

        return cloudAutomationRunner();
    }


}
