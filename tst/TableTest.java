import org.junit.Rule;
import org.junit.rules.Timeout;
import org.junit.Assert;
import org.junit.Test;

public class TableTest {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(5);

    @Test
    public void constructorTest() {
        //Given, when
        Table t = new Table(5);

        //Then
        Assert.assertTrue(t.getClass().equals(Table.class));
        Assert.assertEquals(5, t.getTableSize());
    }

    @Test(expected=IllegalArgumentException.class)
    public void negativeSizeConstructorTest() {
        //Given, when, then
        Table t = new Table(-1);
    }

    @Test
    public void isOccupiedFalseTest() {
        //Given, When
        Table t = new Table(5);

        //Then
        Assert.assertFalse(t.isOccupied());
    }

    @Test
    public void isOccupiedTrueTest() {
        //Given
        Table t = new Table(5);

        //When
        t.setAssignedParty(new Party ("name", 5));

        //Then
        Assert.assertTrue(t.isOccupied());
    }

    @Test
    public void setOccupiedTest() {
        //Given
        Table t = new Table(5);

        //When
        t.setOccupied(true);

        //Then
        Assert.assertTrue(t.isOccupied());

    }

    @Test
    public void getTableSizeTest() {
        //Given, when
        Table t = new Table(5);

        //Then
        Assert.assertEquals(5, t.getTableSize());

    }

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

    @Test(expected = IllegalArgumentException.class)
    public void setAssignedPartyTooBigTest() {
        //Given
        Table t = new Table(2);
        Party p = new Party("name", 5);

        //When, then
        t.setAssignedParty(p);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setAssignedPartyNullTest() {
        //Given
        Table t = new Table(2);
        Party p = null;

        //When, then
        t.setAssignedParty(p);
    }
}
