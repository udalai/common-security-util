package com.uma.security.api;

public class Constants {
    public static final String X_REQUEST_INITIATOR = "X-request-initiator";
    public static final String X_AUTH_ROLES = "X-auth-roles";

    public static final String X_AUTH_EMAIL = "X-auth-email";

    public static final String X_AUTH_USERID = "X-auth-userid";

    public static final String X_AUTH_USERNAME = "X-auth-username";

    public static final String ROLE_PREFIX = "ROLE_";

    public static final String DXP = "DXP";

    public static final String DPA = "DPA";

    public static final String DXP_GATEWAY = "dxp-gateway";

    public static final String TME_ADMIN_USERS = "TME-ADMIN-USERS";

    public static final String DPA_API_USERS = "DPA-API-USERS";

    public static final String EMPTY = "";

    public static final String INTERNAL_ROLES = "hasAnyRole('TME-ADMIN-USERS', 'TME-TMO-USERS', 'TME-TMSA-USERS', 'TME-VIEW-ONLY-USERS', 'TME-RM-BC-USERS', 'DXPUsers')";

    public static final String EXTERNAL_ROLES = "hasAnyRole('DXPUsersExternal')";

    public static final String ADMIN_ONLY = "hasAnyRole('TME-ADMIN-USERS')";

    public static final String EXTERNAL_API_USERS = "hasAnyRole('DPA-API-USERS')";

    public static final String INTERNAL_EXTERNAL = INTERNAL_ROLES + " or " + EXTERNAL_ROLES;

    public static final String VERIFIED_CUSTOMER = INTERNAL_ROLES + " or (" + EXTERNAL_ROLES + " and @customerPermissionEvaluator.hasMatchingCustomerNumber(#customerNumber))";

    public static final String VERIFIED_ENROLLMENT = INTERNAL_ROLES + " or (" + EXTERNAL_ROLES + " and @enrollmentPermissionEvaluator.hasEnrollmentPermission(#enrollmentId))";

    public static final String VERIFIED_CUSTOMER_ENROLLMENT = INTERNAL_ROLES + " or (" + EXTERNAL_ROLES + " and @enrollmentPermissionEvaluator.hasMatchingCustomerNumber(#enrollment))";

    public static final String VERIFIED_ENROLLMENT_CUSTOMER = "(" + INTERNAL_EXTERNAL + ")"
            + " and @enrollmentPermissionEvaluator.hasEnrollmentPermission(#enrollmentId) )"
            + " and @enrollmentPermissionEvaluator.hasMatchingCustomerNumber(#enrollmentId, #customerNumber) )";

    public static final String VERIFIED_USER = "@customerPermissionEvaluator.hasMatchingUsername(#userName)";









}
