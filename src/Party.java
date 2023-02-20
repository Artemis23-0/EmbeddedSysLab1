public class Party {
    private String partyName;
    private int partySize;
    private int assignedServer;

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


    public int getAssignedServer() {
        return assignedServer;
    }

    public void setAssignedServer(int server) {
        assignedServer = server;
    }

    public int getPartySize() {
        return partySize;
    }

    public String getPartyName() {
        return partyName;
    }



}
