import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Restaurant {
    private String name;
    public List<Table> tables;
    private List<Party> waitingList;
    private List<Party> partiesInRestaurant;
    private List<Server> servers;
    private static double cashRegister = 0;

    public Restaurant() {
        partiesInRestaurant = new ArrayList<>();
        tables = new ArrayList<>();
        waitingList = new ArrayList<>();
        servers = new ArrayList<>();
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

    public boolean tablesHavePeople() {
        for (Table t : tables) {
            if (t.isOccupied()) {
                return  true;
            }
        }

        return false;
    }

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

    public void addServer(Server server) {
        if (servers.isEmpty()) {
            servers.add(server);
        } else {
            server.setServerNumber(getHighestServerNumber() + 1);
            servers.add(server);
        }
    }

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

    public void allocateServers() {
        Iterator<Server> iterator = robinIterator();
        for (int i = 0; i < partiesInRestaurant.size(); i++) {
            if (!waitingList.contains(partiesInRestaurant.get(i))) {
                partiesInRestaurant.get(i).setAssignedServer(iterator.next().getServerNumber());
            }
        }
    }

    public void cashOut() {
        servers.remove(getNextServerToCashOut());
        allocateServers();
    }

    //FIXME
    public int getNextServerToCashOut() {
        return servers.size() - 1;
    }

    public int getSmallestAvailableTable (int partySize) {
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

    public int getLargestTable() {
        int largestTable = 0;
        for (Table t: tables) {
            if (t.getTableSize() > largestTable) {
                largestTable = t.getTableSize();
            }
        }
        return largestTable;
    }

    public double totalServerTips() {
        double tips = 0;
        for (Server s: servers) {
            tips += s.getTotalTips();
        }
        return tips;
    }

    public double totalCash() {
        return cashRegister;
    }

    public void addToCashRegister(double cash) {
        cashRegister += (1.10 * cash);
    }

    public void addToWaitingList(Party party) {
        waitingList.add(party);
    }

    public String getName() {
        return name;
    }


    public List<Table> getTables() {
        return tables;
    }


}
