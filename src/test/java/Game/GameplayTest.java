package Game;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class GameplayTest {

    static Gameplay sut;
    private PrintStream originalSystemOut;
    private ByteArrayOutputStream systemOutContent;

    @BeforeEach
    public void setUp() {

        sut = new Gameplay();
        originalSystemOut = System.out;
        systemOutContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOutContent));
    }

    @AfterEach
    void restoreSystemOutStream() {

        System.setOut(originalSystemOut);
        sut = null;
    }


    @Test
    @DisplayName("Test of Function that gives player names, according to turn")
    public void PlayerNameTurnTest() {

        sut.player1 = new Player("Zbychu", 0);
        sut.player2 = new Player("Franek", 1);

        assertThat(sut.PlayerNameTurn(false)).isEqualTo("Franek");
        assertThat(sut.PlayerNameTurn(true)).isEqualTo("Zbychu");//wiem, że powinny być pojedyńcze asercje, ale rozbijanie tego na czesci mija sie z celem i niepotrzebnie poszerza kod
    }


    @Test
    @DisplayName("Test of Function that asks what type of board should be created,checks out string")
    public void BoardChoiceWrongStringOutputMessageCheckTest() {

        sut.StandardBoard("w");
        assertThat(systemOutContent.toString()).isEqualTo("\nZła opcja!\n");
    }

    @DisplayName("Test of Function that asks what type of board should be created, checks returned int")
    @Test
    public void BoardChoiceWrongIntReturnedEqualsZeroTest() {

        sut.StandardBoard("w");
        assertThat(sut.StandardBoard("w")).isIn(0);
    }

    @Test
    @DisplayName("Test of Function that asks what type of board should be created, proper choice 1")
    public void BoardChoice1GivesProper1OutputTest() {

        assertThat(sut.StandardBoard("1")).isIn(1);
    }

    @Test
    @DisplayName("Test of Function that asks what type of board should be created, proper choice 2")
    public void BoardChoice2GivesProper2OutputTest() {

        assertThat(sut.StandardBoard("2")).isGreaterThanOrEqualTo(2);
    }

    @Test
    @DisplayName("Test of dealing with exception of putting out coin from an empty board")
    public void playGoBackCantPullCoinIfNothingThrownInTest() {

        sut.plansza = new Board();
        sut.plansza.createBoard();
        sut.player1 = new Player("Zbychu", 0);
        sut.player2 = new Player("Franek", 1);
        sut.play("c", 1, 5);

        assertThat(systemOutContent.toString()).containsSequence("cofnąć!");
    }


    @Test
    @DisplayName("Test of putting in wrong column throw exception ")
    public void playExceptionTest() {

        sut.plansza = new Board();
        sut.plansza.createBoard();
        sut.player1 = new Player("Zbychu", 0);
        sut.player2 = new Player("Franek", 1);

        assertThatExceptionOfType(NumberFormatException.class).isThrownBy(() ->
        {
            sut.play("k", 1, 5);
        });
    }

    @Test
    @DisplayName("Test of putting1 properly ")
    public void play1ProperTest() {

        sut.plansza = new Board();
        sut.plansza.createBoard();
        sut.player1 = new Player("Zbychu", 0);
        sut.player2 = new Player("Franek", 1);

        assertThat(sut.play("1", 1, 5)).isTrue();
    }


    @Test
    public void TestSaveWithParams() {

        sut.plansza = new Board();
        sut.player1 = new Player("Zbychu", 0);
        sut.player2 = new Player("Franek", 1);
        sut.player1.PlayerWin();
        sut.plansza.createBoard();
        sut.plansza.setBoardSize(4, 4);
        sut.SaveGame("src/test/resources/Tested.txt");
        File actualFile = new File("src/test/resources/Tested.txt");
        File expectedFile = new File("src/test/resources/expected.txt");

        assertThat(actualFile).hasSameContentAs(expectedFile);
    }

    @Test
    public void PickDiscTrueTest() {

        sut.plansza = new Board();
        sut.player1 = new Player("Zbychu", 0);
        sut.player2 = new Player("Franek", 1);
        assertThat(sut.pickDisc("T")).isTrue();
    }

    @Test

    public void PickDiscFalseTest() {

        sut.plansza = new Board();
        sut.player1 = new Player("Zbychu", 0);
        sut.player2 = new Player("Franek", 1);
        assertThat(sut.pickDisc("N")).isFalse();
    }

    @Test
    public void FinishPrintTest() {

        sut.plansza = new Board();
        sut.player1 = new Player("Zbychu", 0);
        sut.player1.PlayerWin();
        sut.player2 = new Player("Franek", 1);
        sut.finish();

        assertThat(systemOutContent.toString()).containsSubsequence("Zbychu:1");
        assertThat(systemOutContent.toString()).containsSubsequence("Franek:0");//2 asserty, bo maven na travisie inaczej sie czepia.

    }
}
//play exception