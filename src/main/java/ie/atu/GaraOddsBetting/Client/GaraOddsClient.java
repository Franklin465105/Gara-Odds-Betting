package ie.atu.GaraOddsBetting.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "garaodds", url = "http://localhost:8080")
public interface GaraOddsClient {

    @GetMapping("/users/exists")
    boolean userExists(@RequestParam String username);

    @PostMapping("/balance/withdraw")
    ResponseEntity<?> withdraw(@RequestParam String username, @RequestParam double amount);

    @PostMapping("/balance/deposit")
    ResponseEntity<?> deposit(@RequestParam String username, @RequestParam double amount);
}