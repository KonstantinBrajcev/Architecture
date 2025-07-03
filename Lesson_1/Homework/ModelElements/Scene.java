package Lesson_1.Homework.ModelElements;

import java.util.ArrayList;
import java.util.Collection;

public class Scene {
    private int id;
    private final Collection<PoligonalModel> models;
    private final Collection<Flash> flashes;
    private final Collection<Camera> cameras;

    public Scene(int id) {
        this.id = id;
        this.models = new ArrayList<>();
        this.flashes = new ArrayList<>();
        this.cameras = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public Collection<PoligonalModel> getModels() {
        return models;
    }

    public Collection<Flash> getFlashes() {
        return flashes;
    }

    public Collection<Camera> getCameras() {
        return cameras;
    }

    public boolean addFlash(Flash flash) {
        return flashes.add(flash);
    }

    public boolean addPoligonalModel(PoligonalModel model) {
        return models.add(model);
    }

    public boolean addCamera(Camera camera) {
        return cameras.add(camera);
    }
}