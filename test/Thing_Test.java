import org.junit.Test;

import java.text.DecimalFormat;

import static org.junit.Assert.assertEquals;

public class Thing_Test {

    @Test
    public void aHundredCentsReturnsDollar() {
        int price = 100;

        addItem(price);
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
    public void addNamedItem(){
        addItem("beans");
        assertEquals("0.33", getTotal());
    }

    @Test
    public void add2NamedItems(){
        addItem("beans");
        addItem("detergent");
        assertEquals("1.61",getTotal());
    }

    @Test
    public void addNamedItemWithSetPrice(){
        setPrice("beans", 69);
        addItem("beans");
        assertEquals("0.69",getTotal());
    }

    private void setPrice(String beans, int i) {
        //To change body of created methods use File | Settings | File Templates.
    }


    private void addItem(String item) {
        if(item .equals("beans"))
            addItem(33);
        if(item .equals("detergent"))
            addItem(128);
    }


    private void checkAddition(String expected, int... prices) {
        for (int price : prices) {
            addItem(price);
        }
        assertEquals(expected, getTotal());
    }

    private int total = 0;

    private String getTotal() {
        return new DecimalFormat("0.00").format(((float) total) / 100);
    }

    private void addItem(int v) {
        total += v;
    }
}
