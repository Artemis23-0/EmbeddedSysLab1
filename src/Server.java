/**
 * Homework #3: Restaurant <br>
 * Class: EGR222, Section A <br>
 * Professor Hudnall <br>
 *
 * This class creates a server that starts off with no tips and an initial server number of 1 <br>
 *
 *
 * @author Kay Karman (754506)
 * @author Riley Spence (Partner)
 * @version 1.0
 * @since   2023-24-02
 */
public class Server {
    //Fields
    private double totalTips;
    private int serverNumber;

    //Constructor
    public Server() {
        totalTips = 0;
        serverNumber = 1;
    }

    /**
     * @return the total tips belonging to this server
     */
    public double getTotalTips() {
        return totalTips;
    }

    /**
     * Adds a tip value to the server's total tips
     * @throws IllegalArgumentException if the totalTips value is less than zero
     * @param totalTips is the amount of tip money to be added to the server's total tips
     */
    public void setTotalTips(double totalTips) {
        if (totalTips < 0) {
            throw new IllegalArgumentException("Why would you give a negative tip you monster");
        }
        this.totalTips += totalTips;
    }

    /**
     * @throws IllegalArgumentException if the serverNumber value is less than 0
     * @param serverNumber is the new server number to set this object's server number to
     */
    public void setServerNumber(int serverNumber) {
        if (serverNumber < 0) {
            throw new IllegalArgumentException("You cannot have a negative server...that's mean");
        }
        this.serverNumber = serverNumber;
    }

    //Basic getter
    public int getServerNumber() {
        return serverNumber;
    }


}
