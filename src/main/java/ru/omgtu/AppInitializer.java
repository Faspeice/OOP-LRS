package ru.omgtu;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import ru.omgtu.controller.*;


public class AppInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        sce.getServletContext()
                .addServlet("aboutServlet", new AboutServlet())
                .addMapping("/about");

        sce.getServletContext()
                .addServlet("contactServlet", new ContactServlet())
                .addMapping("/contact");

        sce.getServletContext()
                .addServlet("feedbackServlet", new FeedbackServlet())
                .addMapping("/feedback");

        sce.getServletContext()
                .addServlet("homeServlet", new HomeServlet())
                .addMapping("/home");

        sce.getServletContext()
                .addServlet("productServlet", new ProductsServlet())
                .addMapping("/products");
    }
}