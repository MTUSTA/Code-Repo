
import java.util.Arrays;
import java.util.Random;

public class Main {
    // Generate n integer object
    public static int[] int_array_generator(int element, int range) {
        Random r = new Random();
        // generate array and size = elemenst and between 0 -> range
        return r.ints(element, 0, range).toArray();
    }

    public static int[] ascending_array(int element) {
        int[] arr1 = new int[element];
        for (int i = 0; i < element; i++) {
            arr1[i] = i;
        }
        return arr1;
    }

    public static int[] descending_array(int element) {
        int[] arr1 = new int[element];
        for (int i = 0; i < element; i++) {
            arr1[i] = element - i;
        }
        return arr1;
    }

    public static void Average_Case_Process(int type) throws Exception {
        // element size array
        int[] elements_arr = {32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768};
        // init array. it will sort
        int[] random_arr = null;
        for (int element_size : elements_arr) {

            // random integer array generator
            if (type == 0) {
                int range = 10000;
                System.out.println("Integer Between 0 and " + range + ". Array element size: " + element_size);
                random_arr = int_array_generator(element_size, range);
            } else if (type == 1) {
                System.out.println("Array element size: " + element_size + ", All elements = 0");
                random_arr = new int[element_size];
                Arrays.fill(random_arr, 0);
            } else if (type == 2) {
                System.out.println("Array element size: " + element_size + " and All elements are descending");
                random_arr = descending_array(element_size);
            } else if (type == 3) {
                System.out.println("Array element size: " + element_size + " and All elements are ascending");
                random_arr = ascending_array(element_size);
            } else {
                throw new Exception("type is wrong! Enter the type between 0-3");
            }
            int[] copy_of_random_arr = null;


            // CombSort object
            CombSort CS = new CombSort();
            // copy of array
            copy_of_random_arr = random_arr.clone();
            // start time of the Combsort
            long combsort_start = System.currentTimeMillis();
            // CombSort start
            CS.sort(copy_of_random_arr);
            // end time of the Combsort
            long combsort_end = System.currentTimeMillis();
            System.out.println("CombSort:");
            System.out.println((combsort_end - combsort_start) / 1000.0 + " second");

            // GnomeSort object
            GnomeSort GS = new GnomeSort();
            // copy of array
            copy_of_random_arr = random_arr.clone();
            // start time of the GnomeSort
            long GnomeSort_start = System.currentTimeMillis();
            // CombSort start
            GS.sort(copy_of_random_arr);
            // end time of the GnomeSort
            long GnomeSort_end = System.currentTimeMillis();
            System.out.println("\nGnomeSort:");
            System.out.println((GnomeSort_end - GnomeSort_start) / 1000.0 + " second");

            // ShakerSort object
            ShakerSort SS = new ShakerSort();
            // copy of array
            copy_of_random_arr = random_arr.clone();
            // start time of the ShakerSort
            long ShakerSort_start = System.currentTimeMillis();
            // ShakerSort start
            SS.sort(copy_of_random_arr);
            // end time of the ShakerSort
            long ShakerSort_end = System.currentTimeMillis();
            System.out.println("\nShakerSort:");
            System.out.println((ShakerSort_end - ShakerSort_start) / 1000.0 + " second");

            // StoogeSort object
            StoogeSort STS = new StoogeSort();
            // copy of array
            copy_of_random_arr = random_arr.clone();
            // start time of the StoogeSort
            long StoogeSort_start = System.currentTimeMillis();
            // StoogeSort start
            STS.sort(copy_of_random_arr, 0, copy_of_random_arr.length - 1);
            // end time of the StoogeSort
            long StoogeSort_end = System.currentTimeMillis();

            System.out.println("\nStoogeSort:");
            System.out.println((StoogeSort_end - StoogeSort_start) / 1000.0 + " second");

            // StoogeSort object
            BitonicSort BS = new BitonicSort();
            // copy of array
            copy_of_random_arr = random_arr.clone();
            // start time of the StoogeSort
            long BitonicSort_start = System.currentTimeMillis();
            // StoogeSort start
            BS.sort(copy_of_random_arr, copy_of_random_arr.length, 1);

            // end time of the StoogeSort
            long BitonicSort_end = System.currentTimeMillis();

            System.out.println("\nBitonicSort:");
            System.out.println((BitonicSort_end - BitonicSort_start) / 1000.0 + " second\n");

        }
    }

    public static void main(String[] args) throws Exception {
        // Average Case -> random [23,12,15,1, ... ]
        System.out.println("Average Case Part");
        Average_Case_Process(0);
        // All Elements = 0 [0,0,0,0,0,0 ... ]
        System.out.println("All Elements = 0");
        Average_Case_Process(1);
        // All Elements = [30,29,28,27,26,25 ... ]
        System.out.println("All Elements descending");
        Average_Case_Process(2);
        // All Elements = [0,1,2,3,4,5,6 ...]
        System.out.println("All Elements ascending");
        Average_Case_Process(3);

    }
}
