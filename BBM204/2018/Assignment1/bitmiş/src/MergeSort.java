import java.util.List;

public class MergeSort {
     
    private List<Double> array;
    private double[] tempMergArr;
    private int length;
      
    public void sort(List<Double> veriler_merge_double) {
        this.array = veriler_merge_double;
        this.length = veriler_merge_double.size();
        this.tempMergArr = new double[length];
        doMergeSort(0, length - 1);
    }
 
    private void doMergeSort(int lowerIndex, int higherIndex) {
         
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            doMergeSort(lowerIndex, middle);
            // Below step sorts the right side of the array
            doMergeSort(middle + 1, higherIndex);
            // Now merge both sides
            mergeParts(lowerIndex, middle, higherIndex);
        }
    }
    private void mergeParts(int lowerIndex, int middle, int higherIndex) {
        for (int i = lowerIndex; i <= higherIndex; i++) {
            tempMergArr[i] = array.get(i);
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (tempMergArr[i] <= tempMergArr[j]) {
                array.set(k, tempMergArr[i]);
                i++;
            } else {
                array.set(k, tempMergArr[j]);
                j++;
            }
            k++;
        }
        while (i <= middle) {
            array.set(k, tempMergArr[i]);
            k++;
            i++;
        }

    }
}