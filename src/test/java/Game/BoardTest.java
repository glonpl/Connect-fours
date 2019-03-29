package Game;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
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



    @Test//junit
    @DisplayName("Default board test")
    public void CreateBoardTest() {
        game.createBoard();

      assertTrue(game.board.length == 6 && game.board[0].length == 7 );

    }

    @Test                                                   //junit
    @DisplayName("Prints 0 filled board test")
    public void CreateBoard0FillPrintTest() {
        game.createBoard();
        game.printBoard();
        assertEquals(String.format("\r\n 0 0 0 0 0 0 0\r\n 0 0 0 0 0 0 0\r\n 0 0 0 0 0 0 0\r\n 0 0 0 0 0 0 0\r\n 0 0 0 0 0 0 0\r\n 0 0 0 0 0 0 0"),systemOutContent.toString());
    }

    @Test                                                    //junit
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
assertThat(exception.getMessage(),containsString("Za mała plansza! Musi być minimum 4x4!"));

    }
    @Test
    @DisplayName("Sets Proper 9x9 board Test")
    public void SetBoardSizeTest() {
        game.createBoard();
        game.setBoardSize(9,9);
        assertThat((game.board.length == 9 && game.board[0].length == 9 ), isOneOf(true));

    }
    @Test
    @DisplayName("Prints 9x9 test")
    public void Board9x9PrintTest() {
        game.createBoard();
        game.setBoardSize(9,9);
        game.printBoard();
        assertThat(systemOutContent.toString(), startsWith("\r\n 0 0 0 0 0 0 0 0 0"));
    }
    @Test
    @DisplayName("Win pattern test | ")
    public void WinCheckTestVer() {
        game.createBoard();
        int count=0;
        for (count=1; count<5; count++){
        game.board[count][0] = 1;}
        game.winCheck(0,2,1,"Zbychu");
       assertThat(game.winner, startsWith("Zby"));
       }
    @Test
    @DisplayName("Win pattern test - ")
    public void WinCheckTestHor() {
        game.createBoard();
        int count=0;
        for (count=1; count<5; count++){
            game.board[0][count] = 1;}
        game.winCheck(2,0,1,"Ania");
        assertThat(game.winner, endsWith("nia"));
    }

    @Test
    @DisplayName("Win pattern test / ")
    public void WinCheckTestVerUp() {
        game.createBoard();
        int count=0;
        for (count=1; count<5; count++){
            game.board[count][count] = 1;}
        game.winCheck(2,2,1,"Zbychu");
        assertThat(game.winner, containsString("bych"));
    }
    @Test
    @DisplayName("Win pattern test \\ ")
    public void WinCheckTestVerDown() {
        game.createBoard();
        int count=0;
        for (count=5; count>1; count--){
            game.board[count][count] = 1;}
        game.winCheck(2,2,1,"Wojtek");
        assertThat(game.winner, containsString("ojte"));
    }



    @Test                                                           //junit
    @DisplayName("Checks if throws exception while trying to put coin in wrong column ")
    public void PutToWrongHoleTest()throws Exception {
        game.createBoard();

        Throwable exception = assertThrows(Exception.class, () -> {
            game.putCoin(9,1,"Franek");
        });
        assertEquals("Nie ma takiej kolumny!", exception.getMessage());
    }
    @Test
    @DisplayName("Check if disc falls ")
    public void FallDownTest()throws Exception {
        game.createBoard();
        game.putCoin(2,1,"Franek");
        game.printBoard();
        assertThat(systemOutContent.toString(), endsWith("0 0 1 0 0 0 0"));
    }
    @Test
    @DisplayName("Check if Informs that column is full ")
    public void FullColumnPutTest()throws Exception {
        game.createBoard();
        game.putCoin(2,1,"Franek");
        game.putCoin(2,1,"Franek");
        game.putCoin(2,1,"Franek");
        game.putCoin(2,1,"Franek");
        game.putCoin(2,1,"Franek");
        game.putCoin(2,1,"Franek");
        game.putCoin(2,1,"Franek");

        assertThat(systemOutContent.toString(), endsWith("Wybierz inną!"));
    }
    @Test                                                       //junit
    @DisplayName("Check if returns False if column full ")
    public void FullColumnPutFALSETest()throws Exception {
        game.createBoard();
        game.putCoin(2,1,"Franek");
        game.putCoin(2,1,"Franek");
        game.putCoin(2,1,"Franek");
        game.putCoin(2,1,"Franek");
        game.putCoin(2,1,"Franek");
        game.putCoin(2,1,"Franek");

        assertFalse(game.putCoin(2,1,"Franek"));
    }
    @Test
    @DisplayName("Check if returns True after putted ")
    public void FullColumnPutTrueTest()throws Exception {
        game.createBoard();

        assertThat(game.putCoin(2,1,"Franek"),is(true));
    }
    @Test
    @DisplayName("Check if sets Winner column ")
    public void FallWinTest()throws Exception {
        game.createBoard();
        game.putCoin(2,1,"Franek");
        game.putCoin(2,1,"Franek");
        game.putCoin(2,1,"Franek");
        game.putCoin(2,1,"Franek");


        assertThat(game.winner,not(isEmptyOrNullString()));

    }
    @Test
    @DisplayName("Check if sets Winner angled+ ")
    public void FallWinAngleUpTest()throws Exception {
        game.createBoard();
        game.putCoin(2,1,"Franek");
        game.putCoin(2,5,"Zbychu");
        game.putCoin(3,5,"Zbychu");
        game.putCoin(4,1,"Franek");
        game.putCoin(4,5,"Zbychu");
        game.putCoin(4,1,"Franek");
        game.putCoin(2,1,"Franek");
        game.putCoin(5,1,"Franek");
        game.putCoin(5,5,"Zbychu");
        game.putCoin(5,1,"Franek");
        game.putCoin(5,1,"Franek");
        assertThat(game.winner,isEmptyOrNullString());
        game.putCoin(3,1,"Franek");
        assertThat(game.winner,is("Franek"));
    }


    @Test
    @DisplayName("Check if sets Winner angled- ")
    public void FallWinAngleDownTest()throws Exception {
        game.createBoard();
        game.putCoin(2,1,"Franek");
        game.putCoin(2,5,"Zbychu");
        game.putCoin(2,1,"Franek");
        game.putCoin(2,5,"Zbychu");

        game.putCoin(3,5,"Zbychu");
        game.putCoin(3,5,"Zbychu");

        game.putCoin(4,1,"Franek");
        game.putCoin(4,5,"Zbychu");

        assertThat(game.winner,isEmptyOrNullString());

        game.putCoin(5,5,"Zbychu");
        game.putCoin(3,5,"Zbychu");

        assertThat(game.winner,is("Zbychu"));
    }

    @Test
    @DisplayName("Check if moves back ")
    public void MoveBack()throws Exception {
        game.createBoard();
        game.putCoin(2,1,"Franek");
        game.putCoin(3,5,"Zbychu");
        game.putCoin(3,1,"Franek");
        game.putCoin(2,5,"Zbychu");
        game.backCoin();
        game.backCoin();

        game.printBoard();
        assertThat(systemOutContent.toString(), endsWith("0 0 1 5 0 0 0"));
         game.backCoin();

        game.printBoard();
        assertThat(systemOutContent.toString(), endsWith("0 0 1 0 0 0 0"));
         game.backCoin();


        game.printBoard();
        assertThat(systemOutContent.toString(), endsWith("0 0 0 0 0 0 0"));
    }
}










