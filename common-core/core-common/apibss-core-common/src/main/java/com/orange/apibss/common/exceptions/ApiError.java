package com.orange.apibss.common.exceptions;

import java.net.URL;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

    private Integer code;
    private String message;
    private String description;
    private URL infoURL;

    public Integer getCode() {
        return this.code;
    }

    public void setCode(final Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public URL getInfoURL() {
        return this.infoURL;
    }

    public void setInfoURL(final URL infoURL) {
        this.infoURL = infoURL;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ApiError [code=" + this.code + ", message=" + this.message + ", description=" + this.description
                + ", infoURL=" + this.infoURL + "]";
    }

}
