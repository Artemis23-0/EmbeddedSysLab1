import java.util.List;

public class Table {
    private int size;
    private boolean isOccupied;
    private Party assignedParty;

    public Table (int size) {
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
    public void setOccupied(Boolean occupied) {
        isOccupied = occupied;
    }

    public void setAssignedParty(Party party) {
        if (party == null) {
            throw new IllegalArgumentException("No null parties allowed here buddy.");
        }
        assignedParty = party;
        isOccupied = true;
    }
}
