package Game;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIOException;

public class StatsTest {
    Stats sut = new Stats();
    private PrintStream originalSystemOut;
    private ByteArrayOutputStream systemOutContent;

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

//    @Test
//    @DisplayName("Test if printAll prints as expected")
//    public void PrintAllTest() {
//        Player e = new Player("Zbychu", 0);
//        e.PlayerWin();
//        sut.Add(e);
//        Player f = new Player("Ania", 1);
//        f.PlayerWin();
//        f.PlayerWin();
//        f.PlayerWin();
//        sut.Add(f);
//        Player g = new Player("Franek", 0);
//        sut.Add(g);
//        sut.PrintAll();
//        assertThat(systemOutContent.toString().length()).isEqualTo(28);
//
//
//    }

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

    @Test
    @DisplayName("Test if SaveToFile works")
    public void TofileTest() throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("/src/test/resources/TestLeaderboards.txt"));
            writer.write("");

        } catch (IOException e) {
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
            }
        }
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
        sut.SaveToFile("src/test/resources/TestLeaderboards.txt");
        sut.reset();
        BufferedReader br = new BufferedReader(new FileReader("src/test/resources/TestLeaderboards.txt"));
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        assertThat(systemOutContent.toString()).containsSequence("Zbychu:1");
        writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("src/test/resources/TestLeaderboards.txt"));
            writer.write("");
        } catch (IOException hh) {
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException hh) {
            }
        }
    }

//    @Test
//    @DisplayName("Test if SaveToFile catches Exception")
//    public void TofileExceptionTest() {
//        BufferedWriter writer = null;
//        try {
//            writer = new BufferedWriter(new FileWriter("/src/test/resources/TestLeaderboards.txt"));
//            writer.write("");
//
//        } catch (IOException e) {
//        } finally {
//            try {
//                if (writer != null)
//                    writer.close();
//            } catch (IOException e) {
//            }
//        }
//        Player e = new Player("Zbychu", 0);
//        e.PlayerWin();
//        sut.Add(e);
//        Player f = new Player("Ania", 1);
//        f.PlayerWin();
//        f.PlayerWin();
//        f.PlayerWin();
//        sut.Add(f);
//        Player g = new Player("Franek", 0);
//        sut.Add(g);
//
//        sut.SaveToFile("src/test/resources/Denied.txt");
//      assertThat(systemOutContent.toString()).containsSequence("Failed to save");
//
//        sut.reset();
//
//    }
    @Test
    @DisplayName("Test if ReadFromFile works")
    public void FromFileTest() throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("src/test/resources/TestLeaderboards.txt"));
            writer.write("");

        } catch (IOException e) {
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
            }
        }
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
        sut.SaveToFile("src/test/resources/TestLeaderboards.txt");
        sut.reset();
        sut.ReadFromFile("src/test/resources/TestLeaderboards.txt");

        assertThat(systemOutContent.toString()).containsSequence("Zbychu:1");
        writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("src/test/resources/TestLeaderboards.txt"));
            writer.write("");
        } catch (IOException hh) {
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException hh) {
            }
        }
    }
}
