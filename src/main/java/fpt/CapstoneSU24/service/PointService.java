package fpt.CapstoneSU24.service;

import fpt.CapstoneSU24.dto.Point;
import fpt.CapstoneSU24.model.ItemLog;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PointService {
    // Ham tinh ra diem tiep theo
    public  double interpolate(List<Point> points, double x) {
        int n = points.size();
        double result = 0;
        for (int i = 0; i < n; i++) {
            double term = points.get(i).getY();
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    term *= (x - points.get(j).getX()) / (points.get(i).getX() - points.get(j).getX());
                }
            }
            result += term;
        }

        return result;
    }
    // Ham kiem tra co nam tren 1 do thi khong
    public boolean isPointOnCurve(List<Point> points, Point p) {
        double interpolatedY = interpolate(points, p.getX());
        return Math.abs(interpolatedY - p.getY()) < 1e-6; // Kiểm tra với sai số nhỏ
    }

    // Ham ramdom ra 1 diem
//    public List<Point>  initializeData() {
//        Random random = new Random();
//        List<Point> points = new ArrayList<>();
//        // Tạo hai điểm ngẫu nhiên
//        points.add(new Point(random.nextInt(), random.nextInt()));
//        points.add(new Point(random.nextInt(), random.nextInt()));
//
//        return points;
//    }

    public Point randomPoint() {
        Random random = new Random();
        double x = roundToThreeDecimalPlaces(random.nextDouble());
        double y = roundToThreeDecimalPlaces(random.nextDouble());
        return new Point(x, y);
    }

    private double roundToThreeDecimalPlaces(double value) {
        return Math.round(value * 1000.0) / 1000.0;
    }
    //ham random ra 1 toa do X
    public double  generatedoubleX() {
        Random random = new Random();
        return random.nextInt();
    }
    public  Point getPoint(String point) {
        point = point.substring(1, point.length() - 1);
        String[] po = point.split(";");

        double x = Double.parseDouble(po[0]);
        double y = Double.parseDouble(po[1]);
        return new Point(x, y);
    }
    public boolean arePointsOnCurve(List<ItemLog> pointLogs) {
        List<Point> points = new ArrayList<>();
        for (int i = 1; i < pointLogs.size() - 1; i++) {
            Point point = getPoint(pointLogs.get(i).getPoint());
            points.add(point);
        }

        Point startPoint = getPoint(pointLogs.get(0).getPoint());
        return isPointOnCurve(points, startPoint);
    }
    public List<Point> getPointList(List<ItemLog> pointLogs) {
        List<Point> points = new ArrayList<>();
        for (int i = 1; i < pointLogs.size() - 1; i++) {
            Point point = getPoint(pointLogs.get(i).getPoint());
            points.add(point);
        }
        return points;
    }


}
