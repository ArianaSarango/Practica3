package controller.Dao;

import models.Familia;

import java.util.Arrays;
import java.util.Comparator;

import controller.Dao.implement.AdapterDao;
import controller.tda.list.LinkedList;
import controller.tda.list.ListEmptyException;

public class FamiliaDao extends AdapterDao<Familia> {
    private Familia familia = new Familia();
    private LinkedList<Familia> listAll;

    public FamiliaDao() {
        super(Familia.class);
    }

    public Familia getFamilia() { 
        if (familia == null) {
            familia = new Familia(); 
        }
        return this.familia; 
    }

    public void setFamilia(Familia familia) { 
        this.familia = familia; 
    }

    public LinkedList getlistAll() { 
        if (listAll == null) { 
            this.listAll = listAll(); 
        }
        return listAll;
    }

    public Boolean save() throws Exception { 
        Integer id = getlistAll().getSize() + 1; 
        familia.setId(id); 
        this.persist(this.familia); 
        this.listAll = listAll(); 
        return true; 
    }

    public Boolean update() throws Exception { 
        this.merge(getFamilia(), getFamilia().getId() - 1);
        this.listAll = listAll();
        return true;
    }

    public Boolean delete(int index) throws Exception { 
        this.supreme(index);
        this.listAll = listAll();
        return true; 
    }

    public int contarFamiliasConGenerador() {
        int contador = 0;
        LinkedList<Familia> familias = listAll(); 
        Familia[] familiasArray = familias.toArray(); 

        for (Familia familia : familiasArray) { 
            if (familia.getTieneGenerador()) { 
                contador++;
            }
        }
        return contador;
    }

    public LinkedList<Familia> order(String attribute, Integer type, Integer metodo) {
        try {
            getlistAll();
            System.out.println("Lista antes de ordenar " + listAll.toString());
            switch (metodo) {
                case 1:
                    System.out.println("Método de ordenación Lineal ");
                    return this.listAll.order(attribute, type);

                case 2:
                    System.out.println("Método de ordenación shellSort ");
                    return this.listAll.shellSort(attribute, type);

                case 3:
                    System.out.println("Método de ordenación quickSort " );
                    return this.listAll.quickSort(attribute, type);

                default:
                    System.out.println("Método de ordenación mergeSort ");
                    return this.listAll.mergeSort(attribute, type);
            }

        } catch (Exception e) {
            return null;
        }
    }

    public LinkedList<Familia> buscar_Apellido_Paterno(String texto) {
        System.out.println("Se está buscando por Metodo Lineal");
        LinkedList<Familia> lista = new LinkedList<>();
        LinkedList<Familia> listita = listAll();
        if (!listita.isEmpty()) {
            Familia[] aux = listita.toArray();
            for (int i = 0; i < aux.length; i++) {
                if (aux[i].getApellidoPaterno().toLowerCase().startsWith(texto.toLowerCase())) {
                    lista.add(aux[i]);

                }
            }
        }
        return lista;
    }

    public LinkedList<Familia> buscar_Apellido_Materno(String texto) {
        System.out.println("Se está buscando por Metodo Lineal");
        LinkedList<Familia> lista = new LinkedList<>();
        LinkedList<Familia> listita = listAll();
        if (!listita.isEmpty()) {
            Familia[] aux = listita.toArray();
            for (int i = 0; i < aux.length; i++) {
                if (aux[i].getApellidoMaterno().toLowerCase().startsWith(texto.toLowerCase())) {
                    lista.add(aux[i]);

                }
            }
        }
        return lista;
    }

    public LinkedList<Familia> buscar_Apellido_Materno_Binaria(String texto) {

        System.out.println("Se está buscando por Metodo Lineal-Binario AM");
        LinkedList<Familia> resultados = new LinkedList<>();

        try {
            getlistAll();
            listAll.quickSort("apellidoMaterno", 0);

            int low = 0;
            int high = listAll.getSize() - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                Familia midFamilia = listAll.get(mid);
                String apellidoMaterno = midFamilia.getApellidoMaterno().toLowerCase();

                if (apellidoMaterno.startsWith(texto.toLowerCase())) {
                    int left = mid;
                    int right = mid;
                    while (left >= 0
                            && listAll.get(left).getApellidoMaterno().toLowerCase().startsWith(texto.toLowerCase())) {
                        resultados.addF(listAll.get(left--));
                    }
                    while (right < listAll.getSize()
                            && listAll.get(right).getApellidoMaterno().toLowerCase().startsWith(texto.toLowerCase())) {
                        if (right != mid)
                            resultados.add(listAll.get(right));
                        right++;
                    }
                    break;
                } else if (apellidoMaterno.compareTo(texto.toLowerCase()) < 0) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            
            return resultados.quickSort("id", 0);

        } catch (Exception e) {
            System.out.println(e);
        }
        return resultados;

    }

