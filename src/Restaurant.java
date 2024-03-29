import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Homework #3: Restaurant <br>
 * Class: EGR222, Section A <br>
 * Professor Hudnall <br>
 *
 * This class manages the tables, server, and party classes to interface with a text user interface
 * to manage a fictional restaurant.
 *
 * @author Kay Karman (754506)
 * @author Riley Spence (Partner)
 * @version 1.0
 * @since   2023-24-02

 *
 */
public class Restaurant {
    //Fields
    private String name;
    private List<Table> tables;
    private List<Party> waitingList;
    private List<Party> partiesInRestaurant;
    private List<Server> servers;
    private double cashRegister;

    //Constructor
    public Restaurant() {
        partiesInRestaurant = new ArrayList<>();
        tables = new ArrayList<>();
        waitingList = new ArrayList<>();
        servers = new ArrayList<>();
        cashRegister = 0;
    }

    //Basic Getters
    public String getName() {
        return name;
    }
    public List<Table> getTables() {
        return tables;
    }
    public List<Party> getWaitingList() {
        return waitingList;
    }
    public List<Party> getPartiesInRestaurant() {
        return partiesInRestaurant;
    }
    public List<Server> getServers() {
        return servers;
    }
    public double totalCash() {
        return cashRegister;
    }

    /**
     * Adds a certain amount of money to the cash register
     * @throws IllegalArgumentException if the cash to be added is less than 0
     * @param cash is the amount of money to add to the cash register
     */
    public void addToCashRegister(double cash) {
        if (cash < 0) {
            throw new IllegalArgumentException("Stealing is wrong you pleb.");
        }
        cashRegister += (1.10 * cash);
    }

    /**
     * Adds a party to the waiting list
     * @throws IllegalArgumentException if the waiting list already contains a party or
     * if the party is null
     * @param party is the party to add to the waiting list
     */
    public void addToWaitingList(Party party) {
        if (waitingList.contains(party)) {
            throw new IllegalArgumentException("Already contains this party");
        }
        if ((party == null)) {
            throw new IllegalArgumentException("Cannot add this party to the waiting list");
        }

        waitingList.add(party);
    }

    //Everything related to table

    /**
     * Produces reads a file of table sizes and creates a new table from the table size
     * @param filename is the file to get all of the table sizes from
     */
    public void producer(String filename) {
        try {
            Scanner input = new Scanner(new File(filename));
            name = input.nextLine();
            while (input.hasNextInt()) {
                int currTableSize = input.nextInt();
                tables.add(new Table(currTableSize));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return if any of the tables in the restaurant have people at them
     */
    public boolean tablesHavePeople() {
        for (Table t : tables) {
            if (t.isOccupied()) {
                return true;
            }
        }

        return false;
    }

    /**
     * This is a helper method to get the index of the smallest possible table that is available to seat
     * a party of a certain size
     * @param partySize the size of the party to seat
     * @return the INDEX of the first smallest table in the list that can seat this party, -1 if there is no
     * table that can fit a party of that size
     */
    public int getSmallestAvailableTable (int partySize) {
        //Find smallest available table size
        int smallestTableSize = -1;
        for (int i = 0; i < tables.size(); i++) {
            Table currTable = tables.get(i);
            if (!currTable.isOccupied()) {
                if (currTable.getTableSize() >= partySize) {
                    if (smallestTableSize == -1) {
                        smallestTableSize = currTable.getTableSize();
                    }
                    if ((smallestTableSize != -1) && (smallestTableSize > currTable.getTableSize())) {
                        smallestTableSize = currTable.getTableSize();
                    }
                }
            }
        }

        //Find that table of that table size
        if (smallestTableSize != -1) {
            for (int i = 0; i < tables.size(); i++) {
                Table currTable = tables.get(i);
                if (!currTable.isOccupied()) {
                    if (currTable.getTableSize() == smallestTableSize) {
                        return i;
                    }
                }
            }
        }

        return -1;
    }

    /**
     * @return the table with the largest size in the restaurant
     */
    public int getLargestTable() {
        int largestTable = 0;
        for (Table t: tables) {
            if (t.getTableSize() > largestTable) {
                largestTable = t.getTableSize();
            }
        }
        return largestTable;
    }

    //Everything related to Server



    /**
     * Adds a server to the list of servers in the restaurant
     * @param server is the server to add to the restaurant
     */
    public void addServer(Server server) {
        if (servers.isEmpty()) {
            servers.add(server);
        } else {
            server.setServerNumber(getHighestServerNumber() + 1);
            servers.add(server);
        }
    }

    /**
     * Gets the highest server number in the list so that the next server to come in
     * can get a server number that is one higher
     * @return the highest server number in the list of servers
     */
    public int getHighestServerNumber() {
        int highestServer = 0;
        for (Server s: servers) {
            if (s.getServerNumber() > highestServer) {
                highestServer = s.getServerNumber();
            }
        }
        return highestServer;
    }


    private int index = 0;

    /**
     * Helper to assign servers. Acts as a continuous iterator that resets the index
     * @return a custom iterator for round-robin assignment
     */
    public Iterator<Server> robinIterator() {
        return new Iterator<Server>() {
            @Override
            public boolean hasNext() {
                return true;
            }
            @Override
            public Server next() {
                if (index >= servers.size()) {
                    index = 0;
                }

                Server res = servers.get(index++);
                return res;
            }
        };
    }

    Iterator<Server> iterator = robinIterator();
    Server currentServer = null;
    /**
     * Allocates servers to a party round-robin style
     */
    public void allocateServers() {
        for (int i = 0; i < partiesInRestaurant.size(); i++) {
            if (!waitingList.contains(partiesInRestaurant.get(i)) && (partiesInRestaurant.get(i).getAssignedServer() < 0)) {
                currentServer = iterator.next();
                partiesInRestaurant.get(i).setAssignedServer(currentServer.getServerNumber());
            }
        }
    }

    /**
     * This method reallocates parties that belonged to a server that has been dismissed
     * @param serverNumber is the server that is leaving
     */
    public void reallocateServers(int serverNumber) {
        for (int i = 0; i < partiesInRestaurant.size(); i++) {
            if (!waitingList.contains(partiesInRestaurant.get(i))) {
                if (partiesInRestaurant.get(i).getAssignedServer() == serverNumber) {
                    currentServer = iterator.next();
                    if (currentServer.getServerNumber() != serverNumber) {
                        partiesInRestaurant.get(i).setAssignedServer(currentServer.getServerNumber());
                    } else {
                        currentServer = iterator.next();
                        partiesInRestaurant.get(i).setAssignedServer(currentServer.getServerNumber());
                    }
                }
            }
        }
    }

    /**
     * @return the next server to remove
     */
    public Server getNextServerToCashOut() {
        return iterator.next();
    }


}
