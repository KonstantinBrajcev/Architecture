package Lesson_1.Homework.ModelElements;

import java.util.ArrayList;
import java.util.List;

public class Flash {
    private Point3D location;
    private Angle3D angle;
    private Color color;

    private float power;

    private final List<Scene> scenes = new ArrayList<>();

    public Flash(Point3D location, Angle3D angle, Color color, float power) {
        this.location = location;
        this.angle = angle;
        this.color = color;
        this.power = power;
    }

    public void rotate(Angle3D angle) {
        this.angle = angle;
    }

    public void move(Point3D location) {
        this.location = location;
    }

    public List<Scene> getScenes() {
        return scenes;
    }

    public Point3D getLocation() {
        return location;
    }

    public Angle3D getAngle() {
        return angle;
    }

    public Color getColor() {
        return color;
    }

    public float getPower() {
        return power;
    }
}