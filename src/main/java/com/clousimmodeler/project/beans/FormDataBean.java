package com.clousimmodeler.project.beans;

import cloudreports.models.*;

import java.util.ArrayList;
import java.util.List;

public class FormDataBean {

    List<DatacenterRegistry> datacenterRegistryList = new ArrayList<>();
    List<HostRegistry> hostRegistryList = new ArrayList<>();
    List<CustomerRegistry> customerRegistryList = new ArrayList<>();
    List<VmRegistry> vmRegistryList = new ArrayList<>();
    List<CloudletRegistry> cloudletRegistryList = new ArrayList<>();

    public List<DatacenterRegistry> getDatacenterRegistryList() {
        return datacenterRegistryList;
    }

    public void setDatacenterRegistryList(List<DatacenterRegistry> datacenterRegistryList) {
        this.datacenterRegistryList = datacenterRegistryList;
    }

    public List<HostRegistry> getHostRegistryList() {
        return hostRegistryList;
    }

    public void setHostRegistryList(List<HostRegistry> hostRegistryList) {
        this.hostRegistryList = hostRegistryList;
    }

    public List<CustomerRegistry> getCustomerRegistryList() {
        return customerRegistryList;
    }

    public void setCustomerRegistryList(List<CustomerRegistry> customerRegistryList) {
        this.customerRegistryList = customerRegistryList;
    }

    public List<VmRegistry> getVmRegistryList() {
        return vmRegistryList;
    }

    public void setVmRegistryList(List<VmRegistry> vmRegistryList) {
        this.vmRegistryList = vmRegistryList;
    }

    public List<CloudletRegistry> getCloudletRegistryList() {
        return cloudletRegistryList;
    }

    public void setCloudletRegistryList(List<CloudletRegistry> cloudletRegistryList) {
        this.cloudletRegistryList = cloudletRegistryList;
    }
}
