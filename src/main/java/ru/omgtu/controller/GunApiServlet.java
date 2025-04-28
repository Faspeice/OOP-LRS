package ru.omgtu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.omgtu.model.Gun;
import ru.omgtu.model.ProductStatus;
import ru.omgtu.service.GunService;

import java.io.IOException;

public class GunApiServlet extends HttpServlet {
    private final ObjectMapper objectMapper;
    private final GunService gunService;

    public GunApiServlet(GunService gunService, ObjectMapper objectMapper) {
        this.gunService = gunService;
        this.objectMapper = objectMapper;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            objectMapper.writeValue(resp.getWriter(), gunService.getAllGuns());
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Ошибка при получении списка оружия: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        try {
            Gun newGun = objectMapper.readValue(req.getReader(), Gun.class);
            newGun.setStatus(ProductStatus.valueOf(newGun.getStatus().name()));
            gunService.addGun(newGun);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            objectMapper.writeValue(resp.getWriter(), newGun);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Ошибка при создании оружия: " + e.getMessage());
        }
    }
} 