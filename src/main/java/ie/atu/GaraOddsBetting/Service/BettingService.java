package ie.atu.GaraOddsBetting.Service;

import ie.atu.GaraOddsBetting.Client.GaraOddsClient;
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

    private final GaraOddsClient garaOddsClient;

    public BettingService(GaraOddsClient garaOddsClient) {
        this.garaOddsClient = garaOddsClient;
    }

    // places a bet and randomly decides if the user wins or loses
    public Bet placeBet(String username, double amount, double odds, String category) {

        if (!garaOddsClient.userExists(username)) {
            throw new RuntimeException("User not found: " + username);
        }

        garaOddsClient.withdraw(username, amount);

        boolean won = random.nextBoolean();

        Bet bet = new Bet();
        bet.setBetId(nextId++);
        bet.setUsername(username);
        bet.setAmount(amount);
        bet.setOdds(odds);
        bet.setCategory(category);
        bet.setPotentialWinnings(Math.round(amount * odds * 100.0) / 100.0);
        bet.setStatus(won ? "WON" : "LOST");
        bets.add(bet);

        if (won) {
            garaOddsClient.deposit(username, bet.getPotentialWinnings());
        }

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

    public List<Bet> getBetsByCategory(String username, String category) {
        List<Bet> userBets = new ArrayList<>();
        for (Bet bet : bets) {
            if (bet.getUsername().equals(username) && bet.getCategory().equalsIgnoreCase(category)) {
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

    public String getBetStats(String username) {
        int wins = 0;
        int losses = 0;
        double netProfit = 0.0;

        for (Bet bet : bets) {
            if (bet.getUsername().equals(username)) {
                if (bet.getStatus().equals("WON")) {
                    wins++;
                    netProfit += bet.getPotentialWinnings() - bet.getAmount();
                } else {
                    losses++;
                    netProfit -= bet.getAmount();
                }
            }
        }

        return "Stats for " + username + ":\n" +
                "  Total Bets: " + (wins + losses) + "\n" +
                "  Wins: " + wins + "\n" +
                "  Losses: " + losses + "\n" +
                "  Net Profit/Loss: €" + Math.round(netProfit * 100.0) / 100.0;
    }
}