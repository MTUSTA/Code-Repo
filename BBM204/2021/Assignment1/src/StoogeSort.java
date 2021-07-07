public class StoogeSort {
    int[] sort(int[] L, int i, int j) {
        // If the leftmost element is larger than the rightmost element
        if (L[i] > L[j]) {
            // Swap the leftmost element and the rightmost element
            int t = L[i];
            L[i] = L[j];
            L[j] = t;
        }

        // If there are at least 3 elements in the array
        if (j - i + 1 > 2) {
            int t = (int) Math.floor((j - i + 1) / 3);
            sort(L, i, j - t); // Sort the first 2/3 of the array
            sort(L, i + t, j);// Sort the last 2/3 of the array
            sort(L, i, j - t);// Sort the first 2/3 of the array again
        }
        return L;
    }


}
