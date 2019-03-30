package Game;


import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

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
    public void DrawTest () {

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
