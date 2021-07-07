import java.text.SimpleDateFormat;
import java.util.Date;

public class Flight {
    private String flight_id;
    private String departure;
    private String arrival_airport_aliases;
    private Date departure_date;
    private String flight_duration;
    private double price;

    public Flight(String flight_id, String departure, String arrival_airport_aliases, String departure_date, String flight_duration, String price) {
        this.flight_id = flight_id;
        this.departure = departure;
        this.arrival_airport_aliases = arrival_airport_aliases;

        // string to Date
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            this.departure_date = format.parse(departure_date);
        }catch (Exception e){
            System.out.println(e);
        }
        this.flight_duration = flight_duration;
        this.price = Double.parseDouble(price);
    }

    public String getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(String flight_id) {
        this.flight_id = flight_id;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival_airport_aliases() {
        return arrival_airport_aliases;
    }

    public void setArrival_airport_aliases(String arrival_airport_aliases) {
        this.arrival_airport_aliases = arrival_airport_aliases;
    }

    public Date getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(Date departure_date) {
        this.departure_date = departure_date;
    }

    public String getFlight_duration() {
        return flight_duration;
    }

    public void setFlight_duration(String flight_duration) {
        this.flight_duration = flight_duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
