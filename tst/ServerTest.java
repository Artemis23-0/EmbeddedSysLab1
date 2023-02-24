import org.junit.Rule;
import org.junit.rules.Timeout;
import org.junit.Assert;
import org.junit.Test;

public class ServerTest {
    @Rule
    public Timeout globalTimeout = Timeout.seconds(5);

    @Test
    public void constructorTest() {
        //Given, When
        Server s = new Server();

        //Then
        Assert.assertEquals(s.getClass(),  Server.class);
    }

    @Test
    public void constructorDefaultsTest() {
        //Given, When
        Server s = new Server();

        //Then
        Assert.assertEquals(0, s.getTotalTips(), 2);
        Assert.assertEquals(1, s.getServerNumber());
    }

    @Test
    public void getServerNumberTest() {
        //Given, When
        Server s = new Server();

        //Then
        Assert.assertEquals(1, s.getServerNumber());
    }

    @Test
    public void setServerNumberTest() {
        //Given
        Server s = new Server();

        //When
        s.setServerNumber(5);

        //Then
        Assert.assertEquals(5, s.getServerNumber());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setServerNumberNegativeTest() {
        //Given
        Server s = new Server();

        //When, Then
        s.setServerNumber(-1);
    }

    @Test
    public void setTotalTipsSingleTest() {
        //Given
        Server s = new Server();

        //When
        s.setTotalTips(5);

        //Then
        Assert.assertEquals(5, s.getTotalTips(), 2);
    }

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

    @Test(expected = IllegalArgumentException.class)
    public void setTotalTipsNegativeTest() {
        //Given
        Server s = new Server();

        //When,then
        s.setTotalTips(-50);
    }

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
