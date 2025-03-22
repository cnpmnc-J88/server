package my.app.controller.auth;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.app.models.User;
import my.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.asynchttpclient.*;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserRepository userRepository;

    @Value("${env.client_id}")
    private String clientId;

    @Value("${env.client_secret}")
    private String clientSecret;

    @Value("${env.redirect_url}")
    private String redirectUrl;

    private String getGOOGLE_OAUTH_URL() {
        return "https://accounts.google.com/o/oauth2/auth" +
                "?client_id=" + clientId +
                "&redirect_uri=" + redirectUrl +
                "&response_type=code" +
                "&scope=openid%20email%20profile" +
                "&access_type=offline" +
                "&prompt=consent";
    }
    @GetMapping("/receive")
    public ResponseEntity<?> tempTokenReceiver(
            @RequestParam String access_token
            , @RequestParam String refresh_token) {
        return ResponseEntity.ok().body(Map.of(
                "access_token", access_token,
                "refresh_token", refresh_token)
        );
    }

    @GetMapping("/login")
    public RedirectView login() {
        return new RedirectView(getGOOGLE_OAUTH_URL());
    }

    @GetMapping("/google/callback")
    public RedirectView google(
            @RequestParam("code") String code,
            @RequestParam("scope") String scope,
            @RequestParam(value = "authuser", required = false) String authUser,
            @RequestParam(value = "prompt", required = false) String prompt
    ) {
        try {
            AsyncHttpClient client = Dsl.asyncHttpClient();

            String requestBody = "client_id=" + clientId
                    + "&client_secret=" + clientSecret
                    + "&code=" + code
                    + "&redirect_uri=" + redirectUrl
                    + "&grant_type=authorization_code";

            CompletableFuture<Response> futureResponse = client
                    .preparePost("https://oauth2.googleapis.com/token")
                    .setHeader("Content-Type", "application/x-www-form-urlencoded")
                    .setBody(requestBody)
                    .execute()
                    .toCompletableFuture();

            Response response = futureResponse.get();

            int statusCode = response.getStatusCode();
            String responseBody = response.getResponseBody();

            if (statusCode >= 200 && statusCode < 300) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(responseBody);
                String access_token = jsonNode.get("access_token").asText();
                String refresh_token = jsonNode.get("refresh_token").asText();

                return new RedirectView("/auth/receive?access_token=" + access_token + "&refresh_token=" + refresh_token);
            } else {
                return new RedirectView(redirectUrl + "/auth/receive?access_token=" + null + "&refresh_token=" + null);
            }
        } catch (Exception e) {
            return new RedirectView(redirectUrl + "/auth/receive?access_token=" + null + "&refresh_token=" + null);
        }
    }

    @PostMapping("/register_email")
    public ResponseEntity<?> registerEmail(@RequestParam String access_token) {
        try {
            AsyncHttpClient client = Dsl.asyncHttpClient();

            CompletableFuture<Response> futureResponse = client.prepareGet("https://www.googleapis.com/oauth2/v3/userinfo")
                    .setHeader("Authorization", "Bearer " + access_token)
                    .execute()
                    .toCompletableFuture();

            Response response = futureResponse.get();
            int statusCode = response.getStatusCode();
            String responseBody = response.getResponseBody();

            if (statusCode >= 200 && statusCode < 300) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(responseBody);

                String email =  jsonNode.get("email").asText();

                User user = new User(email, email.split("@")[0]);

                userRepository.save(user);

                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(statusCode).body(responseBody);
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
