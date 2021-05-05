package com.clousimmodeler.project;

public class OutputBean {
    long cloudlet_id;
    String status;
    long dc_id;
    long host_id;
    int host_pes;
    long vm_id;
    long vm_pes;
    long cloudletLen;
    int cloudPes;
    double startTime;
    double finishTime;
    double execTime;

    public long getCloudlet_id() {
        return cloudlet_id;
    }

    public void setCloudlet_id(long cloudlet_id) {
        this.cloudlet_id = cloudlet_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getDc_id() {
        return dc_id;
    }

    public void setDc_id(long dc_id) {
        this.dc_id = dc_id;
    }

    public long getHost_id() {
        return host_id;
    }

    public void setHost_id(long host_id) {
        this.host_id = host_id;
    }

    public int getHost_pes() {
        return host_pes;
    }

    public void setHost_pes(int host_pes) {
        this.host_pes = host_pes;
    }

    public long getVm_id() {
        return vm_id;
    }

    public void setVm_id(long vm_id) {
        this.vm_id = vm_id;
    }

    public long getVm_pes() {
        return vm_pes;
    }

    public void setVm_pes(long vm_pes) {
        this.vm_pes = vm_pes;
    }

    public long getCloudletLen() {
        return cloudletLen;
    }

    public void setCloudletLen(long cloudletLen) {
        this.cloudletLen = cloudletLen;
    }

    public int getCloudPes() {
        return cloudPes;
    }

    public void setCloudPes(int cloudPes) {
        this.cloudPes = cloudPes;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public double getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(double finishTime) {
        this.finishTime = finishTime;
    }

    public double getExecTime() {
        return execTime;
    }

    public void setExecTime(double execTime) {
        this.execTime = execTime;
    }


}
