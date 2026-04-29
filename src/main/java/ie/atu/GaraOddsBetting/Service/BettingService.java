package ie.atu.GaraOddsBetting.Service;

import ie.atu.GaraOddsBetting.Model.Odds;
import ie.atu.GaraOddsBetting.Model.Bet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class BettingService {

    private final List<Bet> bets = new ArrayList<>();
    private final Random random = new Random();
    private int nextId = 1;

    // places a bet and randomly decides if the user wins or loses
    public Bet placeBet(String username, double amount, double odds) {
        boolean won = random.nextBoolean();

        Bet bet = new Bet();
        bet.setBetId(nextId++);
        bet.setUsername(username);
        bet.setAmount(amount);
        bet.setOdds(odds);
        bet.setPotentialWinnings(amount * odds);
        bet.setStatus(won ? "WON" : "LOST");
        bets.add(bet);

        return bet;
    }

    // gets all bets placed by a user
    public List<Bet> getBetsForUser(String username) {
        List<Bet> userBets = new ArrayList<>();
        for (Bet bet : bets) {
            if (bet.getUsername().equals(username)) {
                userBets.add(bet);
            }
        }
        return userBets;
    }

    // checks if a user has any bets
    public boolean hasBets(String username) {
        for (Bet bet : bets) {
            if (bet.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    public List<Odds> getEsportsOdds() {
        List<Odds> esportsList = new ArrayList<>();

        Odds cod = new Odds();
        cod.setEventName("COD - Team Liquid vs FaZe Clan");
        cod.setOdds(1.90);
        esportsList.add(cod);

        Odds lol = new Odds();
        lol.setEventName("League of Legends - T1 vs Cloud9");
        lol.setOdds(2.20);
        esportsList.add(lol);

        Odds valorant = new Odds();
        valorant.setEventName("Valorant - Sentinels vs NRG");
        valorant.setOdds(1.75);
        esportsList.add(valorant);

        Odds fortnite = new Odds();
        fortnite.setEventName("Fortnite - FNCS Grand Finals");
        fortnite.setOdds(3.00);
        esportsList.add(fortnite);

        return esportsList;
    }
    public List<Odds> getChancerOdds() {
        List<Odds> chancerList = new ArrayList<>();

        Odds numbers = new Odds();
        numbers.setEventName("Numbers - Pick the correct number");
        numbers.setOdds(5.00);
        chancerList.add(numbers);

        Odds colour = new Odds();
        colour.setEventName("Colour - Pick the correct colour");
        colour.setOdds(2.00);
        chancerList.add(colour);

        Odds dicer = new Odds();
        dicer.setEventName("Dicer - Pick the correct dice roll");
        dicer.setOdds(6.00);
        chancerList.add(dicer);

        return chancerList;
    }
}
