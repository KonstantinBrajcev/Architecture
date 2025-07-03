package Lesson_1.Homework.ModelElements;

import java.util.ArrayList;
import java.util.Collection;

public class PoligonalModel {
    private Collection<Poligon> poligons;
    private Collection<Texture> textures;

//    private Collection<Poligon> poligons = new ArrayList<>();
//
//    private Collection<Texture> textures = new ArrayList<>();
//
//    public Collection<Poligon> getPoligons() {
//        return poligons;
//    }
//
//    public Collection<Texture> getTextures() {
//        return textures;
//    }
    public PoligonalModel(Collection<Texture> textures) {
        this.textures = textures;
        this.poligons = new ArrayList<>();
        this.poligons.add(new Poligon());
    }

    public PoligonalModel() {
        this(new ArrayList<>());
    }

    public Collection<Poligon> getPoligons() {
        return poligons;
    }

    public Collection<Texture> getTextures() {
        return textures;
    }

    public void addPoligon(Poligon poligon) {
        poligons.add(poligon);
    }

    public void addTexture(Texture texture) {
        textures.add(texture);
    }
}