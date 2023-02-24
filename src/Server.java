public class Server {
    private double totalTips;
    private int serverNumber;

    public Server() {
        totalTips = 0;
        serverNumber = 1;
    }

    public double getTotalTips() {
        return totalTips;
    }

    public void setTotalTips(double totalTips) {
        if (totalTips < 0) {
            throw new IllegalArgumentException("Why would you give a negative tip you monster");
        }
        this.totalTips += totalTips;
    }

    public void setServerNumber(int serverNumber) {
        if (serverNumber < 0) {
            throw new IllegalArgumentException("You cannot have a negative server...that's mean");
        }
        this.serverNumber = serverNumber;
    }

    public int getServerNumber() {
        return serverNumber;
    }


}
