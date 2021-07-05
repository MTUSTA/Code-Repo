import java.util.List;

public class SelectionSort{
    public void selectionSort(List<String[]> veriler, int sayi,List<Double> veriler_selection_double)    {
        double temp;int pos;
 
        for (int i = 0; i < veriler_selection_double.size() - 1; i++){
            pos = i;
            //finding the position of smallest element between (i+1)th element and last element
            for (int j = i+1; j < veriler_selection_double.size(); j++){
                if(veriler_selection_double.get(j) < veriler_selection_double.get(pos) ){
                    pos = j;
                }
            }

            //Swapping inputArray[i] and inputArray[pos]
            temp = veriler_selection_double.get(i);
            String[] temp2 = veriler.get(i);

            veriler.set(i, veriler.get(pos));
            veriler_selection_double.set(i, veriler_selection_double.get(pos));
            
            veriler.set(pos, temp2);
            veriler_selection_double.set(pos, temp);
        }
    }
}