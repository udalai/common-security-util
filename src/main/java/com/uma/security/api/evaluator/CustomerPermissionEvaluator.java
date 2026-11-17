package com.uma.security.api.evaluator;

import com.uma.security.api.SecurityAppUtil;
import com.uma.security.api.integration.UserProfileClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerPermissionEvaluator {

    private final UserProfileClient userProfileClient;

    public boolean hasMatchingCustomerNumber(String customerNumber) {
        if (SecurityAppUtil.isInternalUser()) {
            return true;
        }

        var accessedBy = SecurityAppUtil.getUserId();
        log.info("CustomerPermissionEvaluator -> customerNumber: {}, accessedBy: {}", customerNumber, accessedBy);

        return customerNumber.equals(customerNumberOnFile(accessedBy));
    }

    public boolean hasMatchingUsername(String userName) {
        var accessedBy = SecurityAppUtil.getUserId();
        return accessedBy.equals(userName);
    }

    private String customerNumberOnFile(String username) {
        return userProfileClient.getUser(username).getCustomerNumber();
    }


}
