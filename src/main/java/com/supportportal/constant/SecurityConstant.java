package com.supportportal.constant;

public class SecurityConstant {
    public static final long EXPIRATION_TIME = 432_000_000;// 5 days expressed in millisecond
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String jwt_TOKEN_HEADER= "Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token can no br verified";
    public static final String GET_ARRAYS_LLC = "Get Arrys, LLC";
    public static final String GET_ARRAYS_ADMINISTRATION = "User Management portal";
    public static final String AUTHORITIES = "Authorities";
    public static final String FORBIDDEN_MESSAGE = "you need to log in to access this page";
    public static final String ACCESS_DENIED_MESSAGE = "you do not have permissoion to access this page ";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
    public static final String[] PUBLIC_URLS = {"/user/login","/user/register","/user/resetpassword/**","/user/image/**"};

}
