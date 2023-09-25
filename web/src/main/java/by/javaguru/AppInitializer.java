package by.javaguru;

import by.javaguru.dao.UserDaoJsonImpl;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class AppInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UserDaoJsonImpl.generateDatabase();
    }
}
