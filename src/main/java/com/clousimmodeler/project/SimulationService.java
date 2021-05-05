package com.clousimmodeler.project;

import com.clousimmodeler.project.beans.FormDataBean;
import com.clousimmodeler.project.beans.OutputBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public interface SimulationService {

    static Logger logger  = LoggerFactory.getLogger(CloudSimRunner.class);
    public List<OutputBean> generate(FormDataBean formDataBean) throws IOException;
}
