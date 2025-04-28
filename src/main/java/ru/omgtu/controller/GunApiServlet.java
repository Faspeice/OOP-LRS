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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GunApiServlet extends HttpServlet {
    private final ObjectMapper objectMapper;
    private final GunService gunService;
    private static final Pattern ID_PATTERN = Pattern.compile("/api/guns/(\\d+)");

    public GunApiServlet(GunService gunService, ObjectMapper objectMapper) {
        this.gunService = gunService;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            String pathInfo = req.getPathInfo();
            if (pathInfo == null || pathInfo.equals("/")) {
                objectMapper.writeValue(resp.getWriter(), gunService.getAllGuns());
            } else {
                Matcher matcher = ID_PATTERN.matcher(req.getRequestURI());
                if (matcher.find()) {
                    Long id = Long.parseLong(matcher.group(1));
                    Gun gun = gunService.getGunById(id);
                    if (gun != null) {
                        objectMapper.writeValue(resp.getWriter(), gun);
                    } else {
                        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        resp.getWriter().write("Пушка с ID " + id + " не найдена");
                    }
                } else {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.getWriter().write("Неверный формат запроса");
                }
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Ошибка при обработке запроса: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String pathInfo = req.getPathInfo();
            if (pathInfo == null || pathInfo.equals("/")) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("ID пушки не указан");
                return;
            }

            Matcher matcher = ID_PATTERN.matcher(req.getRequestURI());
            if (matcher.find()) {
                Long id = Long.parseLong(matcher.group(1));
                Gun updatedGun = objectMapper.readValue(req.getReader(), Gun.class);
                updatedGun.setId(id);
                updatedGun.setStatus(ProductStatus.valueOf(updatedGun.getStatus().name()));
                
                if (gunService.updateGun(updatedGun)) {
                    objectMapper.writeValue(resp.getWriter(), updatedGun);
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().write("Пушка с ID " + id + " не найдена");
                }
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("Неверный формат запроса");
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Ошибка при обновлении оружия: " + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String pathInfo = req.getPathInfo();
            if (pathInfo == null || pathInfo.equals("/")) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("ID пушки не указан");
                return;
            }

            Matcher matcher = ID_PATTERN.matcher(req.getRequestURI());
            if (matcher.find()) {
                Long id = Long.parseLong(matcher.group(1));
                if (gunService.deleteGun(id)) {
                    resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().write("Пушка с ID " + id + " не найдена");
                }
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("Неверный формат запроса");
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Ошибка при удалении оружия: " + e.getMessage());
        }
    }
} 