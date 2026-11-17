package com.uma.security.api.integration.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProfile {
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String customerNumber;
}
