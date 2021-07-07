import java.util.ArrayList;


public class Airport {
    // name
    String name;
    // alias
    ArrayList<String> alias = new ArrayList<String>();

    public Airport(String[] information){
        this.name = information[0];
        for (int i = 1;i<information.length;i++){
            this.alias.add(information[i]);
        }
    }
}
