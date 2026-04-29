package ie.atu.GaraOddsBetting.Model;

public class Odds {

    private String eventName;
    private double odds;

    // empty constructor (needed for Spring/JSON)
    public Odds() {
    }

    // optional constructor (makes creating objects easier)
    public Odds(String eventName, double odds) {
        this.eventName = eventName;
        this.odds = odds;
    }

    // getter for eventName
    public String getEventName() {
        return eventName;
    }

    // setter for eventName
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    // getter for odds
    public double getOdds() {
        return odds;
    }

    // setter for odds
    public void setOdds(double odds) {
        this.odds = odds;
    }
}