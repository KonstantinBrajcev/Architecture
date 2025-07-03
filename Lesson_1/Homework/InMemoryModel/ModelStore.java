package Lesson_1.Homework.InMemoryModel;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import Lesson_1.Homework.ModelElements.*;
//import Lesson_1.Homework.ModelElements.Camera;
//import Lesson_1.Homework.ModelElements.Flash;
//import Lesson_1.Homework.ModelElements.PoligonalModel;
//import Lesson_1.Homework.ModelElements.Scene;

/**
 * Хранилище 3D-элементов
 * TODO: Доработать самостоятельно в рамках домашней работы
 */
public class ModelStore implements ModelChanger {
    private final Collection<PoligonalModel> models = new ArrayList<>();
    private final Collection<ModelChangedObserver> observers = new ArrayList<>();
    private final List<Flash> flashes = new ArrayList<>();
    private final Collection<Camera> cameras = new ArrayList<>();
    private final Collection<Scene> scenes = new ArrayList<>();

    @Override
    public void registerModelChanger(ModelChangedObserver o) {
        observers.add(o);
    }

    @Override
    public void removeModelChanger(ModelChangedObserver o) {
        observers.remove(o);
    }

    /**
     * Нотификация изменений на уровне хранилища
     */
    private void notifyChange() {
        for (ModelChangedObserver observer : observers) {
            observer.applyUpdateModel();
        }
    }

    public void addModel(PoligonalModel poligonalModel) {
        models.add(poligonalModel);
        notifyChange();
    }

    public void addFlash(Flash flash) {
        flashes.add(flash);
        notifyChange();
    }

    public void addCamera(Camera camera) {
        cameras.add(camera);
        notifyChange();
    }

    public Scene createScene(int id, Collection<PoligonalModel> models, Collection<Flash> flashes, Collection<Camera> cameras) {
        Scene scene = new Scene(id);
        scene.getModels().addAll(models);
        scene.getFlashes().addAll(flashes);
        scenes.add(scene);
        notifyChange();
        return scene;
    }

    public Scene getScene(int id) {
        for (Scene scene : scenes) {
            if (scene.getId() == id) {
                return scene;
            }
        }
        return null;
    }
}