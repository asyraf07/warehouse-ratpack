package com.asyraf.whizware.infrastructure.config;

import lombok.Data;

@Data
public class AppConfig {

    private HibernateConfig hibernate;
    private DatabaseConfig database;

    @Data
    public class DatabaseConfig {
        private String url;
        private String username;
        private String password;
    }

    @Data
    public class HibernateConfig{
        private Boolean showSql;
        private Boolean formatSql;
        private String ddlAuto;
    }

}

