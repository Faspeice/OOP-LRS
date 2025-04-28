package ru.omgtu;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.omgtu.controller.*;
import ru.omgtu.factory.GunFactory;
import ru.omgtu.repo.GunJsonRepository;
import ru.omgtu.service.GunService;

@WebListener
public class AppInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        ObjectMapper objectMapper = new ObjectMapper();
        
        GunJsonRepository repository = new GunJsonRepository(objectMapper);
        GunService gunService = new GunService(repository);
        
        if (gunService.getAllGuns().isEmpty()) {
            GunFactory.createInitialGuns().forEach(gunService::addGun);
        }
        
        GunServlet gunServlet = new GunServlet(gunService, objectMapper);
        context.addServlet("GunServlet", gunServlet).addMapping("/guns");

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
    }

}