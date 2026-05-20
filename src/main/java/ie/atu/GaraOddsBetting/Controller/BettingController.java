package ie.atu.GaraOddsBetting.Controller;

import ie.atu.GaraOddsBetting.Model.Bet;
import ie.atu.GaraOddsBetting.Service.BettingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bets")
public class BettingController {

    private final BettingService bettingService;

    public BettingController(BettingService bettingService) {
        this.bettingService = bettingService;
    }

    // places a bet for the user
    @PostMapping("/place")
    public ResponseEntity<?> placeBet(
            @RequestParam String username,
            @RequestParam double amount,
            @RequestParam double odds,
            @RequestParam(defaultValue = "General") String category) {

        // stops negative or 0 bet amount
        if (amount <= 0) {
            return ResponseEntity.badRequest().body("Please enter a positive amount");
        }

        // odds must be greater than 1 to make sense
        if (odds <= 1.0) {
            return ResponseEntity.badRequest().body("Odds must be greater than 1.0");
        }

        try {
            Bet bet = bettingService.placeBet(username, amount, odds, category);

            // returns different message depending on if the user won or lost
            if (bet.getStatus().equals("WON")) {
                return ResponseEntity.ok("You WON! Potential winnings: €" + bet.getPotentialWinnings() + ". Bet ID: " + bet.getBetId());
            }
            return ResponseEntity.ok("You LOST. €" + amount + " was your stake. Bet ID: " + bet.getBetId());

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // shows all bets placed by a user
    @GetMapping
    public ResponseEntity<?> getBets(@RequestParam String username) {

        // checks if the user has any bets
        if (!bettingService.hasBets(username)) {
            return ResponseEntity.badRequest().body("No bets found for " + username);
        }

        return ResponseEntity.ok(bettingService.getBetsForUser(username));
    }

    @GetMapping("/category")
    public ResponseEntity<?> getBetsByCategory(
            @RequestParam String username,
            @RequestParam String category) {

        var categoryBets = bettingService.getBetsByCategory(username, category);

        if (categoryBets.isEmpty()) {
            return ResponseEntity.badRequest().body("No bets found for " + username + " in category: " + category);
        }
        return ResponseEntity.ok(categoryBets);
    }

    @GetMapping("/stats")
    public ResponseEntity<?> getStats(@RequestParam String username) {
        if (!bettingService.hasBets(username)) {
            return ResponseEntity.badRequest().body("No bets found for " + username);
        }
        return ResponseEntity.ok(bettingService.getBetStats(username));
    }
}