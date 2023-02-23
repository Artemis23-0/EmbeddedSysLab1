import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
public class PartyTest {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(5);

    @Test
    public void constructorTest() {
        Party p = new Party("Name", 8);
        Assert.assertEquals("Name", p.getPartyName());
        Assert.assertEquals(8, p.getPartySize());
        Assert.assertEquals(-1, p.getAssignedServer());
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorBadPartySize() {
        Party p = new Party("Name", 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorBadPartyName() {
        Party p = new Party(null, 8);
    }

    @Test
    public void getAssignedServerTest() {
        //Given, When
        Party p = new Party("Name", 8);
        //Then
        Assert.assertEquals(-1, p.getAssignedServer());
    }

    @Test
    public void setAssignedServerTest() {
        /*Party p = new Party("Name", 8);
        p.setAssignedServer(new Server());
        Assert.assertEquals(5, p.getAssignedServer());*/
    }

    @Test
    public void getPartyNameTest() {
        Party p = new Party("Name", 8);
        Assert.assertEquals("Name", p.getPartyName());
    }

    @Test
    public void getPartySizeTest() {
        Party p = new Party("Name", 8);
        Assert.assertEquals(8, p.getPartySize());
    }


}
