package com.orange.apibss.cucumber.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Header {
    
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String value;

}
