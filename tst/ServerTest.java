import org.junit.Rule;
import org.junit.rules.Timeout;
import org.junit.Assert;
import org.junit.Test;

/**
 * Homework #3: Restaurant <br>
 * Class: EGR222, Section A <br>
 * Professor Hudnall <br>
 *
 * This tests all methods in the Server Class using JUnit 4
 *
 * @author Kay Karman (754506)
 * @author Riley Spence (Partner)
 * @version 1.0
 * @since   2023-24-02
 *
 */
public class ServerTest {
    //Timeout rule
    @Rule
    public Timeout globalTimeout = Timeout.seconds(5);

    /**
     * Tests that a server is constructed with a basic server number
     */
    @Test
    public void constructorTest() {
        //Given, When
        Server s = new Server();

        //Then
        Assert.assertEquals(s.getClass(),  Server.class);
    }

    /**
     * Tests the server number and tip defaults of a newly constructed server
     */
    @Test
    public void constructorDefaultsTest() {
        //Given, When
        Server s = new Server();

        //Then
        Assert.assertEquals(0, s.getTotalTips(), 2);
        Assert.assertEquals(1, s.getServerNumber());
    }

    /**
     * Tests that the proper server number is returned
     */
    @Test
    public void getServerNumberTest() {
        //Given, When
        Server s = new Server();

        //Then
        Assert.assertEquals(1, s.getServerNumber());
    }

    /**
     * Tests that a server number can be changed
     */
    @Test
    public void setServerNumberTest() {
        //Given
        Server s = new Server();

        //When
        s.setServerNumber(5);

        //Then
        Assert.assertEquals(5, s.getServerNumber());
    }

    /**
     * Tests that an exception is thrown if a server number is being set to a negative number
     */
    @Test(expected = IllegalArgumentException.class)
    public void setServerNumberNegativeTest() {
        //Given
        Server s = new Server();

        //When, Then
        s.setServerNumber(-1);
    }

    /**
     * Tests that tips are added properly to a server's tips
     */
    @Test
    public void setTotalTipsSingleTest() {
        //Given
        Server s = new Server();

        //When
        s.setTotalTips(5);

        //Then
        Assert.assertEquals(5, s.getTotalTips(), 2);
    }

    /**
     * Tests that tips are added properly to a server's tips
     */
    @Test
    public void setTotalTipsMultipleTest() {
        //Given
        Server s = new Server();

        //When
        s.setTotalTips(5);
        s.setTotalTips(15);

        //Then
        Assert.assertEquals(20, s.getTotalTips(), 2);

    }

    /**
     * Tests that an exception is thrown if an illegal tip value is passed
     */
    @Test(expected = IllegalArgumentException.class)
    public void setTotalTipsNegativeTest() {
        //Given
        Server s = new Server();

        //When,then
        s.setTotalTips(-50);
    }

    /**
     * Tests that the server's tips can be retrieved
     */
    @Test
    public void getTotalTipsTest() {
        //Given
        Server s = new Server();

        //When
        s.setTotalTips(5);
        s.setTotalTips(15);

        double totalTips = s.getTotalTips();

        //Then
        Assert.assertEquals(20, totalTips, 2);
    }

}
