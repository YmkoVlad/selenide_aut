package com.selenide_hw.enums;

public enum PropertyFile {

    CONFIG("config.properties"),

    EMAIL("email.properties");

    private String pathToFile;

    PropertyFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public String getPathToFile() {
        return pathToFile;
    }
}
