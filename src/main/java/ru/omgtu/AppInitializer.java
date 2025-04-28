package ru.omgtu;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.omgtu.controller.GunServlet;
import ru.omgtu.controller.GunApiServlet;
import ru.omgtu.controller.AboutServlet;
import ru.omgtu.controller.ContactServlet;
import ru.omgtu.controller.FeedbackServlet;
import ru.omgtu.controller.HomeServlet;
import ru.omgtu.controller.ProductsServlet;
import ru.omgtu.service.GunService;
import ru.omgtu.repo.GunJsonRepository;
import ru.omgtu.factory.GunFactory;

@WebListener
public class AppInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        
        GunJsonRepository repository = new GunJsonRepository();
        GunService gunService = new GunService(repository);
        
        if (gunService.getAllGuns().isEmpty()) {
            GunFactory.createInitialGuns().forEach(gunService::addGun);
        }
        
        GunServlet gunServlet = new GunServlet();
        context.addServlet("GunServlet", gunServlet).addMapping("/guns");
        
        GunApiServlet gunApiServlet = new GunApiServlet(gunService);
        context.addServlet("GunApiServlet", gunApiServlet).addMapping("/api/guns");

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

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}