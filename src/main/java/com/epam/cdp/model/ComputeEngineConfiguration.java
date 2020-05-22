package com.epam.cdp.model;

public class ComputeEngineConfiguration extends BaseConfiguration {
    private int instanceCount;
    private String os;
    private String vmClass;
    private String instanceType;
    private int gpuCount;
    private String gpuType;
    private int ssdCount;
    private String datacenterLocation;
    private int commitedUsageYears;

    public ComputeEngineConfiguration(int instanceCount, String os, String vmClass, String instanceType,
                                      int gpuCount, String gpuType, int ssdCount, String datacenterLocation, int commitedUsageYears) {
        this.instanceCount = instanceCount;
        this.os = os;
        this.vmClass = vmClass;
        this.instanceType = instanceType;
        this.gpuCount = gpuCount;
        this.gpuType = gpuType;
        this.ssdCount = ssdCount;
        this.datacenterLocation = datacenterLocation;
        this.commitedUsageYears = commitedUsageYears;
    }

    public int getInstanceCount() {
        return instanceCount;
    }

    public String getOs() {
        return os;
    }

    public String getVmClass() {
        return vmClass;
    }

    public String getInstanceType() {
        return instanceType;
    }

    public int getGpuCount() {
        return gpuCount;
    }

    public String getGpuType() {
        return gpuType;
    }

    public int getSsdCount() {
        return ssdCount;
    }

    public String getDatacenterLocation() {
        return datacenterLocation;
    }

    public int getCommitedUsageYears() {
        return commitedUsageYears;
    }
}
