package Game;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    private Board game;

    @BeforeEach
    public void setUp() {
        game = new Board();
    }




    @Test
    @DisplayName("Default board test")
    public void CreateBoardTest() {
        game.createBoard();

      assertTrue(game.board.length == 6 && game.board[0].length == 7 );

    }
    @Test
    @DisplayName("Prints 0 filled board test")
    public void CreateBoard0FillPrintTest() {
        game.createBoard();

        assertEquals(game.board.printBoard," sad " );

    }



}










