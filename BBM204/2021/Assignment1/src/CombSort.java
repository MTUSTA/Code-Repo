
public class CombSort {
    void sort(int[] input) {
        int gap = input.length;
        double shrink = 1.3;
        boolean sorted = false;

        while (!sorted) {
            // Update the gap value for a next comb
            gap = (int) Math.floor(gap / shrink);
            if (gap <= 1) {
                gap = 1;
                sorted = true; // If there are no swaps this pass, we are done
            }
            // A single "comb" over the input list
            int i = 0;
            while (i + gap < input.length) {// See Shell sort for a similar idea
                if (input[i] > input[i + gap]) {
                    // swapping
                    // Swap arr[i] and arr[i+gap]
                    int temp = input[i];
                    input[i] = input[i + gap];
                    input[i + gap] = temp;

                    sorted = false;
                    // If this assignment never happens within the loop,
                    // then there have been no swaps and the list is sorted.
                }
                i++;
            }
        }
    }




}
