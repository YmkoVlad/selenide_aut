package com.selenide_hw.utils;

import com.selenide_hw.enums.Capability;
import com.selenide_hw.enums.PropertyFile;
import org.apache.commons.lang3.StringUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    public static String getProperty(PropertyFile propertyFile, Capability capability) {
        Properties properties = new Properties();
        try {
            FileReader fileReader = new FileReader("src/main/resources/" + propertyFile.getPathToFile());
            properties.load(fileReader);
        } catch (IOException e) {
            System.out.println("Properties are not loaded. Use default value");
        }
        for (Object key : properties.keySet()) {
            String systemValue = System.getProperty((String) key);
            if (!StringUtils.isEmpty(systemValue)) {
                properties.put(key, systemValue);
            }
        }
        return properties.getProperty(capability.getKey(), capability.getDefaultValue());
    }

    public static String getConfigPropery(Capability capability) {
        return getProperty(PropertyFile.CONFIG, capability);
    }
}
