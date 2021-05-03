package com.clousimmodeler.project;

import cloudreports.models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

import java.util.Map;


public class SimulationServiceImpl implements SimulationService {

    @Override
    public void createLists(Map<String, String> formData) {
        System.out.println(formData);
        System.out.println(formData.get("Host"));
        System.out.println(formData.get("Datacenter"));
        System.out.println(formData.get("Cloudlet"));
        System.out.println(formData.get("Virtual Machine"));

    }

    public void generate(DatacenterRegistry datacenterRegistry,CustomerRegistry customerRegistry) throws JsonProcessingException {
//
////        DatacenterRegistry datacenterRegistry = new DatacenterRegistry();
////        HostRegistry hostRegistry = new HostRegistry();
////        CustomerRegistry customerRegistry = new CustomerRegistry();
////        VmRegistry vmRegistry = new VmRegistry();
////        CloudletRegistry cloudletRegistry = new CloudletRegistry();
////
//        FormDataBean pojo = new FormDataBean();
////        pojo.hostRegistryList.add(hostRegistry);
////        pojo.hostRegistryList.add(hostRegistry);
////        datacenterRegistry.setHosts(pojo.hostRegistryList);
//        pojo.datacenterRegistryList.add(datacenterRegistry);
////        pojo.vmRegistryList.add(vmRegistry);
////        pojo.cloudletRegistryList.add(cloudletRegistry);
////        customerRegistry.setVms(pojo.vmRegistryList);
////        customerRegistry.setCloudlets(pojo.cloudletRegistryList);
////        pojo.customerRegistryList.add(customerRegistry);
//
//        ObjectMapper mapper = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));
//        String yamlDatacenter = mapper.writeValueAsString(pojo.datacenterRegistryList);
//        String yamlCustomer = mapper.writeValueAsString(pojo.customerRegistryList);
//        System.out.println(yamlDatacenter+yamlCustomer);
//
    }


}
