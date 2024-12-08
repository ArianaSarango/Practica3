package controller.Dao.servicies;

import controller.tda.list.LinkedList;
import models.Generador;
import controller.Dao.GeneradorDao;

public class GeneradorServicies {
    private GeneradorDao obj;

    public GeneradorServicies() {
        obj = new GeneradorDao();
    }

    public Boolean update() throws Exception {
        return obj.update();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public LinkedList<Generador> listAll() {
        return obj.getlistAll();
    }

    public Generador getGenerador() {
        return obj.getGenerador();
    }

    public void setGenerador(Generador generador) {
        obj.setGenerador(generador);
    }

    public Generador get(Integer id) throws Exception {
        return obj.get(id);
    }

    public Boolean delete(int index) throws Exception {
        return obj.delete(index);
    }
}