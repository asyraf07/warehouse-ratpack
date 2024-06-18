package com.asyraf.whizware.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.SneakyThrows;

import java.io.File;

public class ConfigReader {
    public static AppConfig appConfig;

    @SneakyThrows
    public static void setup() {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        appConfig = objectMapper.readValue(new File("src/main/resources/application.yaml"), AppConfig.class);
    }


}
