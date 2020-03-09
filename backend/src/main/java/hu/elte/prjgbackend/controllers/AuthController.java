package hu.elte.prjgbackend.controllers;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import hu.elte.prjgbackend.models.User;
import hu.elte.prjgbackend.models.UserSession;
import hu.elte.prjgbackend.repositories.UserRepository;
import hu.elte.prjgbackend.repositories.UserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/signin")
public class AuthController {
    private final int HTTP_BAD_REQUEST = 400;
    private final int HTTP_UNAUTHORIZED = 401;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSessionRepository userSessionRepository;

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

            UUID uuid = createSession(currentUser, new Timestamp(payload.getExpirationTimeSeconds() * 1000L));
            System.out.println("UUID: " + uuid);

            return ResponseEntity.ok(uuid);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.status(HTTP_BAD_REQUEST).build();
    }

    private UUID createSession(User user, Timestamp expires) {
        Optional<UserSession> checkSession = userSessionRepository.findByUser(user);
        UserSession session = null;

        if (!checkSession.isPresent()) { //no session yet
            session = new UserSession();
            session.setUser(user);
            session.setExpires(expires);

            userSessionRepository.save(session);
        } else { //session already exists
            if (checkSession.get().getExpires().after(new Timestamp(System.currentTimeMillis()))) { //not yet expired
                session = checkSession.get();
            } else { //expired
                //delete
                userSessionRepository.delete(checkSession.get());
                //todo test this

                //make new
                session = new UserSession();
                session.setUser(user);
                session.setExpires(expires);

                userSessionRepository.save(session);
            }
        }

        return session.getToken();
    }
}