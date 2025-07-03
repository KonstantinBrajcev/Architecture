package Lesson_1.Homework.ModelElements;

import java.util.ArrayList;
import java.util.Collection;

public class Poligon {
    private Collection<Point3D> points;

    public Poligon(Collection<Point3D> points) {
        this.points = points;
    }

    public Poligon() {
        this(new ArrayList<>());
    }

    public Collection<Point3D> getPoints() {
        return points;
    }

    public boolean addPoint(Point3D point) {
        return points.add(point);
    }

    public boolean removePoint(Point3D point) {
        return points.remove(point);
    }
}