package com.clousimmodeler.project;

import cloudreports.models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import org.apache.catalina.Host;

import javax.xml.crypto.Data;
import java.nio.file.ClosedDirectoryStreamException;
import java.util.ArrayList;
import java.util.List;

//class Example {
//
//    String name;
//    int value;
//
//    public Example() {
//
//    }
//
//    public Example(String name, int value) {
//        super();
//        this.name = name;
//        this.value = value;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getValue() {
//        return value;
//    }
//
//    public void setValue(int value) {
//        this.value = value;
//    }
//}
//
//class Out {
//    Example example = new Example("someName", 10);
//
//    public Out() {
//
//    }
//
//    public Out(Example example) {
//        super();
//        this.example = example;
//    }
//
//    public Example getExample() {
//        return example;
//    }
//
//    public void setExample(Example example) {
//        this.example = example;
//    }
//}


public class YAMLGeneration {


    public static void generate() throws JsonProcessingException {

        DatacenterRegistry datacenterRegistry = new DatacenterRegistry();
        HostRegistry hostRegistry = new HostRegistry();
        CustomerRegistry customerRegistry = new CustomerRegistry();
        VmRegistry vmRegistry = new VmRegistry();
        CloudletRegistry cloudletRegistry = new CloudletRegistry();

        POJO pojo = new POJO();
        pojo.hostRegistryList.add(hostRegistry);
        pojo.hostRegistryList.add(hostRegistry);
        datacenterRegistry.setHosts(pojo.hostRegistryList);
        pojo.datacenterRegistryList.add(datacenterRegistry);
        pojo.vmRegistryList.add(vmRegistry);
        pojo.cloudletRegistryList.add(cloudletRegistry);
        customerRegistry.setVms(pojo.vmRegistryList);
        customerRegistry.setCloudlets(pojo.cloudletRegistryList);
        pojo.customerRegistryList.add(customerRegistry);

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));
        String yamlDatacenter = mapper.writeValueAsString(pojo.datacenterRegistryList);
        String yamlCustomer = mapper.writeValueAsString(pojo.customerRegistryList);
        System.out.println(yamlDatacenter+yamlCustomer);

    }
}
