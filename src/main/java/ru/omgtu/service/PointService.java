package ru.omgtu.service;

import ru.omgtu.model.Point;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class PointService {

    public List<Point> loadPointsFromFile(String fileName) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new FileNotFoundException("Файл не найден: " + fileName);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return reader.lines()
                    .map(line -> line.split("\\s+"))
                    .map(parts -> new Point(Double.parseDouble(parts[0]), Double.parseDouble(parts[1])))
                    .collect(Collectors.toList());
        }
    }

    public List<Point> filterPoints(List<Point> points, double A, double B, double C, double maxDist) {
        return points.stream()
                .filter(p -> distanceToLine(p.getX(), p.getY(), A, B, C) <= maxDist)
                .collect(Collectors.toList());
    }

    private double distanceToLine(double x, double y, double A, double B, double C) {
        return Math.abs(A * x + B * y + C) / Math.sqrt(A * A + B * B);
    }
}

