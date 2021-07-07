

public class GnomeSort {
    void sort(int[] input) {
        int pos = 0;
        while (pos < input.length) {
            if (pos == 0 || input[pos] >= input[pos - 1]) {
                pos++;
            } else {
                //swap a[pos] and a[pos-1]
                int temp = 0;
                temp = input[pos];
                input[pos] = input[pos - 1];
                input[pos - 1] = temp;

                pos--;
            }
        }
    }


}