    public LinkedList<Familia> buscar_Canton_LB(String texto) {

        System.out.println("Se está buscando por Metodo Lineal-Binario C");
        LinkedList<Familia> resultados = new LinkedList<>();

        try {
            getlistAll();
            listAll.mergeSort("canton", 0);
            int low = 0;
            int high = listAll.getSize() - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                Familia midFamilia = listAll.get(mid);
                String canton = midFamilia.getCanton().toLowerCase();

                if (canton.startsWith(texto.toLowerCase())) {
                    int left = mid;
                    int right = mid;
                    while (left >= 0
                            && listAll.get(left).getCanton().toLowerCase().startsWith(texto.toLowerCase())) {
                        resultados.addF(listAll.get(left--));
                    }
                    while (right < listAll.getSize()
                            && listAll.get(right).getCanton().toLowerCase().startsWith(texto.toLowerCase())) {
                        if (right != mid)
                            resultados.add(listAll.get(right));
                        right++;
                    }
                    break;
                } else if (canton.compareTo(texto.toLowerCase()) < 0) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            return resultados.quickSort("id", 0);

        } catch (Exception e) {
            System.out.println("Error durante la búsqueda: " + e.getMessage());
        }
        return resultados;
    }

    public Familia buscar_Canton_Binaria(String texto) {
        System.out.println("Se está buscando por Método Binario puro");
    
        try {
            getlistAll(); 
            listAll.mergeSort("canton", 0); 
    
            int low = 0;
            int high = listAll.getSize() - 1;
    
            while (low <= high) {
                int mid = (low + high) / 2;
                Familia midFamilia = listAll.get(mid);
                String canton = midFamilia.getCanton().toLowerCase();
    
                if (canton.startsWith(texto.toLowerCase())) {
                    return midFamilia; 
                } else if (canton.compareTo(texto.toLowerCase()) < 0) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        } catch (Exception e) {
            System.out.println("Se está durante la búsqueda: " + e.getMessage());
        }
    
        return null;
    }
    

    public LinkedList<Familia> buscar_Apellido_Paterno_Binaria(String texto) {

        System.out.println("Se está buscando por Metodo Binario AP");
        LinkedList<Familia> resultados = new LinkedList<>();

        try {
            this.getlistAll();
            System.err.println("Se hace quickSort FamiliaDao");
            listAll.quickSort("apellidoPaterno", 0);
            System.err.println("Se sale quickSort FamiliaDao");

            int low = 0;
            int high = listAll.getSize() - 1;

            while (low <= high) {
                int mid = (low + high) / 2;
                Familia midFamilia = listAll.get(mid);
                String apellidoPaterno = midFamilia.getApellidoPaterno().toLowerCase();

                if (apellidoPaterno.startsWith(texto.toLowerCase())) {
                    int left = mid;
                    int right = mid;
                    while (left >= 0
                            && listAll.get(left).getApellidoPaterno().toLowerCase().startsWith(texto.toLowerCase())) {
                        resultados.addF(listAll.get(left--));
                    }
                    while (right < listAll.getSize()
                            && listAll.get(right).getApellidoPaterno().toLowerCase().startsWith(texto.toLowerCase())) {
                        if (right != mid)
                            resultados.add(listAll.get(right));
                        right++;
                    }
                    break;
                } else if (apellidoPaterno.compareTo(texto.toLowerCase()) < 0) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            return resultados.quickSort("id", 0);

        } catch (Exception e) {
            // TODO: handle exception
        }
        
        return resultados;

    }

    public Familia buscar_Id_Binaria(Integer id) {
        System.out.println("Se está buscando por Metodo Binario Id");

        try {
            this.getlistAll();
            listAll.quickSort("id", 0);
            Familia[] listaOrdenada = listAll.toArray();
            if (listaOrdenada == null || listaOrdenada.length == 0) {
                return null;
            }

            int izquierda = 0;
            int derecha = listaOrdenada.length - 1;

            while (izquierda <= derecha) {
                int medio = izquierda + (derecha - izquierda) / 2;
                Integer idMedio = listaOrdenada[medio].getId();

                if (idMedio.equals(id)) {
                    return listaOrdenada[medio];
                }

                if (idMedio < id) {
                    izquierda = medio + 1;
                } else {
                    derecha = medio - 1;
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

        return null;

    }

    public LinkedList<Familia> buscar_Integrantes_LinealBinaria(int integrantes) {
        System.out.println("Se está buscando por Método Binario para integrantes");
        LinkedList<Familia> resultados = new LinkedList<>();
        this.getlistAll();
        try {
            listAll.quickSort("integrantes", 0);
            Familia[] listaOrdenada = listAll.toArray();

            if (listaOrdenada == null || listaOrdenada.length == 0) {
                return resultados;
            }

            int izquierda = 0;
            int derecha = listaOrdenada.length - 1;

            while (izquierda <= derecha) {
                int medio = izquierda + (derecha - izquierda) / 2;
                int integrantesMedio = listaOrdenada[medio].getIntegrantes();

                if (integrantesMedio == integrantes) {
                    resultados.add(listaOrdenada[medio]);

                    int left = medio - 1;
                    while (left >= 0 && listaOrdenada[left].getIntegrantes() == integrantes) {
                        resultados.addF(listaOrdenada[left]);
                        left--;
                    }

                    int right = medio + 1;
                    while (right < listaOrdenada.length && listaOrdenada[right].getIntegrantes() == integrantes) {
                        resultados.add(listaOrdenada[right]);
                        right++;
                    }
                    break;
                }

                if (integrantesMedio < integrantes) {
                    izquierda = medio + 1;
                } else {
                    derecha = medio - 1;
                }
            }

            return resultados.quickSort("id", 0);

        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        return resultados;
    }

    public Familia buscar_Integrantes_Binaria(Integer integrantes) {
        System.out.println("Se está buscando por Metodo Binario integrantes");

        try {
            this.getlistAll();
            listAll.quickSort("integrantes", 0);
            Familia[] listaOrdenada = listAll.toArray();
            if (listaOrdenada == null || listaOrdenada.length == 0) {
                return null; 
            }

            int izquierda = 0;
            int derecha = listaOrdenada.length - 1;

            while (izquierda <= derecha) {
                int medio = izquierda + (derecha - izquierda) / 2;
                Integer integrantesMedio = listaOrdenada[medio].getIntegrantes();

                if (integrantesMedio.equals(integrantes)) {
                    return listaOrdenada[medio]; 
                }

                if (integrantesMedio < integrantes) {
                    izquierda = medio + 1; 
                } else {
                    derecha = medio - 1; 
                }
            }

        } catch (Exception e) {
            System.out.println("Error " + e);
        }

        return null;

    }

    public Familia buscar_Id(Integer id) {
        Familia familia = new Familia();
        LinkedList<Familia> listita = listAll();
        if (!listita.isEmpty()) {
            Familia[] aux = listita.toArray();
            for (int i = 0; i < aux.length; i++) {

                if (aux[i].getId().equals(id)) {
                    familia = aux[i];
                    System.out.println(familia.getApellidoPaterno());
                    break;
                }
            }
        }
        return familia;
    }

    public LinkedList<Familia> buscar_Integrantes_LinealBinaria_P(LinkedList<Familia> lista, int integrantes) {
        LinkedList<Familia> resultados = new LinkedList<>();
        try {
            Familia[] listaOrdenada = lista.toArray();

            if (listaOrdenada == null || listaOrdenada.length == 0) {
                return resultados;
            }

            int izquierda = 0;
            int derecha = listaOrdenada.length - 1;

            while (izquierda <= derecha) {
                int medio = izquierda + (derecha - izquierda) / 2;
                int integrantesMedio = listaOrdenada[medio].getIntegrantes();

                if (integrantesMedio == integrantes) {
                    resultados.add(listaOrdenada[medio]);

                    int left = medio - 1;
                    while (left >= 0 && listaOrdenada[left].getIntegrantes() == integrantes) {
                        resultados.addF(listaOrdenada[left]);
                        left--;
                    }

                    int right = medio + 1;
                    while (right < listaOrdenada.length && listaOrdenada[right].getIntegrantes() == integrantes) {
                        resultados.add(listaOrdenada[right]);
                        right++;
                    }
                    break;
                }

                if (integrantesMedio < integrantes) {
                    izquierda = medio + 1;
                } else {
                    derecha = medio - 1;
                }
            }

            return resultados.quickSort("id", 0);

        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        return resultados;
    }

    public LinkedList<Familia> buscar_Integrantes_LinealBinaria_PL(LinkedList <Familia> listita, Integer valor) {
        System.out.println("Se está buscando por Metodo Lineal");
        LinkedList<Familia> lista = new LinkedList<>();
        if (!listita.isEmpty()) {
            Familia[] aux = listita.toArray();
            for (int i = 0; i < aux.length; i++) {
                if (aux[i].getIntegrantes() == valor) {
                    lista.add(aux[i]);

                }
            }
        }
        return lista;
    }
}