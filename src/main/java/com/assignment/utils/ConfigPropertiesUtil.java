package com.assignment.utils;

import java.util.Properties;

public class ConfigPropertiesUtil {
    //user credential variables
    public static String USER_EMAIL;
    public static String USER;
    public static String PASSWORD;

    //e-commerce variables
    public static String E_COMMERCE_URL;

    public interface ConfigElements {
        //user elements
        static final String USER_EMAIL = "USER_EMAIL";
        static final String USER = "USER";
        static final String PASSWORD = "PASSWORD";

        //e-commerce elements
        static final String E_COMMERCE_URL = "E_COMMERCE_URL";
    }

    static {
        //load property file
        Properties configProperties = Configuration.loadProperties(Configuration.PROPERTY_FILE_NAME);

        //assign user variables
        USER_EMAIL = configProperties.getProperty(ConfigElements.USER_EMAIL);
        USER = configProperties.getProperty(ConfigElements.USER);
        PASSWORD = configProperties.getProperty(ConfigElements.PASSWORD);

        //assign e-commerce variables
        E_COMMERCE_URL = configProperties.getProperty(ConfigElements.E_COMMERCE_URL);
    }
}
