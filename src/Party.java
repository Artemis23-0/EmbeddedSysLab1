public class Party {
    String partyName;
    int partySize;
    Boolean isSeated;
    int assignedServer;

    public Party(String partyName, int partySize) {
        this.partyName = partyName;
        this.partySize = partySize;
        isSeated = false;
        assignedServer = -1;
    }

    public Boolean getSeated() {
        return isSeated;
    }

    public void setSeated(Boolean seated) {
        isSeated = seated;
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
