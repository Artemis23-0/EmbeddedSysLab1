import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class RestaurantTest {
    @Rule
    public Timeout globalTimeout = Timeout.seconds(5);

    @Test
    public void ConstructorTest() {
        //Given, When
        Restaurant r = new Restaurant();

        //Then
        Assert.assertEquals(r.getClass(), Restaurant.class);
        Assert.assertEquals(0, r.totalCash(),2);
    }

    @Test
    public void getTablesEmptyTest() {
        Restaurant r = new Restaurant();
        Assert.assertTrue(r.getTables().isEmpty());
    }

    @Test
    public void getServersEmptyTest() {
        Restaurant r = new Restaurant();
        Assert.assertTrue(r.getServers().isEmpty());
    }

    @Test
    public void getPartiesInRestaurantEmptyTest() {
        Restaurant r = new Restaurant();
        Assert.assertTrue(r.getPartiesInRestaurant().isEmpty());
    }

    @Test
    public void getWaitingListEmptyTest() {
        Restaurant r = new Restaurant();
        Assert.assertTrue(r.getWaitingList().isEmpty());
    }

    @Test
    public void addToCashRegisterTest() {
        //given
        Restaurant r = new Restaurant();

        //when
        r.addToCashRegister(50);
        r.addToCashRegister(100);

        //Then
        Assert.assertEquals((50*1.10) + (100*1.10), r.totalCash(),20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addToCashRegisterNegativeTest() {
        //given
        Restaurant r = new Restaurant();
        //when, then
        r.addToCashRegister(-50);
    }

    @Test
    public void producerTest() {
        //Given
        Restaurant r = new Restaurant();

        //When
        r.producer("/Users/artemis1/Desktop/IdeaProjects/hw3-Artemis23-0/tables.txt");

        //Then
        Assert.assertEquals("Joe's Restaurant", r.getName());
        Assert.assertEquals(8, r.getTables().size());
    }

    @Test
    public void addToWaitingListTest() {
        //Given
        Restaurant r = new Restaurant();
        Party p = new Party("Waiting", 5);

        //When
        r.getWaitingList().add(p);

        //Then
        Assert.assertTrue(r.getWaitingList().contains(p));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addToWaitingListAlreadyExistsTest() {
        //Given
        Restaurant r = new Restaurant();
        Party p = new Party("Waiting", 5);

        //When
        r.addToWaitingList(p);

        //Then
        r.addToWaitingList(p);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addToWaitingListNullTest() {
        //Given
        Restaurant r = new Restaurant();
        Party p = null;

        //When, then
        r.addToWaitingList(p);
    }

    @Test
    public void tablesHavePeopleTrueTest() {
        //Given
        Restaurant r = new Restaurant();
        r.producer("/Users/artemis1/Desktop/IdeaProjects/hw3-Artemis23-0/tables.txt");

        //When
        r.getTables().get(5).setOccupied(true);

        //Then
        Assert.assertTrue(r.tablesHavePeople());
    }

    @Test
    public void tablesHavePeopleFalseTest() {
        //Given
        Restaurant r = new Restaurant();
        r.producer("/Users/artemis1/Desktop/IdeaProjects/hw3-Artemis23-0/tables.txt");

        //When, Then
        Assert.assertFalse(r.tablesHavePeople());
    }

    @Test
    public void getSmallestAvailableTableIsTableTest() {
        //Given
        Restaurant r = new Restaurant();
        r.producer("/Users/artemis1/Desktop/IdeaProjects/hw3-Artemis23-0/tables.txt");

        //When
        int table = r.getSmallestAvailableTable(3);

        //Then
        Assert.assertEquals(0, table);
    }

    @Test
    public void getSmallestAvailableTableIsNoTableTest() {
        //Given
        Restaurant r = new Restaurant();
        r.producer("/Users/artemis1/Desktop/IdeaProjects/hw3-Artemis23-0/tables.txt");

        //When
        int table = r.getSmallestAvailableTable(50);

        //Then
        Assert.assertEquals(-1, table);

    }

    @Test
    public void getLargestTableTest() {
        //Given
        Restaurant r = new Restaurant();
        r.producer("/Users/artemis1/Desktop/IdeaProjects/hw3-Artemis23-0/tables.txt");

        //When

        int largestTableSize = r.getLargestTable();

        //Then
        Assert.assertEquals(8, largestTableSize);
    }

    @Test
    public void addServerTest() {
        //Given
        Restaurant r = new Restaurant();

        //When
        r.addServer(new Server());

        //Then
        Assert.assertTrue(r.getServers().size() == 1);
    }

    @Test
    public void addServerMoreThanOneTest() {
        //Given
        Restaurant r = new Restaurant();

        //When
        r.addServer(new Server());
        r.addServer(new Server());

        //Then
        Assert.assertTrue(r.getServers().size() == 2);
    }

    @Test
    public void getHighestServerTest() {
        //Given
        Restaurant r = new Restaurant();

        //When
        r.addServer(new Server());
        r.addServer(new Server());

        //Then
        Assert.assertTrue(r.getHighestServerNumber() == 2);
    }

    @Test
    public void allocateServersTest() {
        //Given
        Restaurant r = new Restaurant();
        r.producer("/Users/artemis1/Desktop/IdeaProjects/hw3-Artemis23-0/tables.txt");


        //When
        r.addServer(new Server());
        r.addServer(new Server());
        r.addServer(new Server());

        r.getPartiesInRestaurant().add(new Party("Party 1", 3));
        r.getPartiesInRestaurant().add(new Party("Party 2", 4));
        r.getPartiesInRestaurant().add(new Party("Party 3", 7));
        r.getPartiesInRestaurant().add(new Party("Party 4", 2));

        r.allocateServers();

        Assert.assertTrue(r.getPartiesInRestaurant().get(0).getAssignedServer() == 1);
        Assert.assertTrue(r.getPartiesInRestaurant().get(1).getAssignedServer() == 2);
        Assert.assertTrue(r.getPartiesInRestaurant().get(2).getAssignedServer() == 3);
        Assert.assertTrue(r.getPartiesInRestaurant().get(3).getAssignedServer() == 1);
    }

    @Test
    public void reAllocateServersTest() {
        //Given
        Restaurant r = new Restaurant();
        r.producer("/Users/artemis1/Desktop/IdeaProjects/hw3-Artemis23-0/tables.txt");


        //When
        r.addServer(new Server());
        r.addServer(new Server());
        r.addServer(new Server());

        r.getPartiesInRestaurant().add(new Party("Party 1", 3));
        r.getPartiesInRestaurant().add(new Party("Party 2", 4));
        r.getPartiesInRestaurant().add(new Party("Party 3", 7));

        r.allocateServers();

        r.reallocateServers(1);

        Assert.assertTrue(r.getPartiesInRestaurant().get(0).getAssignedServer() == 2);
        Assert.assertTrue(r.getPartiesInRestaurant().get(1).getAssignedServer() == 2);
        Assert.assertTrue(r.getPartiesInRestaurant().get(2).getAssignedServer() == 3);
    }

}
