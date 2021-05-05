package com.clousimmodeler.project;

import com.clousimmodeler.project.beans.FormDataBean;
import com.clousimmodeler.project.beans.OutputBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public interface SimulationService {

    static Logger logger  = LoggerFactory.getLogger(SimulationService.class);
    public List<OutputBean> generate(FormDataBean formDataBean, String fileName) throws IOException;
    public String yamlGenerate(FormDataBean formDataBean) throws JsonProcessingException;

}
