package ie.atu.GaraOddsBetting.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bet {

    // unique id for each bet
    private int betId;

    private String username;
    private double amount;
    private double odds;
    private double potentialWinnings;

    // WON or LOST
    private String status;
}