import org.junit.Rule;
import org.junit.rules.Timeout;
import org.junit.Assert;
import org.junit.Test;

/**
 * Homework #3: Restaurant <br>
 * Class: EGR222, Section A <br>
 * Professor Hudnall <br>
 *
 * This tests all methods in the Table Class using JUnit 4
 *
 * @author Kay Karman (754506)
 * @author Riley Spence (Partner)
 * @version 1.0
 * @since   2023-24-02
 *
 */
public class TableTest {

    //Timeout rule
    @Rule
    public Timeout globalTimeout = Timeout.seconds(5);

    /**
     * Tests that a table is made with the parameter size
     */
    @Test
    public void constructorTest() {
        //Given, when
        Table t = new Table(5);

        //Then
        Assert.assertTrue(t.getClass().equals(Table.class));
        Assert.assertEquals(5, t.getTableSize());
    }

    /**
     * Tests that an exception is thrown if an illegal size is passed in
     */
    @Test(expected=IllegalArgumentException.class)
    public void negativeSizeConstructorTest() {
        //Given, when, then
        Table t = new Table(-1);
    }

    /**
     * Tests that the availability returned is correct
     */
    @Test
    public void isOccupiedFalseTest() {
        //Given, When
        Table t = new Table(5);

        //Then
        Assert.assertFalse(t.isOccupied());
    }

    /**
     * Tests that the availability returned is correct
     */
    @Test
    public void isOccupiedTrueTest() {
        //Given
        Table t = new Table(5);

        //When
        t.setAssignedParty(new Party ("name", 5));

        //Then
        Assert.assertTrue(t.isOccupied());
    }

    /**
     * Tests that availabilty can be changed
     */
    @Test
    public void setOccupiedTest() {
        //Given
        Table t = new Table(5);

        //When
        t.setOccupied(true);

        //Then
        Assert.assertTrue(t.isOccupied());

    }

    /**
     * Tests that the proper table size is returned
     */
    @Test
    public void getTableSizeTest() {
        //Given, when
        Table t = new Table(5);

        //Then
        Assert.assertEquals(5, t.getTableSize());

    }

    /**
     * Tests that a party can be assigned to a table
     */
    @Test
    public void setAssignedPartyTest() {
        //Given
        Table t = new Table(5);
        Party p = new Party("name", 5);

        //When
        t.setAssignedParty(p);

        //Then
        Assert.assertEquals(t.getAssignedParty(), p);
    }

    /**
     * Tests that an exception is thrown if a party is too big for a table
     */
    @Test(expected = IllegalArgumentException.class)
    public void setAssignedPartyTooBigTest() {
        //Given
        Table t = new Table(2);
        Party p = new Party("name", 5);

        //When, then
        t.setAssignedParty(p);
    }

    /**
     * Tests that an exception is thrown if a party is null
     */
    @Test(expected = IllegalArgumentException.class)
    public void setAssignedPartyNullTest() {
        //Given
        Table t = new Table(2);
        Party p = null;

        //When, then
        t.setAssignedParty(p);
    }
}
