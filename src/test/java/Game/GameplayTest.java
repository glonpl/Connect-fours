package Game;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.assertj.core.api.Assertions.*;
public class GameplayTest {

    private PrintStream originalSystemOut;
    private ByteArrayOutputStream systemOutContent;

    static Gameplay sut;

    @BeforeEach
    public void setUp () {

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
        sut= new Gameplay();
sut.player1= new Player("Zbychu",0);
sut.player2= new Player("Franek",1);

        assertThat(sut.PlayerNameTurn(false)).isEqualTo("Franek");
        assertThat(sut.PlayerNameTurn(true)).isEqualTo("Zbychu");//wiem, że powinny być pojedyńcze asercje, ale rozbijanie tego na czesci mija sie z celem i niepotrzebnie poszerza kod
    }


    @Test
    @DisplayName("Test of Function that asks what type of board should be created")
    public void BoardChoiceTest () { //napraw jakos setup
        sut= new Gameplay();
       sut.StandardBoard("w");
        assertThat(systemOutContent.toString()).isEqualTo("\nZła opcja!\n");
    }
    @Test
    public void WinTest() {
//        sut= new Player("Zbychu",0);
//        Player sut1= new Player("Franek",1);
//        assertThat(sut.getPlayerId()&sut1.getPlayerId()).isIn(0,1);
    }
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
