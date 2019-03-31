package Game;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

import static org.assertj.core.api.Assertions.*;

public class PlayerTest {

    private PrintStream originalSystemOut;
    private ByteArrayOutputStream systemOutContent;
    private Board game;
    static Player sut;
    String line;

    @BeforeEach

    public void setUp() {

        originalSystemOut = System.out;

        // given
        systemOutContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOutContent));
    }

    @AfterEach
    void restoreSystemOutStream() {
        System.setOut(originalSystemOut);

        sut = null;

    }


    @Test
    public void testFirstId() {
        sut = new Player("Zbychu", 0);
        assertThat(sut.getPlayerId()).isEqualTo(0);
    }

    @Test
    public void TwoIdsTest() {
        sut = new Player("Zbychu", 0);
        Player sut1 = new Player("Franek", 1);
        assertThat(sut.getPlayerId() & sut1.getPlayerId()).isIn(0, 1);
    }

    @ParameterizedTest //                                     maven: odmowa dostępu!! :<
    @CsvFileSource(resources = "/testPlayers.csv", numLinesToSkip = 1)
    void CSVParamFilePlayerTest(String input, boolean expected) {
        sut = new Player("Zbychu", 0);
        Player sut1 = new Player("Franek", 1);
        assertThat((sut.getPlayer().contains(input)) || ((sut1.getPlayer().contains(input)))).isIn(expected);
    }


    @Test
    public void winsTest() {
        sut = new Player("Zbychu", 1);
        sut.PlayerWin();
        sut.PlayerWin();
        sut.PlayerWin();
        assertThat(sut.getPlayerScore()).isEqualTo(3);
    }

    @Test
    public void TwoIdsGetNameTest() {
        sut = new Player("Zbychu", 1);
        assertThat(sut.getPlayer()).contains("Zbychu");
    }

    @Test
    public void NameSetTest() {
        sut = new Player("Zbychu", 1);
        sut.setPlayer("Karyna");
        assertThat(sut.getPlayer()).contains("Karyna");
    }
}
