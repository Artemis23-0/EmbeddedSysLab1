/**
 * Homework #3: Restaurant <br>
 * Class: EGR222, Section A <br>
 * Professor Hudnall <br>
 *
 * This class creates a Party with a name, assigned server, and size
 *
 * @author Kay Karman (754506)
 * @author Riley Spence (Partner)
 * @version 1.0
 * @since   2023-24-02
 *
 */
public class Party {
    //Fields
    private String partyName;
    private int partySize;
    private int assignedServer;

    /**
     * Basic constructor
     * @throws IllegalArgumentException if the party size is zero or less, or if the party
     * has no name
     * @param partyName is the name of the party
     * @param partySize is the size of the party
     */
    public Party(String partyName, int partySize) {
        if (partySize <= 0) {
            throw new IllegalArgumentException("Must have a party of at least 1");
        }

        if (partyName == null) {
            throw new IllegalArgumentException("Must have a valid party name");
        }

        this.partyName = partyName;
        this.partySize = partySize;
        assignedServer = -1;
    }


    //Basic getters
    public int getPartySize() {
        return partySize;
    }
    public String getPartyName() {
        return partyName;
    }
    public int getAssignedServer() {
        return assignedServer;
    }

    /**
     * Assigns a server number to the party
     * @param server is the server number to be assigned to this party
     */
    public void setAssignedServer(int server) {
        assignedServer = server;
    }



}
