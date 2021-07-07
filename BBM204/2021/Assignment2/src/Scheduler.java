import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Scheduler {

    private Assignment[] assignmentArray;
    private Integer[] C;
    private Double[] max;
    private ArrayList<Assignment> solutionDynamic;
    private ArrayList<Assignment> solutionGreedy;

    /**
     * @throws IllegalArgumentException when the given array is empty
     */
    public Scheduler(Assignment[] assignmentArray) throws IllegalArgumentException {
        if (assignmentArray.length == 0){
            throw new IllegalArgumentException("Input is empty");
        }
        // Should be instantiated with an Assignment array
        this.assignmentArray = assignmentArray;
        // if input is empty , give an error
        // All the properties of this class should be initialized here
        this.C = new Integer[assignmentArray.length];
        // -1 means no assign -> all values -1 for init array
        Arrays.fill(this.C, -1);

        this.max = new Double[assignmentArray.length];
        Arrays.fill(this.max, -1.0);

        this.solutionDynamic = new ArrayList<Assignment>();
        this.solutionGreedy = new ArrayList<Assignment>();

    }

    /**
     * @param index of the {@link Assignment}
     * @return Returns the index of the last compatible {@link Assignment},
     * returns -1 if there are no compatible assignments
     */
    private int binarySearch(int index) {
        int lo = 0;
        int hi = this.assignmentArray.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = (hi + lo) / 2;
            //System.out.println(this.assignmentArray[mid].getFinishTime().compareTo(this.assignmentArray[index].getStartTime()));
            //System.out.println(this.assignmentArray[mid].getFinishTime());
            //System.out.println(this.assignmentArray[index].getStartTime());
            if (this.assignmentArray[mid].getFinishTime().compareTo(this.assignmentArray[index].getStartTime()) < 1) {
                if (this.assignmentArray[mid + 1].getFinishTime().compareTo(this.assignmentArray[index].getStartTime()) < 1)
                    lo = mid + 1;
                else
                    return mid;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }


    /**
     * {@link #C} must be filled after calling this method
     */
    private void calculateC() {
        for (int i = 1; i < this.assignmentArray.length; i++) {
            this.C[i] = this.binarySearch(i);
        }
    }


    /**
     * Uses {@link #assignmentArray} property
     *
     * @return Returns a list of scheduled assignments
     */
    public ArrayList<Assignment> scheduleDynamic() {
        // process with this.solutionDynamic
        this.calculateC();
        this.calculateMax(this.assignmentArray.length - 1);
        this.findSolutionDynamic(this.assignmentArray.length - 1);
        /*
        * so i need to reverse arraylist for outfile
        * i need to copy of arraylist for scheduleGreedy
        * */
        ArrayList<Assignment> solutionDynamic_copy = new ArrayList<Assignment>(this.solutionDynamic);
        Collections.reverse(solutionDynamic_copy);
        return solutionDynamic_copy;
    }

    /**
     * {@link #solutionDynamic} must be filled after calling this method
     */
    private void findSolutionDynamic(int i) {

        if (i == -1) {
            // output nothing
        } else {
            System.out.println("findSolutionDynamic(" + i + ")");
            /*
            * formula ->  (wj + M[p[ j ]] > M[ j – 1])
            * when i = 0, M[ j – 1] will be M[-1] . Error
            * Error FIX
             * */
            if (i == 0) {
                System.out.println("Adding " + this.assignmentArray[i] + " to the dynamic schedule");
                this.solutionDynamic.add(this.assignmentArray[i]);
            }
            /*
             * Since binary search returns -1 instead of NULL, when i = -1 in C [i], OutOfIndex error occurs.
             * To prevent this, since the numerical meaning of NULL is 0, it is arranged in the style of
             * C [null] = C [-1] = 0 at -1.
             * */
            else if (this.C[i] == -1 && this.assignmentArray[i].getWeight() + 0 > this.max[i - 1]) {
                System.out.println("Adding " + this.assignmentArray[i] + " to the dynamic schedule");
                this.solutionDynamic.add(this.assignmentArray[i]);
                findSolutionDynamic(this.C[i]);
            }
            //  i != -1
            else if (this.C[i] != -1 && this.assignmentArray[i].getWeight() + this.max[this.C[i]] > this.max[i - 1]) {

                System.out.println("Adding " + this.assignmentArray[i] + " to the dynamic schedule");
                this.solutionDynamic.add(this.assignmentArray[i]);
                findSolutionDynamic(this.C[i]);

            } else {
                findSolutionDynamic(i - 1);
            }
        }
    }

    /**
     * {@link #max} must be filled after calling this method
     */
    private Double calculateMax(int i) {
        if (i == -1) {
            return 0.0;
        }
        // not first time, print present
        else if (this.max[i] != -1 && i != 0) {
            System.out.println("calculateMax(" + i + "): Present");
            return this.max[i];
        }
        // another options
        else {
            // i equals 0 , print zero
            if (i == 0) {
                System.out.println("calculateMax(" + i + "): Zero");
                this.max[i] = this.assignmentArray[i].getWeight();
                return this.assignmentArray[i].getWeight();
            } else {
                System.out.println("calculateMax(" + i + "): Prepare");
                /*
                the result of the TA is not compatible with the codes in pdf
                pdf codes -> max {COMPUTE-OPT( j – 1), wj + COMPUTE-OPT(p[ j ]) }.
                TA result code -> max {COMPUTE-OPT(p[ j ]) + wj , COMPUTE-OPT( j – 1) }.
                 */
                Double result = Math.max(calculateMax(C[i]) + this.assignmentArray[i].getWeight(), calculateMax(i - 1));
                this.max[i] = result;
                return result;
            }
        }
    }

    /**
     * {@link #solutionGreedy} must be filled after calling this method
     * Uses {@link #assignmentArray} property
     *
     * @return Returns a list of scheduled assignments
     */
    public ArrayList<Assignment> scheduleGreedy() {
        // adding first element in solutionGreedy (default)
        this.solutionGreedy.add(this.assignmentArray[0]);
        System.out.println("Adding " + this.assignmentArray[0] + " to the greedy schedule");
        for (int i = 1; i < this.assignmentArray.length; i++) {
            // if solutionGreedy[-1].finishtime <= assignmentArray[i].starttime -> add it in solutionGreedy
            if (this.solutionGreedy.get(this.solutionGreedy.size() - 1).getFinishTime().compareTo(this.assignmentArray[i].getStartTime()) < 1) {
                this.solutionGreedy.add(this.assignmentArray[i]);
                System.out.println("Adding " + this.assignmentArray[i] + " to the greedy schedule");
            }
        }
        return this.solutionGreedy;
    }
}
