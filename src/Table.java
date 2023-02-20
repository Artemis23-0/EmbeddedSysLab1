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


    public Party getAssignedParty() {
        return assignedParty;
    }


    //Getters
    public Boolean isOccupied() {
        return isOccupied;
    }

    public int getTableSize() {
        return size;
    }

    //Setters
    public void setOccupied(Boolean occupied) {
        isOccupied = occupied;
    }

    public void setAssignedParty(Party party) {
        assignedParty = party;
        isOccupied = true;
    }
}
