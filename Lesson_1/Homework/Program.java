package Lesson_1.Homework;

import Lesson_1.Homework.InMemoryModel.*;
import Lesson_1.Homework.ModelElements.*;

import java.util.ArrayList;
import java.util.List;

public class Program {

    public static void main(String[] args) {

        // 1. Создаем наблюдателей
        ModelChangedObserver observer1 = new Observer1();
        ModelChangedObserver observer2 = new Observer2();

        // 2. Инициализируем хранилище моделей
        ModelStore store = new ModelStore();
        store.registerModelChanger(observer1);
        store.registerModelChanger(observer2);

        // 3. Создаем текстуры
        Texture woodTexture = new Texture("Дерево");
        Texture metalTexture = new Texture("Металл");

        // 4. Создаем полигональную модель с текстурами
        List<Texture> textures = new ArrayList<>();
        textures.add(woodTexture);
        textures.add(metalTexture);

        PoligonalModel model = new PoligonalModel(textures);

        // 5. Добавляем полигон с точками
        Point3D point1 = new Point3D(0, 0, 0);
        Point3D point2 = new Point3D(1, 0, 0);
        Point3D point3 = new Point3D(0, 1, 0);

        List<Point3D> points = new ArrayList<>();
        points.add(point1);
        points.add(point2);
        points.add(point3);

        Poligon poligon = new Poligon(points);
        model.addPoligon(poligon);

        // 6. Добавляем модель в хранилище (должен сработать Observer)
        store.addModel(model);

        // 7. Создаем камеру
        Point3D cameraLocation = new Point3D(10, 10, 10);
        Vector3D vector1 = new Vector3D(cameraLocation);
        Vector3D vector2 = new Vector3D(new Point3D(0, 0, 0));
        Angle3D cameraAngle = new Angle3D(vector1, vector2);

        Camera camera = new Camera(cameraLocation, cameraAngle);
        store.addCamera(camera);

        // 8. Создаем вспышку
        Flash flash = new Flash(
                new Point3D(5, 5, 5),
                new Angle3D(vector1, vector2),
                new Color(),
                100.0f
        );
        store.addFlash(flash);

        // 9. Создаем сцену
        List<PoligonalModel> sceneModels = new ArrayList<>();
        sceneModels.add(model);

        List<Flash> sceneFlashes = new ArrayList<>();
        sceneFlashes.add(flash);

        List<Camera> sceneCameras = new ArrayList<>();
        sceneCameras.add(camera);

        Scene scene = store.createScene(1, sceneModels, sceneFlashes, sceneCameras);

        // 10. Проверяем, что сцена создана
        System.out.println("Сцена создана с ID: " + scene.getId());
        System.out.println("Количество моделей в сцене: " + scene.getModels().size());
        System.out.println("Количество вспышек в сцене: " + scene.getFlashes().size());
    }
}