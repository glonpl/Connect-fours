package Game;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class BoardTest {

    private PrintStream originalSystemOut;
    private ByteArrayOutputStream systemOutContent;
    private Board game;

    @BeforeEach
    public void setUp() {
        game = new Board();
        originalSystemOut = System.out;

        // given
        systemOutContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOutContent));
    }




    @AfterEach
    void restoreSystemOutStream() {
        System.setOut(originalSystemOut);
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
        game.printBoard();
        assertEquals(String.format("\r\n 0 0 0 0 0 0 0\r\n 0 0 0 0 0 0 0\r\n 0 0 0 0 0 0 0\r\n 0 0 0 0 0 0 0\r\n 0 0 0 0 0 0 0\r\n 0 0 0 0 0 0 0"),systemOutContent.toString());
    }

    @Test
    @DisplayName("Sets wrong new size of a board, Exception test")//can throw exception if x or y <4
    public void SetBoardSizeExceptionThrowTest() {
        game.createBoard();
        assertThrows(Exception.class, () -> {
            game.setBoardSize(2,5);
        });


    }
    @Test
    @DisplayName("Sets wrong new size of a board, Exception message test")//can throw exception if x or y <4
    public void SetBoardSizeExceptionThrowMessageTest() {
        game.createBoard();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            game.setBoardSize(2,5);
        });
assertEquals("Za mała plansza! Musi być minimum 4x4!",exception.getMessage());

    }
}










