package ie.atu.GaraOddsBetting.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "garaodds", url = "http://localhost:8080")
public interface GaraOddsClient {

    // checks if a user exists in the user microservice
    @GetMapping("/users/exists")
    boolean userExists(@RequestParam String username);

    // deducts the stake from the users balance
    @PostMapping("/balance/withdraw")
    String withdraw(@RequestParam String username, @RequestParam double amount);

    // adds winnings to the users balance
    @PostMapping("/balance/deposit")
    String deposit(@RequestParam String username, @RequestParam double amount);
}