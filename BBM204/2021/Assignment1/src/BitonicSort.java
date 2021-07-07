public class BitonicSort {
    void compAndSwap(int[] a, int i, int j, int dire) {
        if ((dire == 1 && a[i] > a[j]) || (dire == 0 && a[i] < a[j])) {
            // Swapping elements
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

    void bitonicMerge(int[] a, int low, int cnt, int dire) {
        if (cnt > 1) {
            int k = cnt / 2;
            for (int i = low; i < low + k; i++)
                compAndSwap(a, i, i + k, dire);
            bitonicMerge(a, low, k, dire);
            bitonicMerge(a, low + k, k, dire);
        }
    }

    void bitonicSort(int[] a, int low, int cnt, int dire) {
        if (cnt > 1) {
            int k = cnt / 2;

            // sort in ascending order since dire here is 1
            bitonicSort(a, low, k, 1);

            // sort in descending order since dire here is 0
            bitonicSort(a, low + k, k, 0);

            // Will merge wole sequence in ascending order since dire=1.
            bitonicMerge(a, low, cnt, dire);
        }
    }

    void sort(int[] a, int N, int up) {
        bitonicSort(a, 0, N, up);
    }


}

