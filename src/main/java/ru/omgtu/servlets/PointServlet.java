package ru.omgtu.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;


import ru.omgtu.model.Point;
import ru.omgtu.service.PointService;


import java.io.IOException;


@WebServlet("/points")
public class PointServlet extends HttpServlet {
    private final PointService pointService = new PointService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        try {
            double A = Double.parseDouble(request.getParameter("A"));
            double B = Double.parseDouble(request.getParameter("B"));
            double C = Double.parseDouble(request.getParameter("C"));
            double maxDist = Double.parseDouble(request.getParameter("maxDist"));

            List<Point> points = pointService.loadPointsFromFile("points.txt");
            List<Point> filteredPoints = pointService.filterPoints(points, A, B, C, maxDist);

            response.setContentType("application/json");
            response.getWriter().write(objectMapper.writeValueAsString(filteredPoints));
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Некорректные данные: " + e.getMessage());
        }
    }
}


