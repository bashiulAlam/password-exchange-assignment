package com.assignment.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    public static final String PROPERTY_FILE_NAME = "config";
    private static final String PROPERTY_FILE_NAME_SEPARATOR = "-";
    private static final String PROPERTY_FILE_EXTENTION = ".properties";
    private static final String TARGET_PROFILE_PARAM_NAME = "endToEndTestProfile";

    public static Properties loadProperties(String fileName) {
        Properties properties = new Properties();
        String strBuildProfile = System.getenv(TARGET_PROFILE_PARAM_NAME);
        if (strBuildProfile == null) {
            strBuildProfile = "";
        }
        strBuildProfile = strBuildProfile.trim().toLowerCase();
        if (strBuildProfile.length() > 0) {
            strBuildProfile = PROPERTY_FILE_NAME_SEPARATOR + strBuildProfile;
        }
        strBuildProfile =
                fileName +
                        strBuildProfile +
                        PROPERTY_FILE_EXTENTION;
        System.out.println("Properties file " + strBuildProfile);
        InputStream inputStream = Configuration.class.getClassLoader().getResourceAsStream(strBuildProfile);
        try {
            properties.load(inputStream);
            System.out.println("Load Properties " + strBuildProfile + ": Loaded Successfully.");
        } catch (IOException e) {
            System.out.println("Failed to load" + strBuildProfile);
        }
        return properties;
    }
}
