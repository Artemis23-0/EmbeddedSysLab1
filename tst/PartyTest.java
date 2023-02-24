import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Homework #3: Restaurant <br>
 * Class: EGR222, Section A <br>
 * Professor Hudnall <br>
 *
 * This tests all methods in the Party Class using JUnit 4
 *
 * @author Kay Karman (754506)
 * @author Riley Spence (Partner)
 * @version 1.0
 * @since   2023-24-02
 *
 */
public class PartyTest {

    //Timeout rule
    @Rule
    public Timeout globalTimeout = Timeout.seconds(5);

    /**
     * Tests that a Party is properly constructed with parameter name and size values
     */
    @Test
    public void constructorTest() {
        Party p = new Party("Name", 8);
        Assert.assertEquals("Name", p.getPartyName());
        Assert.assertEquals(8, p.getPartySize());
        Assert.assertEquals(-1, p.getAssignedServer());
    }

    /**
     * Tests that an exception is thrown if an illegal party size is passed
     */
    @Test(expected = IllegalArgumentException.class)
    public void constructorBadPartySize() {
        Party p = new Party("Name", 0);
    }

    /**
     * Tests that an exception is thrown if an illegal party name is passed
     */
    @Test(expected = IllegalArgumentException.class)
    public void constructorBadPartyName() {
        Party p = new Party(null, 8);
    }

    /**
     * Tests that the proper server value for the party is returned
     */
    @Test
    public void getAssignedServerTest() {
        //Given, When
        Party p = new Party("Name", 8);
        //Then
        Assert.assertEquals(-1, p.getAssignedServer());
    }

    /**
     * Tests that the server for a party can be changed
     */
    @Test
    public void setAssignedServerTest() {
        Party p = new Party("Name", 8);
        p.setAssignedServer(5);
        Assert.assertEquals(5, p.getAssignedServer());
    }

    /**
     * Tests that the party name can be retrieved
     */
    @Test
    public void getPartyNameTest() {
        Party p = new Party("Name", 8);
        Assert.assertEquals("Name", p.getPartyName());
    }

    /**
     * Tests that the party size can be retrieved
     */
    @Test
    public void getPartySizeTest() {
        Party p = new Party("Name", 8);
        Assert.assertEquals(8, p.getPartySize());
    }


}
