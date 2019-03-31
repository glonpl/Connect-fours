package Game;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class StatsTest {
    private PrintStream originalSystemOut;
    private ByteArrayOutputStream systemOutContent;
    Stats sut = new Stats();

    @BeforeEach
    public void setUp() {
        sut.reset();
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
    @DisplayName("Test printing function with one player on list")
    public void PrintAll1Test() {

        Player e = new Player("Zbychu", 1);
        e.PlayerWin();
        sut.Add(e);
        sut.PrintAll();
        assertThat(systemOutContent.toString()).containsSequence("Zbychu:1");


    }


    @Test
    @DisplayName("Test if Add adds player to list")
    public void AddTest() {

        Player e = new Player("Zbychu", 1);
        e.PlayerWin();
        sut.Add(e);
        assertThat(sut.list.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("Test if printAll prints as expected")
    public void PrintAllTest() {
        Player e = new Player("Zbychu", 0);
        e.PlayerWin();
        sut.Add(e);
        Player f = new Player("Ania", 1);
        f.PlayerWin();
        f.PlayerWin();
        f.PlayerWin();
        sut.Add(f);
        Player g = new Player("Franek", 0);
        sut.Add(g);
        sut.PrintAll();
        assertThat(systemOutContent.toString().length()).isEqualTo(28);


    }

    @Test
    @DisplayName("Test if reset works properly")
    public void resetTest() {
        Player e = new Player("Zbychu", 0);
        e.PlayerWin();
        sut.Add(e);
        Player f = new Player("Ania", 1);
        f.PlayerWin();
        f.PlayerWin();
        f.PlayerWin();
        sut.Add(f);
        Player g = new Player("Franek", 0);
        sut.Add(g);
        sut.PrintAll();
        assertThat(sut.list.isEmpty()).isFalse();
        sut.reset();
        assertThat(sut.list.isEmpty()).isTrue();
    }


}
