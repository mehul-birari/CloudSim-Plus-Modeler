package com.clousimmodeler.project;

import cloudreports.models.CustomerRegistry;
import cloudreports.models.DatacenterRegistry;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

public interface SimulationService {
    public String generate(FormDataBean formDataBean) throws IOException;
}
