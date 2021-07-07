

public class Assignment implements Comparable {
    private String name;
    private String start;
    private int duration;
    private int importance;
    private boolean maellard;

    /*
        Getter methods
     */
    public String getName() {
        return this.name;
    }

    public String getStartTime() {
        return this.start;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getImportance() {
        return this.importance;
    }

    public int isMaellard() {
        return this.maellard ? 1001 : 1;
    }

    /**
     * Finish time should be calculated here
     *
     * @return calculated finish time as String
     */
    public String getFinishTime() {
        String[] splitted_start = this.start.split(":");
        int val1 = Integer.parseInt(splitted_start[0]);
        /* When converting from string to integer, if the string is less than 10, it loses 0 in the conversion.
        * like -> "05" convert to int -> 5
         */
        String added = Integer.toString(val1 + this.duration);
        // type fixer -> if integer value less than 10 , add 0 at the beginning of the string
        if (added.length() == 1){
            added = "0" + added;
        }
        return added + ":" + splitted_start[1];
    }

    /**
     * Weight calculation should be performed here
     *
     * @return calculated weight
     */
    // Step 3: Calculating Weights
    public double getWeight() {
        return (double) (this.importance * isMaellard()) / this.duration;
    }

    /**
     * This method is needed to use {@link java.util.Arrays#sort(Object[])} ()}, which sorts the given array easily
     *
     * @param o Object to compare to
     * @return If self > object, return > 0 (e.g. 1)
     * If self == object, return 0
     * If self < object, return < 0 (e.g. -1)
     */
    // part 2
    @Override
    public int compareTo(Object o) {
        return this.getFinishTime().compareTo(((Assignment) o).getFinishTime());
    }

    /**
     * @return Should return a string in the following form:
     * Assingment{name='Refill vending machines', start='12:00', duration=1, importance=45, maellard=false, finish=13:00, weight=45.0}
     */
    @Override
    public String toString() {
        return "Assignment{name='" + this.name + "', start='" + this.start + "', duration=" + this.duration + ", importance=" + this.importance + ", maellard=" + this.maellard + ", finish='" + this.getFinishTime() + "', weight=" + this.getWeight() + "}";
    }
}
