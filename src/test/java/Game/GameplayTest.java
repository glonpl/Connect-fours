package Game;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.MarshalException;
import java.io.*;
import java.util.StringTokenizer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class GameplayTest {

    private PrintStream originalSystemOut;
    private ByteArrayOutputStream systemOutContent;
    String line;
    static Gameplay sut;

    @BeforeEach
    public void setUp () {
        sut= new Gameplay();
        originalSystemOut = System.out;

        // given
        systemOutContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOutContent));
    }
    @AfterEach
    void restoreSystemOutStream () {
        System.setOut(originalSystemOut);

        sut = null;

    }


    @Test
    @DisplayName("Test of Function that gives player names, according to turn")
    public void PlayerNameTurnTest () {

sut.player1= new Player("Zbychu",0);
sut.player2= new Player("Franek",1);

        assertThat(sut.PlayerNameTurn(false)).isEqualTo("Franek");
        assertThat(sut.PlayerNameTurn(true)).isEqualTo("Zbychu");//wiem, że powinny być pojedyńcze asercje, ale rozbijanie tego na czesci mija sie z celem i niepotrzebnie poszerza kod
    }


    @Test
    @DisplayName("Test of Function that asks what type of board should be created")
    public void BoardChoiceWrongTest () { //napraw jakos setup

       sut.StandardBoard("w");
        assertThat(systemOutContent.toString()).isEqualTo("\nZła opcja!\n");
    }
    @Test
    public void BoardChoiceWrong2Test () { //napraw jakos setup

        sut.StandardBoard("w");
        assertThat(sut.StandardBoard("w")).isIn(0);
    }
    @Test
    @DisplayName("Test of Function that asks what type of board should be created")
    public void BoardChoice1Test () {
         assertThat(sut.StandardBoard("1")).isIn(1);
    }
    @Test
    @DisplayName("Test of Function that asks what type of board should be created")
    public void BoardChoice2Test () {
          assertThat(sut.StandardBoard("2")).isGreaterThanOrEqualTo(2);
    }

    @Test
    public void playGoBackTest()throws Exception {
        sut.plansza=new Board();
        sut.plansza.createBoard();
        sut.player1= new Player("Zbychu",0);
        sut.player2= new Player("Franek",1);
        sut.play("c",1,5);

        assertThat(systemOutContent.toString()).containsSequence("cofnąć!");


    }
    @Test
    public void playExceptionTest() {
        sut.plansza=new Board();
        sut.plansza.createBoard();
        sut.player1= new Player("Zbychu",0);
        sut.player2= new Player("Franek",1);

        assertThatExceptionOfType(NumberFormatException.class).isThrownBy(()->
        {sut.play("k",1,5);});


    }
    @Test
    public void play1Test()throws Exception  {
       sut.plansza=new Board();
        sut.plansza.createBoard();
        sut.player1= new Player("Zbychu",0);
        sut.player2= new Player("Franek",1);

       assertThat(sut.play("1",1,5)).isTrue();


    }




    @Test
    public void TestSaveWithParams() throws Exception
    {
        sut.plansza=new Board();
        sut.player1= new Player("Zbychu",0);
        sut.player2= new Player("Franek",1);
        sut.player1.PlayerWin();
        sut.plansza.createBoard();
        sut.plansza.setBoardSize(4,4);
        sut.SaveGame("Tested.txt");

        File actualFile = new File("Tested.txt");
        File expectedFile = new File("expected.txt");
        assertThat(actualFile).hasSameContentAs(expectedFile);
    }

    //matchplaytest
//gameplaysetup
    @Test
    public void Point4WinnerTest() {
//        sut= new Player("Zbychu",1);
//        sut.PlayerWin();
//        sut.PlayerWin();
//        sut.PlayerWin();
//        assertThat(sut.getPlayerScore()).isEqualTo(3);
    }
//    @Test
//    public void TwoIdsGetNameTest() {
////        sut= new Player("Zbychu",1);
////        assertThat(sut.getPlayer()).contains("Zbychu");
//    }
}
