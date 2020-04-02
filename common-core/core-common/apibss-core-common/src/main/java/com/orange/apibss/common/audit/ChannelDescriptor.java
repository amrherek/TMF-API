package com.orange.apibss.common.audit;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class to log channel information.
 *
 * @author Denis Borscia (deyb6792)
 *
 */
@XmlRootElement(name="backend")
@XmlAccessorType(XmlAccessType.FIELD)
public class ChannelDescriptor {
    private String name;
    private String url;
    private String ip;
    private String port;
    private long entryDate;
    private long exitDate;
    private long duration;


    /**
     * @return the executionDuration
     */
    public long getExecutionDuration() {
        return exitDate - entryDate;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }
    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }
    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }
    /**
     * @return the port
     */
    public String getPort() {
        return port;
    }
    /**
     * @param port the port to set
     */
    public void setPort(String port) {
        this.port = port;
    }
    /**
     * @return the entryDate
     */
    public long getEntryDate() {
        return entryDate;
    }
    /**
     * @param entryDate the entryDate to set
     */
    public void setEntryDate(long entryDate) {
        this.entryDate = entryDate;
    }
    /**
     * @return the exitDate
     */
    public long getExitDate() {
        return exitDate;
    }
    /**
     * @param exitDate the exitDate to set
     */
    public void setExitDate(long exitDate) {
        this.exitDate = exitDate;
        duration = exitDate - entryDate;
    }
    /**
     * @return the duration
     */
    public long getDuration() {
        return duration;
    }
    /**
     * @param duration the duration to set
     */
    public void setDuration(long duration) {
        this.duration = duration;
    }}
