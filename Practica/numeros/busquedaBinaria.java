import java.util.Arrays;
import java.util.Random;

public class busquedaBinaria {

    public static int[] generarArreglo(int size) {
        Random rand = new Random();
        int[] arreglo = new int[size];
        for (int i = 0; i < size; i++) {
            arreglo[i] = rand.nextInt(100000);
        }
        return arreglo;
    }

    public static int busquedaSecuencial(int[] arreglo, int objetivo) {
        for (int i = 0; i < arreglo.length; i++) {
            if (arreglo[i] == objetivo) {
                return i; 
            }
        }
        return -1; 
    }

    public static int busquedaBinaria(int[] arreglo, int objetivo) {
        int low = 0;
        int high = arreglo.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arreglo[mid] == objetivo) {
                return mid; 
            } else if (arreglo[mid] < objetivo) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1; 
    }

    public static void main(String[] args) {
        int[] tamaños = {10000, 20000, 25000};
        Random rand = new Random();

        System.out.println("Tiempos de ejecución para cada algoritmo de búsqueda:");

        for (int size : tamaños) {
            int[] arreglo = generarArreglo(size);
            int objetivo = rand.nextInt(100000);

            long inicio = System.nanoTime();
            busquedaSecuencial(arreglo, objetivo);
            long fin = System.nanoTime();
            long tiempoSecuencial = fin - inicio;

            Arrays.sort(arreglo);
            inicio = System.nanoTime();
            busquedaBinaria(arreglo, objetivo);
            fin = System.nanoTime();
            long tiempoBinaria = fin - inicio;

            System.out.println("Arreglo de tamaño " + size + ":");
            System.out.println("Tiempo de búsqueda secuencial: " + tiempoSecuencial + " ns");
            System.out.println("Tiempo de búsqueda binaria: " + tiempoBinaria + " ns");
            System.out.println("----------------------------------------");
        }
    }
}
