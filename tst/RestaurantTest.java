import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class RestaurantTest {

    @Test
    public void ConstructorTest() {
        Restaurant r = new Restaurant();
        System.out.println(r.getName());
    }

    @Test
    public void getTablesTest() {
        Restaurant r = new Restaurant();
        System.out.println(r.getTables().size());
    }
}
