import static junit.framework.Assert.assertEquals;

import org.dojo.Till;
import org.junit.Before;
import org.junit.Test;

public class Till_Test extends Till {

    public static final String CAN_O_SOUP = "can o soup";

    public static final String BEANS = "beans";

    public static final String DETERGENT = "detergent";


    @Before
    public void setup() {
        price.put(BEANS, 33);
        price.put(DETERGENT, 128);

    }


    @Test
    public void aHundredCentsReturnsDollar() {
        int price = 100;

        addPriceToTotal(price);
        assertEquals("1.00", getTotal());
    }


    @Test
    public void adds2Prices() {
        checkAddition("3.50", 100, 250);
    }


    @Test
    public void adds4Prices() {
        checkAddition("1.41", 73, 50, 7, 11);
    }


    @Test
    public void adds3Prices() {
        checkAddition("1.24", 73, 50, 1);
    }


    @Test
    public void addNamedItem() {
        addItem(BEANS);
        assertEquals("0.33", getTotal());
    }


    @Test
    public void add2NamedItems() {
        addItem(BEANS);
        addItem(DETERGENT);
        assertEquals("1.61", getTotal());
    }


    @Test
    public void changedNamedItemWithNewPrice() {
        setPrice(BEANS, 69);
        addItem(BEANS);
        assertEquals("should have changed to new price", "0.69", getTotal());
    }


    @Test
    public void addNamedItemWithSetPrice() {
        createItem(CAN_O_SOUP, 102);
        addItem(CAN_O_SOUP);
        assertEquals("should can o soup at", "1.02", getTotal());
    }


    @Test
    public void check3SoupFor2() {
        addSpecial(CAN_O_SOUP, 3);
        createItem(CAN_O_SOUP, 102);
        addItem(CAN_O_SOUP, 3);
        assertEquals("should have 3 can o soup for the price of 2", "2.04", getTotal());
    }


    @Test
    public void check4SoupFor3() {
        addSpecial(CAN_O_SOUP, 4);
        createItem(CAN_O_SOUP, 102);
        addItem(CAN_O_SOUP, 4);
        assertEquals("should have 4 can o soup for the price of 3", "3.06", getTotal());
    }


    @Test
    public void check6SoupFor4() {
        addSpecial(CAN_O_SOUP, 3);
        createItem(CAN_O_SOUP, 102);
        addItem(CAN_O_SOUP, 6);
        assertEquals("should have 6 can o soup for the price of 4", "4.08", getTotal());
    }


    @Test
    public void checkBeans5for4andSoup3For2() {
        addSpecial(CAN_O_SOUP, 3);
        addSpecial(BEANS, 5);
        createItem(CAN_O_SOUP, 102);
        createItem(BEANS, 66);

        addItem(CAN_O_SOUP, 3);

        addItem(BEANS, 5);
        assertEquals("should have 3 can o soup for 2 and 5 beans for 4 ", "4.68", getTotal());
    }


    @Test
    public void testPrintReceipt() {
        createItem(BEANS, 66);
        addItem(BEANS);
        assertEquals("should be a receipt", "1 * beans 0.66\n==========\nTotal 0.66", printReceipt());
    }


    @Test
    public void test2DetergentPrintReceipt() {
        addItem(DETERGENT, 2);
        assertEquals("should be a receipt for 2 * detergent", "2 * detergent 1.28\n==========\nTotal 2.56", printReceipt());
    }


    @Test
    public void check5BeansFor4() {
        addSpecial(BEANS, 5);
        createItem(BEANS, 66);
        addItem(BEANS, 5);
        assertEquals("should have 5 beans for the price of 4", "2.64", getTotal());
    }
}
