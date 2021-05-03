package com.clousimmodeler.project;

import cloudreports.models.CustomerRegistry;
import cloudreports.models.DatacenterRegistry;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface SimulationService {
    public void generate(DatacenterRegistry datacenterRegistry, CustomerRegistry customerRegistry) throws JsonProcessingException;

    public void createLists(Map<String, String> formData) ;
}
