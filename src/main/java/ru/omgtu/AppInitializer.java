package ru.omgtu;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.omgtu.controller.GunServlet;
import ru.omgtu.controller.GunApiServlet;
import ru.omgtu.controller.AboutServlet;
import ru.omgtu.controller.ContactServlet;
import ru.omgtu.controller.FeedbackServlet;
import ru.omgtu.controller.HomeServlet;
import ru.omgtu.controller.ProductsServlet;
import ru.omgtu.repo.GunJdbcDao;
import ru.omgtu.service.GunService;

import ru.omgtu.util.ConnectionProvider;

import java.sql.SQLException;

@WebListener
public class AppInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            GunJdbcDao gunDao = new GunJdbcDao(ConnectionProvider.getConnection());
            GunService gunService = new GunService(gunDao);

            GunServlet gunServlet = new GunServlet();
            context.addServlet("GunServlet", gunServlet).addMapping("/guns");
        
            GunApiServlet gunApiServlet = new GunApiServlet(gunService, objectMapper);
            context.addServlet("GunApiServlet", gunApiServlet).addMapping("/api/guns", "/api/guns/*");

            context
                    .addServlet("aboutServlet", new AboutServlet())
                    .addMapping("/about");

            context
                    .addServlet("contactServlet", new ContactServlet())
                    .addMapping("/contact");

            context
                    .addServlet("feedbackServlet", new FeedbackServlet())
                    .addMapping("/feedback");

            context
                    .addServlet("homeServlet", new HomeServlet())
                    .addMapping("/home");

            context
                    .addServlet("productServlet", new ProductsServlet())
                    .addMapping("/products");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}