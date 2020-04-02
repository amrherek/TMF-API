package com.orange.apibss.common.audit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ApiStats {

    private Long entryDate;

    private Long exitDate;

    private String executionTime;

    public ApiStats() {
        entryDate = Calendar.getInstance().getTimeInMillis();
    }

    /**
     * @param exitDate
     *            the exitDate to set
     */
    public void setExitDate(Long exitDate) {
        this.exitDate = exitDate;
        this.executionTime = String.valueOf(exitDate - entryDate) + " ms";
    }

    /**
     * @return the entryDate
     */
    public String getEntryDate() {
        return formatDate(entryDate);
    }

    public Long getEntryDateTime() {
        return entryDate;
    }

    /**
     * @return the exitDate
     */
    public String getExitDate() {
        return formatDate(exitDate);
    }

    private String formatDate(Long exitDate) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss z");
        return dateFormat.format(exitDate);
    }

    /**
     * @return the executionTime
     */
    public String getExecutionTime() {
        return executionTime;
    }

}
