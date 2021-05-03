package com.clousimmodeler.project.controller;

import cloudreports.models.CloudletRegistry;
import cloudreports.models.DatacenterRegistry;
import cloudreports.models.HostRegistry;
import cloudreports.models.VmRegistry;
import com.clousimmodeler.project.FormDataBean;
import com.clousimmodeler.project.SimulationService;
import com.clousimmodeler.project.SimulationServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class HomeController {
    private SimulationService simulationService = new SimulationServiceImpl();
    FormDataBean dataBean = new FormDataBean();

    @RequestMapping("/")
    public ModelAndView start(){
        ModelAndView model = new ModelAndView("index");
        model.addObject("Output",new String("Output"));
        return model;
    }

    @RequestMapping("/sendDataHost")
    public String sendDataHost(@RequestBody List<HostRegistry> hostList){
        System.out.println(hostList);
        dataBean.setHostRegistryList(hostList);
        return "index";
    }

    @RequestMapping("/sendDataDC")
    public String sendDataDC(@RequestBody List<DatacenterRegistry> dcList){
        System.out.println(dcList);
        dataBean.setDatacenterRegistryList(dcList);
        return "index";
    }

    @RequestMapping("/sendDataVM")
    public String sendDataVM(@RequestBody List<VmRegistry> vmList){
        System.out.println(vmList);
        dataBean.setVmRegistryList(vmList);
        return "index";
    }

    @RequestMapping("/sendDataCloudlet")
    public String sendDataCloudlet(@RequestBody List<CloudletRegistry> cloudletList){
        System.out.println(cloudletList);
        dataBean.setCloudletRegistryList(cloudletList);
        return "index";
    }

}
