package hu.elte.prjgbackend.controllers;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import hu.elte.prjgbackend.models.User;
import hu.elte.prjgbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/signin")
public class AuthController {
    private final int HTTP_BAD_REQUEST = 400;
    private final int HTTP_UNAUTHORIZED = 401;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity externalSignin(@RequestBody String token) {
        if (token == null) {
            return ResponseEntity.status(HTTP_BAD_REQUEST).build();
        }

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList("xzy")) //todo make google oauth key
                .build();

        try {
            GoogleIdToken idToken = verifier.verify(token);

            if (idToken == null) {
                System.out.println("Invalid ID token.");
                return ResponseEntity.status(HTTP_UNAUTHORIZED).build();
            }

            GoogleIdToken.Payload payload = idToken.getPayload();

            User currentUser;

            //check if user exists
            Optional<User> checkUser = userRepository.findById(payload.getSubject());

            //add user to db if not
            if (!checkUser.isPresent()) {
                User newUser = new User();

                newUser.setId(payload.getSubject());
                newUser.setEmail(payload.getEmail());
                newUser.setPictureUrl(null); //todo find out where picture url is in the payload
                newUser.setUserName(null);

                userRepository.save(newUser);
                currentUser = newUser;
            } else {
                currentUser = checkUser.get();
            }

            //todo make a session for the user

            return ResponseEntity.ok(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.status(HTTP_BAD_REQUEST).build();
    }
}
