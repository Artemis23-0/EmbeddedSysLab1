import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Homework #3: Restaurant <br>
 * Class: EGR222, Section A <br>
 * Professor Hudnall <br>
 *
 * This tests all methods in the Restaurant Class using JUnit 4
 *
 * @author Kay Karman (754506)
 * @author Riley Spence (Partner)
 * @version 1.0
 * @since   2023-24-02
 *
 */
public class RestaurantTest {
    //Timeout rule
    @Rule
    public Timeout globalTimeout = Timeout.seconds(5);

    /**
     * Tests that the constructor makes a new Restaurant object
     */
    @Test
    public void ConstructorTest() {
        //Given, When
        Restaurant r = new Restaurant();

        //Then
        Assert.assertEquals(r.getClass(), Restaurant.class);
        Assert.assertEquals(0, r.totalCash(),2);
    }

    /**
     * Tests for construction of empty table list
     */
    @Test
    public void getTablesEmptyTest() {
        Restaurant r = new Restaurant();
        Assert.assertTrue(r.getTables().isEmpty());
    }

    /**
     * Tests for construction of empty server list
     */
    @Test
    public void getServersEmptyTest() {
        Restaurant r = new Restaurant();
        Assert.assertTrue(r.getServers().isEmpty());
    }

    /**
     * Tests for construction of empty party in restaurant list
     */
    @Test
    public void getPartiesInRestaurantEmptyTest() {
        Restaurant r = new Restaurant();
        Assert.assertTrue(r.getPartiesInRestaurant().isEmpty());
    }

    /**
     * Tests for construction of empty waiting list
     */
    @Test
    public void getWaitingListEmptyTest() {
        Restaurant r = new Restaurant();
        Assert.assertTrue(r.getWaitingList().isEmpty());
    }

    /**
     * Tests that values are added properly in the cash register
     */
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

    /**
     * Tests that an error is thrown when a negative value is added to the cash register
     */
    @Test(expected = IllegalArgumentException.class)
    public void addToCashRegisterNegativeTest() {
        //given
        Restaurant r = new Restaurant();
        //when, then
        r.addToCashRegister(-50);
    }

    /**
     * Tests that tables are properly populated
     */
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

    /**
     * Tests that parties get added to the waiting list properly
     */
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

    /**
     * Tests that an exception is thrown if a party is already in the waiting list
     */
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

    /**
     * Tests that an exception is thrown if a party being added to the waiting list is null
     */
    @Test(expected = IllegalArgumentException.class)
    public void addToWaitingListNullTest() {
        //Given
        Restaurant r = new Restaurant();
        Party p = null;

        //When, then
        r.addToWaitingList(p);
    }

    /**
     * Tests that if there is a table with a party in it, then true is returned
     */
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

    /**
     * Tests that false is returned if there are no parties in the restuarant at a table
     */
    @Test
    public void tablesHavePeopleFalseTest() {
        //Given
        Restaurant r = new Restaurant();
        r.producer("/Users/artemis1/Desktop/IdeaProjects/hw3-Artemis23-0/tables.txt");

        //When, Then
        Assert.assertFalse(r.tablesHavePeople());
    }

    /**
     * Tests that the smallest available table for a party is returned
     */
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


    /**
     * Tests that -1 is returned if there is no table to fit a party
     */
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

    /**
     * Tests that the largest table in the restaurant is returned
     */
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

    /**
     * Tests that a server can be added to a restaurant
     */
    @Test
    public void addServerTest() {
        //Given
        Restaurant r = new Restaurant();

        //When
        r.addServer(new Server());

        //Then
        Assert.assertTrue(r.getServers().size() == 1);
    }

    /**
     * Tests that multiple servers can be added to a restaurant
     */
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

    /**
     * Tests that the largest server number in a server list is returned
     */
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

    /**
     * Tests that servers are allocated round-robin style
     */
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

    /**
     * Tests that servers are reallocated round-robin style when a server is dismissed
     */
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
