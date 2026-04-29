package ie.atu.GaraOddsBetting.Service;

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
}
