package hu.elte.prjgbackend;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.hamcrest.MatcherAssert;

import hu.elte.prjgbackend.models.Game;
import hu.elte.prjgbackend.models.Team;
import hu.elte.prjgbackend.models.User;

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

        mockGames.add(new Game(0L,null,1542, true));
        mockGames.add(new Game(1L,null,65246, true));

        Mockito.when(gameService.findAll()).thenReturn(mockGames);

        ArrayList<Game> games = (ArrayList<Game>) gameService.findAll();
        MatcherAssert.assertThat("", games.equals(mockGames));
    }


}