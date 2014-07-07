package Demo;

/**
 * Created by ljxi_828 on 6/14/14.
 */
public class DepartureModel {

    private String routeName;
    private String lineName;
    private int minutes;

    public DepartureModel() {
        this.routeName = "";
        this.lineName = "";
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
}
