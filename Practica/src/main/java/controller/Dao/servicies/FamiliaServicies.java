package controller.Dao.servicies;

import controller.tda.list.LinkedList;
import models.Familia;
import controller.Dao.FamiliaDao;

public class FamiliaServicies {
    private FamiliaDao obj;

    public FamiliaServicies() {
        obj = new FamiliaDao();
    }

    public Boolean update() throws Exception {
        return obj.update();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public LinkedList<Familia> listAll() {
        return obj.getlistAll();
    }

    public Familia getFamilia() {
        return obj.getFamilia();
    }

    public void setFamilia(Familia familia) {
        obj.setFamilia(familia);
    }

    public Familia get(Integer id) throws Exception {
        return obj.get(id);
    }

    public Boolean delete(int index) throws Exception {
        return obj.delete(index);
    }

    public int contarFamiliasConGenerador() {
        return obj.contarFamiliasConGenerador();
    }

    public LinkedList<Familia> order(String attribute, Integer type, Integer metodo) {
        return obj.order(attribute, type, metodo);
    }

    public LinkedList<Familia> buscar_Apellido_Paterno(String texto) {
        return obj.buscar_Apellido_Paterno_Binaria(texto);
    }

    public LinkedList<Familia> buscar_Apellido_Materno(String texto) {
        return obj.buscar_Apellido_Materno_Binaria(texto);
    }

    public LinkedList<Familia> buscar_Canton(String texto) {
        return obj.buscar_Canton_LB(texto);
    }

    public Familia buscar_Id(Integer id) {
        return obj.buscar_Id_Binaria(id);
    }

    public LinkedList<Familia> buscar_Integrantes(Integer integrantes) {
        return obj.buscar_Integrantes_LinealBinaria(integrantes);
    }

    public Familia buscar_Integrantes_Binario(Integer integrantes) {
        return obj.buscar_Integrantes_Binaria(integrantes);
    }

    public LinkedList<Familia> buscar_Integrantes_Prueba(LinkedList<Familia> lista, Integer integrantes) {
        return obj.buscar_Integrantes_LinealBinaria_P(lista, integrantes);
    }

    public LinkedList<Familia> buscar_Integrantes_Prueba_Lineal(LinkedList<Familia> lista, Integer integrantes) {
        return obj.buscar_Integrantes_LinealBinaria_PL(lista, integrantes);
    }
}