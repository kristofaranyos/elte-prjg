package hu.elte.prjgbackend;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.hamcrest.MatcherAssert;
import static org.hamcrest.Matchers.*;

import hu.elte.prjgbackend.models.Game;
import hu.elte.prjgbackend.models.Team;
import hu.elte.prjgbackend.models.User;
import hu.elte.prjgbackend.models.Image;

import hu.elte.prjgbackend.services.GameService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameServiceTest {

    @MockBean
    GameService gameService;

    @Test
    public void testFindAllListIsEmpty(){
        Mockito.when(gameService.findAll()).thenReturn(new ArrayList<Game>());

        ArrayList<Game> games = (ArrayList<Game>) gameService.findAll();
        MatcherAssert.assertThat("", games.isEmpty());
    }

    @Test
    public void testFindAllListContainsElements(){
        ArrayList<Game> mockGames = new ArrayList<Game>();

        mockGames.add(new Game(0L,dummyTeams(),1542, true));
        mockGames.add(new Game(1L,dummyTeams(),65246, false));

        Mockito.when(gameService.findAll()).thenReturn(mockGames);

        ArrayList<Game> games = (ArrayList<Game>) gameService.findAll();
        MatcherAssert.assertThat("", games.equals(mockGames));
    }

    private List<Team> dummyTeams(){
        User u1 = new User("0","User1","asdfgsdfh1.com","user1@email.com");
        User u2 = new User("1","User2","asdfgsdfh2.com","user2@email.com");
        User u3 = new User("2","User3","asdfgsdfh3.com","user3@email.com");

        Image i1 = new Image(0L,"image1url.com");
        Image i2 = new Image(1L,"image2url.com");
        Image i3 = new Image(2L,"image3url.com");
        Image i4 = new Image(3L,"image4url.com");

        Set<Image> images = new HashSet<Image>();
        images.add(i1);
        images.add(i2);
        images.add(i3);
        images.add(i4);

        User u4 = new User("3","User4","asdfgsdfh4.com","user4@email.com");
        User u5 = new User("4","User5","asdfgsdfh5.com","user5@email.com");
        User u6 = new User("5","User6","asdfgsdfh6.com","user6@email.com");
        User u7 = new User("6","User7","asdfgsdfh7.com","user7@email.com");

        Set<User> players1 = new HashSet<User>();
        players1.add(u1);
        players1.add(u2);
        players1.add(u3);

        Set<User> players2 = new HashSet<User>();
        players2.add(u4);
        players2.add(u5);
        players2.add(u6);
        players2.add(u7);

        Team team1 = new Team(0L,"team1",players1,images,3.6f);
        Team team2 = new Team(1L,"team2",players2,images,2.4f);

        List<Team> teams = new ArrayList<Team>();
        teams.add(team1);
        teams.add(team2);

        return teams;
    }
}