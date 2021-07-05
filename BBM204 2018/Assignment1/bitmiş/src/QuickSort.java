import java.util.List;

// Java program for implementation of QuickSort
public class QuickSort{
    /* This function takes last element as pivot,
       places the pivot element at its correct
       position in sorted array, and places all
       smaller (smaller than pivot) to left of
       pivot and all greater elements to right
       of pivot */
    int partition(int low, int high,List<Double> veriler_quick_double){
        double pivot = veriler_quick_double.get(high); 
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++){
            // If current element is smaller than or
            // equal to pivot
            if (veriler_quick_double.get(j) <= pivot){
                i++;
                // swap veriler_quick_double[i] and veriler_quick_double[j]
                double temp = veriler_quick_double.get(i);   
                veriler_quick_double.set(i, veriler_quick_double.get(j));                           
                veriler_quick_double.set(j, temp);
            }
        }
        // swap arr[i+1] and arr[high] (or pivot)
        double temp = veriler_quick_double.get(i+1);
        veriler_quick_double.set(i+1, veriler_quick_double.get(high));
        veriler_quick_double.set(high, temp);
        return i+1;
    }
 
 
    /* The main function that implements QuickSort()
      veriler_quick and veriler_quick_double --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index 
      sayi --> Feature index*/
    void sort(int low, int high,List<Double> veriler_quick_double){
        if (low < high){
            /* pi is partitioning index, arr[pi] is 
              now at right place */
            int pi = partition(low, high,veriler_quick_double);
 
            // Recursively sort elements before
            // partition and after partition
            sort(low, pi-1,veriler_quick_double);
            sort(pi+1, high,veriler_quick_double);
        }
    }
}