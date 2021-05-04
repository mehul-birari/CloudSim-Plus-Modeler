package com.clousimmodeler.project;

import cloudreports.models.DatacenterRegistry;
import cloudreports.models.HostRegistry;

import java.util.List;

public class DataCenterBean {

    DatacenterRegistry datacenterRegistry;
    List<HostRegistry> hostRegistryList;

    public DatacenterRegistry getDatacenterRegistry() {
        return datacenterRegistry;
    }

    public void setDatacenterRegistry(DatacenterRegistry datacenterRegistry) {
        this.datacenterRegistry = datacenterRegistry;
    }

    public List<HostRegistry> getHostRegistryList() {
        return hostRegistryList;
    }

    public void setHostRegistryList(List<HostRegistry> hostRegistryList) {
        this.hostRegistryList = hostRegistryList;
    }

}
