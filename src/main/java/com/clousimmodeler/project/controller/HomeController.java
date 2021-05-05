package com.clousimmodeler.project.controller;

import cloudreports.models.CloudletRegistry;
import cloudreports.models.DatacenterRegistry;
import cloudreports.models.HostRegistry;
import cloudreports.models.VmRegistry;
import com.clousimmodeler.project.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;


@Controller
public class HomeController {
    private SimulationService simulationService = new SimulationServiceImpl();
    FormDataBean yamlDataBean = new FormDataBean();

    @RequestMapping("/")
    public String start(){
        return "index";
    }


//    @RequestMapping(value = "/test", method = RequestMethod.POST, produces = "application/json")
//    public @ResponseBody List<OutputBean> test() {
////        ModelAndView model = new ModelAndView("index");
//        return cloudAutomationRunner();
//    }

    @RequestMapping(value = "/sendDataVmCloudlet", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody List<OutputBean> sendDataVmCloudlet(@RequestBody FormDataBean dataBean) throws  IOException {
        yamlDataBean.setVmRegistryList(dataBean.getVmRegistryList());
        yamlDataBean.setCloudletRegistryList(dataBean.getCloudletRegistryList());

        List<OutputBean> output = simulationService.generate(yamlDataBean);
        return output;
    }

    @RequestMapping(value = "/sendDataDC", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody void sendDataVmCloudlet(@RequestBody DataCenterBean dataBean){
        dataBean.getDatacenterRegistry().setHosts(dataBean.getHostRegistryList());
        yamlDataBean.getDatacenterRegistryList().add(dataBean.getDatacenterRegistry());
    }

}
