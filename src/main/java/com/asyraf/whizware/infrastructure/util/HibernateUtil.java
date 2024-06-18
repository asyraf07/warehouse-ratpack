package com.asyraf.whizware.infrastructure.util;

import com.asyraf.whizware.domain.item.Item;
import com.asyraf.whizware.domain.order.Order;
import com.asyraf.whizware.domain.user.User;
import com.asyraf.whizware.infrastructure.config.ConfigReader;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private SessionFactory sessionFactory;
    public HibernateUtil() {
        ConfigReader.setup();
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url", ConfigReader.appConfig.getDatabase().getUrl());
        configuration.setProperty("hibernate.connection.username", ConfigReader.appConfig.getDatabase().getUsername());
        configuration.setProperty("hibernate.connection.password", ConfigReader.appConfig.getDatabase().getPassword());

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        configuration.setProperty("hibernate.show_sql", ConfigReader.appConfig.getHibernate().getShowSql());
        configuration.setProperty("hibernate.format_sql", ConfigReader.appConfig.getHibernate().getFormatSql());

        configuration.setProperty("hibernate.hbm2ddl.auto", ConfigReader.appConfig.getHibernate().getDdlAuto());

        configuration.addAnnotatedClass(Item.class);
        configuration.addAnnotatedClass(Order.class);
        configuration.addAnnotatedClass(User.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            ConfigReader.setup();
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
            configuration.setProperty("hibernate.connection.url", ConfigReader.appConfig.getDatabase().getUrl());
            configuration.setProperty("hibernate.connection.username", ConfigReader.appConfig.getDatabase().getUsername());
            configuration.setProperty("hibernate.connection.password", ConfigReader.appConfig.getDatabase().getPassword());

            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            configuration.setProperty("hibernate.show_sql", ConfigReader.appConfig.getHibernate().getShowSql());
            configuration.setProperty("hibernate.format_sql", ConfigReader.appConfig.getHibernate().getFormatSql());

            configuration.setProperty("hibernate.hbm2ddl.auto", ConfigReader.appConfig.getHibernate().getDdlAuto());
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }
}
