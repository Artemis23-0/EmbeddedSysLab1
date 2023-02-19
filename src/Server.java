import java.util.Iterator;

public class Server {
    private double totalTips;
    private int serverNumber = 1;

    public Server() {
        totalTips = 0;
    }

    public double getTotalTips() {
        return totalTips;
    }

    public void setTotalTips(double totalTips) {
        this.totalTips += totalTips;
    }

    public void setServerNumber(int serverNumber) {
        this.serverNumber = serverNumber;
    }

    public int getServerNumber() {
        return serverNumber;
    }


}
