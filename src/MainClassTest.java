import org.junit.Assert;
import org.junit.Test;

public class MainClassTest
{

    MainClass Main = new MainClass();

    @Test
    public void testCetLocalNumber()
    {

        int expected = 14;
        int actual = Main.getLocalNumber();
        Assert.assertTrue(
                this.getEqualErrorMessage(actual, expected) ,actual == expected
        );

    }

    public String getEqualErrorMessage(int actual, int expected)
    {
        return String.format(
            "The actual number %d from getLocalNumber method is not equal to the expected number %d.",
            actual, expected
        );
    }
}
