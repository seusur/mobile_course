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

    @Test
    public void testGetClassNumber()
    {
        int lesser_number = 45;
        int actual = Main.getClassNumber();
        Assert.assertTrue(
                this.getGreaterErrorMessage(actual, lesser_number),actual > lesser_number
        );
    }

    public String getGreaterErrorMessage(int actual, int lesser_number)
    {
        return String.format(
                "The actual number %d from getClassNumber method is not greater then number %d.",
                actual, lesser_number
        );
    }
}
