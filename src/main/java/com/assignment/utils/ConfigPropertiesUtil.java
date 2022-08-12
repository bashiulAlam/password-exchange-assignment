package com.assignment.utils;

import java.util.Properties;

public class ConfigPropertiesUtil {
    //user credential variables
    public static String USER_EMAIL;
    public static String USER;
    public static String PASSWORD;

    //url variable
    public static String PRODUCTS_UP_URL;

    public interface ConfigElements {
        //user elements
        String USER_EMAIL = "USER_EMAIL";
        String USER = "USER";
        String PASSWORD = "PASSWORD";

        //url element
        String PRODUCTS_UP_URL = "PRODUCTS_UP_URL";
    }

    static {
        //load property file
        Properties configProperties = Configuration.loadProperties(Configuration.PROPERTY_FILE_NAME);

        //assign user variables
        USER_EMAIL = configProperties.getProperty(ConfigElements.USER_EMAIL);
        USER = configProperties.getProperty(ConfigElements.USER);
        PASSWORD = configProperties.getProperty(ConfigElements.PASSWORD);

        //assign url variable
        PRODUCTS_UP_URL = configProperties.getProperty(ConfigElements.PRODUCTS_UP_URL);
    }
}
