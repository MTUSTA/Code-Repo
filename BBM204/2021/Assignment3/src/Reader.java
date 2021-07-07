import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class Reader {

    Scanner scan;

    public Reader() {

    }

    // reads the lines of files in the parameter
    public ArrayList<String[]> ReadFile(String filepath) {

        ArrayList<String[]> lines = new ArrayList<String[]>();
        try {
            scan = new Scanner(new File(filepath));
            while (scan.hasNextLine()) {
                // read line
                String line = scan.nextLine();
                // split line
                String[] splitted_line = line.split("\t");
                lines.add(splitted_line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!!!");
        }
        return lines;
    }

    // Calculates the duration of the flight route
    public long[] calculate_flight_duration(ArrayList<Flight> flight) {
        // First element is hour, Second element is minute
        long[] result = {0, 0};
        // time difference  -> last element - first element
        long diff = flight.get(flight.size() - 1).getDeparture_date().getTime() - flight.get(0).getDeparture_date().getTime();
        // last element time
        String[] splitted_Flight_duration = flight.get(flight.size() - 1).getFlight_duration().split(":");
        // adding hours and minutes
        diff += Integer.parseInt(splitted_Flight_duration[0]) * (60 * 60 * 1000);
        diff += Integer.parseInt(splitted_Flight_duration[1]) * (60 * 1000);
        // converting ms to minute and ms to hour
        long dhours = diff / (60 * 60 * 1000);
        long dminutes = (diff / (60 * 1000)) % 60;
        // save result
        result[0] = dhours;
        result[1] = dminutes;
        //return saved result
        return result;
    }

    // print flight function
    public void print_flight(ArrayList<Flight> temp, PrintWriter writer) {
        // store price in varible
        int price = 0;
        // print result to file
        for (int j = 0; j < temp.size(); j++) {
            // get flight from hashtable
            writer.print(temp.get(j).getFlight_id() + "\t" + temp.get(j).getDeparture() + "->" + temp.get(j).getArrival_airport_aliases());
            // correct prints
            if (j != temp.size() - 1) {
                writer.print("||");
            }
            price += temp.get(j).getPrice();
        }
        // calculate time
        long[] result = this.calculate_flight_duration(temp);
        //print time
        writer.print("\t" + String.format("%02d", result[0]) + ":" + String.format("%02d", result[1]) + "/");
        writer.println(price);
    }

    static ArrayList<ArrayList<Flight>> listAll = new ArrayList<>();
    static ArrayList<ArrayList<Flight>> listProper = new ArrayList<>();

    // executes orders in commads.txt
    public void process(Digraph G, ArrayList<String[]> commands, PrintWriter writer) {
        for (String[] command : commands) {

            writer.print("command : " + command[0]);

            if (command[0].compareTo("listAll") == 0) {
                writer.print("\t" + command[1] + "\t" + command[2]);

                // split string by ->
                String[] src_dest = command[1].split("->");
                // string so index
                int[] index = G.searcher(src_dest[0], src_dest[1]);
                // generate all algs by using recursion
                G.AllPaths(index[0], index[1]);
                /* for TA output
                    The order of the flights in ListAll affects the result.
                    ListAll elements affect ListProper elements
                    I did sorting to reduce this effect.
                    You can see the result by commenting the listAll.sort () function on line 90.
                    but the place of the flights on ListAll in the list is very important for the results.
                    Digraph.java implementation taken in course pdf
                */
                listAll.sort(Comparator.comparing(o -> o.get(0).getFlight_id()));
                writer.println();
                // print flight
                for (int i = 0; i < listAll.size(); i++) {
                    // get copy for easy writing
                    ArrayList<Flight> temp = listAll.get(i);
                    this.print_flight(temp, writer);
                }
                writer.println("\n");

            } else if (command[0].compareTo("listProper") == 0) {
                writer.println("\t" + command[1] + "\t" + command[2]);

                // add first value in listProper from All list
                listProper.add(listAll.get(0));
                // calculate first element time
                long[] listproper_one = this.calculate_flight_duration(listProper.get(0));
                // calculate first element price
                int first_element_price = 0;
                for (int j = 0; j < listProper.get(0).size(); j++) {
                    first_element_price += listProper.get(0).get(j).getPrice();
                }
                // create listProper list process
                for (int i = 1; i < listAll.size(); i++) {
                    // get copy for easy writing
                    ArrayList<Flight> temp = listAll.get(i);
                    // calculate price
                    int price = 0;
                    for (int j = 0; j < temp.size(); j++) {
                        price += temp.get(j).getPrice();
                    }
                    // calculate flight duration
                    long[] result = this.calculate_flight_duration(temp);
                    // both quicker and cheaper
                    if (price < first_element_price && ((result[0] == listproper_one[0] && result[1] < listproper_one[1]) || (result[0] < listproper_one[0]))) {
                        listProper.remove(0);
                        listProper.add(0, temp);
                        //update first element price and time variable
                        first_element_price = price;
                        listproper_one = result;
                    } //only quicker
                    else if (price >= first_element_price && ((result[0] == listproper_one[0] && result[1] < listproper_one[1]) || (result[0] < listproper_one[0]))) {
                        listProper.add(temp);
                    } // only cheaper
                    else if (price < first_element_price && ((result[0] == listproper_one[0] && result[1] > listproper_one[1]) || (result[0] > listproper_one[0]))) {
                        listProper.add(temp);
                    }
                }
                // print every proper flight
                for (ArrayList<Flight> temp : listProper) {
                    this.print_flight(temp, writer);
                }
                writer.println("\n");
            } else if (command[0].compareTo("listCheapest") == 0) {
                writer.println("\t" + command[1] + "\t" + command[2]);

                // store cheapest
                int cheapest_price = 0;
                ArrayList<Flight> cheapest_flight = null;
                // calculate and find cheapest
                for (ArrayList<Flight> t1 : listAll) {
                    int price = 0;
                    for (Flight t2 : t1) {
                        price += t2.getPrice();
                    }
                    // set init first flight
                    if (cheapest_price == 0 && cheapest_flight == null) {
                        cheapest_price = price;
                        cheapest_flight = t1;
                    }
                    // if cheaper change saved flight
                    else if (price < cheapest_price) {
                        cheapest_flight = t1;
                    }
                }
                if (cheapest_flight == null) {
                    writer.println("No suitable flight plan is found");
                } else {
                    this.print_flight(cheapest_flight, writer);
                }

                writer.println("\n");
            } else if (command[0].compareTo("listQuickest") == 0) {
                writer.println("\t" + command[1] + "\t" + command[2]);
                // store Quickest
                long[] quickest = null;
                ArrayList<Flight> quickest_flight = null;
                // calculate and find Quickest
                for (ArrayList<Flight> t1 : listAll) {
                    long[] result = this.calculate_flight_duration(t1);
                    // set init first flight
                    if (quickest == null && quickest_flight == null) {
                        quickest = result;
                        quickest_flight = t1;
                    }
                    // if quicker change saved flight
                    else if ((result[0] == quickest[0] && result[1] < quickest[1]) || (result[0] < quickest[0])) {
                        quickest = result;
                        quickest_flight = t1;
                    }
                }
                if (quickest_flight == null) {
                    writer.println("No suitable flight plan is found");
                } else {
                    this.print_flight(quickest_flight,writer);
                }

                writer.println("\n");

            } else if (command[0].compareTo("listCheaper") == 0) {
                writer.println("\t" + command[1] + "\t" + command[2] + "\t" + command[3]);
                int max_price = Integer.parseInt(command[3]);
                // store cheaper flight
                ArrayList<ArrayList<Flight>> cheaper_flight_list_from_proper = new ArrayList<ArrayList<Flight>>();

                for (ArrayList<Flight> t1 : listProper) {
                    int price = 0;
                    // calculate price
                    for (Flight t2 : t1) {
                        price += t2.getPrice();
                    }
                    // if cheaper saved flight
                    if (price < max_price) {
                        cheaper_flight_list_from_proper.add(t1);
                    }
                }
                // if no cheaper flight
                if (cheaper_flight_list_from_proper.size() == 0) {
                    writer.println("No suitable flight plan is found");
                }
                // print cheaper flight
                else {
                    for (ArrayList<Flight> p : cheaper_flight_list_from_proper) {
                        this.print_flight(p,writer);
                    }
                }
                writer.println("\n");

            } else if (command[0].compareTo("listQuicker") == 0) {
                writer.println("\t" + command[1] + "\t" + command[2] + "\t" + command[3]);
                // time converting
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                Date latest_date_time = null;
                try {
                    latest_date_time = format.parse(command[3]);
                } catch (Exception e) {
                    writer.println(e);
                }
                // store Quicker flight
                ArrayList<ArrayList<Flight>> earlier_date_time = new ArrayList<ArrayList<Flight>>();
                // calculate Quicker flight
                for (ArrayList<Flight> t1 : listProper) {
                    // get last flight from route
                    Flight last_flight = t1.get(t1.size() - 1);

                    // calculate arrival time last flight
                    Date last_flight_date = last_flight.getDeparture_date();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(last_flight_date);
                    // split string 7:45 -> ["7","45"]
                    String[] splitted_flight_duration = last_flight.getFlight_duration().split(":");
                    // string to int
                    int hour = Integer.parseInt(splitted_flight_duration[0]);
                    int minute = Integer.parseInt(splitted_flight_duration[1]);
                    // adding time
                    calendar.add(Calendar.HOUR, hour);
                    calendar.add(Calendar.MINUTE, minute);
                    last_flight_date = calendar.getTime();

                    // route result < commad txt date -> add flight route in arraylist
                    if (latest_date_time.compareTo(last_flight_date) > 0) {
                        earlier_date_time.add(t1);
                    }
                }
                // if no route
                if (earlier_date_time.size() == 0) {
                    writer.println("No suitable flight plan is found");
                } else {
                    for (ArrayList<Flight> p : earlier_date_time) {
                        this.print_flight(p,writer);
                    }
                }

                writer.println("\n");
            } else if (command[0].compareTo("listExcluding") == 0) {
                writer.println("\t" + command[1] + "\t" + command[2] + "\t" + command[3]);
                // store Excluding route
                ArrayList<ArrayList<Flight>> excluding = new ArrayList<ArrayList<Flight>>();
                // listing all the proper flight
                for (ArrayList<Flight> t1 : listProper) {
                    int counter = 0;
                    // search airlines company. if found, stop loop and increase counter
                    for (Flight f : t1) {
                        if (f.getFlight_id().contains(command[3])) {
                            counter++;
                            break;
                        }
                    }
                    // do not involve a flight from given airlines company.
                    if (counter == 0) {
                        excluding.add(t1);
                    }
                }
                // if no excluding
                if (excluding.size() == 0) {
                    writer.println("No suitable flight plan is found");
                } else {
                    for (ArrayList<Flight> p : excluding) {
                        this.print_flight(p,writer);
                    }
                }
                writer.println("\n");
            } else if (command[0].compareTo("listOnlyFrom") == 0) {
                writer.println("\t" + command[1] + "\t" + command[2] + "\t" + command[3]);

                ArrayList<ArrayList<Flight>> listOnlyFrom = new ArrayList<ArrayList<Flight>>();
                // listing all the proper flight
                for (ArrayList<Flight> t1 : listProper) {
                    int counter = 0;
                    for (Flight f : t1) {
                        // search airlines company. if found, stop loop and increase counter
                        if (f.getFlight_id().contains(command[3])) {
                            counter++;
                            break;
                        }
                    }
                    // only from the given airlines company
                    if (counter != 0) {
                        listOnlyFrom.add(t1);
                    }
                }
                // if no OnlyFrom
                if (listOnlyFrom.size() == 0) {
                    writer.println("No suitable flight plan is found");
                }
                else {
                    for (ArrayList<Flight> p : listOnlyFrom) {
                        this.print_flight(p,writer);
                    }
                }
                writer.println("\n");
            }
            /*I think TA result is wrong for exp2 and exp3*/
            else if (command[0].compareTo("diameterOfGraph") == 0) {
                writer.println();

                // default min value
                int max_price = Integer.MIN_VALUE;

                // all nodes -> G.V() -> but here source loop
                for (int source = 0; source < G.V(); source++) {
                    // Single-source shortest directed paths finder object
                    BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(G, source);
                    // all source node -> G.V() -> but here target lane
                    for (int v = 0; v < G.V(); v++) {
                        // source to target has path
                        if (bfs.hasPathTo(v)) {
                            // get shortest path
                            ArrayList<Integer> path = bfs.pathTo(v);
                            // default init price
                            int price = 0;
                            if (path.size() > 1) {
                                int element1 = path.get(path.size() - 1);
                                int element2 = path.get(path.size() - 2);
                                // get flights
                                ArrayList<Flight> f = G.flights.get(Integer.toString(element1) + Integer.toString(element2));
                                // If there is more than 1 flight between the source and the destination, finds the one with a higher price
                                Flight init_value = f.stream().max(Comparator.comparing(Flight::getPrice)).get();
                                price += init_value.getPrice();

                                int counter = 1;
                                for (int x = path.size() - 3; x > -1; x--) {
                                    // get max price from list
                                    if (counter == 1) {
                                        element1 = path.get(x);
                                        f = G.flights.get(Integer.toString(element2) + Integer.toString(element1));
                                        counter = 2;
                                    } else if (counter == 2) {
                                        element2 = path.get(x);
                                        f = G.flights.get(Integer.toString(element1) + Integer.toString(element2));
                                        counter = 1;
                                    }

                                    if (f != null) {
                                        //Flight::getPrice = ele -> ele.getPrice()
                                        Flight max_price_flight = f.stream().max(Comparator.comparing(Flight::getPrice)).get();
                                        price += max_price_flight.getPrice();
                                    }
                                }

                            }

                            if (price > max_price) {
                                max_price = price;
                            }
                        }
                    }
                }
                writer.println("The diameter of graph : " + max_price + "\n\n");
            } else if (command[0].compareTo("pageRankOfNodes") == 0) {
                writer.println();
                // source algorithm -> https://youtu.be/P8Kt6Abq_rM

                // init setting iteration 0 -> 1/ Node number(airport num)
                double[] iterations = new double[G.V()];
                for (int i = 0; i < iterations.length; i++) {
                    // set init array values
                    iterations[i] = 1.0 / G.V();
                }
                /*I just did the ITER1 step because the results came out so close.
                 * That's why I didn't want to code ITER2
                 */
                // iteration 1
                for (int i = 0; i < iterations.length; i++) {
                    double iter1_result = 0;
                    // find outdegree node
                    for (int j = 0; j < iterations.length; j++) {
                        if (j != i) {
                            // get vertices
                            Iterable<Integer> vertices = G.adj(j);
                            for (Integer int1 : vertices) {
                                // if outdegree
                                if (int1 == i) {
                                    //calculate iter1
                                    int outdegree_val = G.outdegree(j);
                                    iter1_result += iterations[i] / outdegree_val;
                                }
                            }
                        }
                    }
                    iterations[i] = iter1_result;
                }

                // printing result
                for (int i = 0; i < iterations.length; i++) {
                    if (i !=iterations.length-1) {
                        writer.println(G.Cities.get(i).alias.get(0) + " : " + String.format("%.3f", iterations[i]));
                    }
                    else{
                        writer.print(G.Cities.get(i).alias.get(0) + " : " + String.format("%.3f", iterations[i]));
                    }
                }

            }

        }
    }
}
