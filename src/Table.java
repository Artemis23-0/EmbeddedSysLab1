/**
 * Homework #3: Restaurant <br>
 * Class: EGR222, Section A <br>
 * Professor Hudnall <br>
 *
 * This class creates a Table with a size, availability, and assigned party
 *
 * @author Kay Karman (754506)
 * @author Riley Spence (Partner)
 * @version 1.0
 * @since   2023-24-02
 *
 */

public class Table {
    //Fields
    private int size;
    private boolean isOccupied;
    private Party assignedParty;

    /**
     * Basic constructor
     * @throws IllegalArgumentException if the table size is 0 or less
     * @param size
     */
    public Table (int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("You cannot have a negative table size");
        }
        this.size = size;
        isOccupied = false;
        assignedParty = null;
    }



    //Getters
    public Boolean isOccupied() {
        return isOccupied;
    }
    public Party getAssignedParty() {
        return assignedParty;
    }
    public int getTableSize() {
        return size;
    }

    //Setters

    /**
     * Sets the availability of the table
     * @param occupied is the new availability of the table
     */
    public void setOccupied(Boolean occupied) {
        isOccupied = occupied;
    }

    /**
     * Assigns a party object to a table and sets the occupied value to true
     * @throws IllegalArgumentException if the party value is null or the party size is greater than the table size.
     * @param party is the party to assign to the table
     */
    public void setAssignedParty(Party party) {
        if (party == null) {
            throw new IllegalArgumentException("No null parties allowed here buddy.");
        }

        if (party.getPartySize() > getTableSize()) {
            throw new IllegalArgumentException("Cannot seat a party larger than the table");
        }

        assignedParty = party;
        isOccupied = true;
    }
}
