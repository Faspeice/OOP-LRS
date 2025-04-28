package ru.omgtu;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import ru.omgtu.service.PointService;
import ru.omgtu.servlets.PointServlet;


public class AppInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ObjectMapper objectMapper = new ObjectMapper();
        PointService pointService = new PointService();

        sce.getServletContext()
                .addServlet("pointServlet", new PointServlet(pointService, objectMapper))
                .addMapping("/points");
    }
}