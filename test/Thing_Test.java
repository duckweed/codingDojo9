import org.dojo.Till;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class Thing_Test extends Till {

    @Before
    public void setup() {
        price.put("beans", 33);
        price.put("detergent", 128);

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
        addItem("beans");
        assertEquals("0.33", getTotal());
    }

    @Test
    public void add2NamedItems() {
        addItem("beans");
        addItem("detergent");
        assertEquals("1.61", getTotal());
    }


    @Test
    public void changedNamedItemWithNewPrice() {
        setPrice("beans", 69);
        addItem("beans");
        assertEquals("should have changed to new price", "0.69", getTotal());
    }

    @Test
    public void addNamedItemWithSetPrice() {
        createItem("can o soup", 102);
        addItem("can o soup");
        assertEquals("should can o soup at", "1.02", getTotal());
    }

    @Test
    public void check3SoupFor2() {
        addSpecial("can o soup", 3);
        createItem("can o soup", 102);
        addItem("can o soup");
        addItem("can o soup");
        addItem("can o soup");
        assertEquals("should have 3 can o soup for the price of 2", "2.04", getTotal());
    }

    @Test
    public void check4SoupFor3() {
        addSpecial("can o soup", 4);
        createItem("can o soup", 102);
        addItem("can o soup");
        addItem("can o soup");
        addItem("can o soup");
        addItem("can o soup");
        assertEquals("should have 4 can o soup for the price of 3", "3.06", getTotal());
    }

    @Test
    public void check6SoupFor4() {
        addSpecial("can o soup", 3);
        createItem("can o soup", 102);
        addItem("can o soup");
        addItem("can o soup");
        addItem("can o soup");
        addItem("can o soup");
        addItem("can o soup");
        addItem("can o soup");
        assertEquals("should have 6 can o soup for the price of 4", "4.08", getTotal());
    }


    @Test
    public void checkBeans5for4andSoup3For2() {
        addSpecial("can o soup", 3);
        addSpecial("beans", 5);
        createItem("can o soup", 102);
        createItem("beans", 66);


        addItem("can o soup");
        addItem("can o soup");
        addItem("can o soup");

        addItem("beans");
        addItem("beans");
        addItem("beans");
        addItem("beans");
        addItem("beans");
        assertEquals("should have 3 can o soup for 2 and 5 beans for 4 ", "4.68", getTotal());
    }

    @Test
    public void testPrintReceipt() {
        createItem("beans", 66);
        addItem("beans");
        assertEquals("should be a receipt", "1 * beans 0.66\n==========\nTotal 0.66", printReceipt());
    }

    @Test
    public void test2DetergentPrintReceipt() {
        addItem("detergent");
        addItem("detergent");
        assertEquals("should be a receipt for 2 * detergent", "2 * detergent 1.28\n==========\nTotal 2.56", printReceipt());
    }

    @Test
    public void check5BeansFor4() {
        addSpecial("beans", 5);
        createItem("beans", 66);
        addItem("beans");
        addItem("beans");
        addItem("beans");
        addItem("beans");
        addItem("beans");
        assertEquals("should have 5 beans for the price of 4", "2.64", getTotal());
    }
}
