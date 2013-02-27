import static junit.framework.Assert.assertEquals;

import org.dojo.Till;
import org.junit.Before;
import org.junit.Test;

public class Till_Test extends Till {

    public static final String SOUP = "soup";

    public static final String BEANS = "beans";

    public static final String DETERGENT = "detergent";

    // todo: 5 for 3


    @Before
    public void setup() {
        price.put(BEANS, 33);
        price.put(DETERGENT, 128);

    }


    @Test
    public void aHundredCentsReturnsDollar() {
        int price = 100;

        addPriceToTotal(price);
        assertEquals(100, getTotalInCents());
    }


    @Test
    public void adds2Prices() {
        checkAddition(350, 100, 250);
    }


    @Test
    public void adds4Prices() {
        checkAddition(141, 73, 50, 7, 11);
    }


    @Test
    public void adds3Prices() {
        checkAddition(124, 73, 50, 1);
    }


    @Test
    public void addNamedItem() {
        addItem(BEANS);
        assertEquals(33, getTotalInCents());
    }


    @Test
    public void add2NamedItems() {
        addItem(BEANS);
        addItem(DETERGENT);
        assertEquals(161, getTotalInCents());
    }


    @Test
    public void changedNamedItemWithNewPrice() {
        setPrice(BEANS, 69);
        addItem(BEANS);
        assertEquals("should have changed to new price", 69, getTotalInCents());
    }


    @Test
    public void addNamedItemWithSetPrice() {
        checkNumberOfItemsWithPriceCost(1, SOUP, 102, 102 * 1);
    }


    private void checkNumberOfItemsWithPriceCost(int number, String item, int price, int expectedPrice) {
        createItem(item, price);
        addItem(item, number);
        assertEquals("should have " + item + " at", expectedPrice, getTotalInCents());
    }


    @Test
    public void check3SoupFor2() {
        addSpecial(SOUP, 3);
        checkNumberOfItemsWithPriceCost(3, SOUP, 102, 102 * 2);
    }


    @Test
    public void check4SoupFor3() {
        addSpecial(SOUP, 4);
        checkNumberOfItemsWithPriceCost(4, SOUP, 102, 102 * 3);
    }


    @Test
    public void check5BeansFor4() {
        addSpecial(BEANS, 5);
        checkNumberOfItemsWithPriceCost(5, BEANS, 66, 66 * 4);
    }


    @Test
    public void check6SoupFor4() {
        addSpecial(SOUP, 3);
        checkNumberOfItemsWithPriceCost(3, SOUP, 102, 102 * 2);
    }


    @Test
    public void checkBeans5for4andSoup3For2() {
        addSpecial(BEANS, 5);
        createItem(BEANS, 66);
        addItem(BEANS, 5);

        addSpecial(SOUP, 3);
        createItem(SOUP, 102);
        addItem(SOUP, 3);

        assertEquals("should have 5 beans for 4 and 3 can o soup for 2", 66 * 4 + 102 * 2, getTotalInCents());
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
}
