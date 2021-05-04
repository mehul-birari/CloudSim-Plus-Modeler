package com.clousimmodeler.project.controller;

import cloudreports.models.CloudletRegistry;
import cloudreports.models.DatacenterRegistry;
import cloudreports.models.HostRegistry;
import cloudreports.models.VmRegistry;
import com.clousimmodeler.project.CloudSimRunner;
import com.clousimmodeler.project.DataCenterBean;
import com.clousimmodeler.project.FormDataBean;
import com.clousimmodeler.project.SimulationService;
import com.clousimmodeler.project.SimulationServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;


@Controller
public class HomeController {
    private SimulationService simulationService = new SimulationServiceImpl();
    FormDataBean yamlDataBean = new FormDataBean();

    @RequestMapping("/")
    public ModelAndView start(){
        ModelAndView model = new ModelAndView("index");
        model.addObject("Output",new String("Output"));
        return model;
    }

    @RequestMapping("/sendDataVmCloudlet")
    public ModelAndView sendDataVmCloudlet(@RequestBody FormDataBean dataBean) throws  IOException {
        yamlDataBean.setVmRegistryList(dataBean.getVmRegistryList());
        yamlDataBean.setCloudletRegistryList(dataBean.getCloudletRegistryList());

        String output = simulationService.generate(yamlDataBean);
        var mav = new ModelAndView("output");
        mav.addObject("output", output);
        return mav;
    }

    @RequestMapping("/sendDataDC")
    public String sendDataDC(@RequestBody DataCenterBean dataBean){
        dataBean.getDatacenterRegistry().setHosts(dataBean.getHostRegistryList());
        yamlDataBean.getDatacenterRegistryList().add(dataBean.getDatacenterRegistry());
        return "index";
    }

}
