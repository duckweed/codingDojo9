package org.dojo;

import junit.framework.Assert;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Till {
    public int total = 0;
    public Map<String, Integer> price = new HashMap<String, Integer>();

    public Till() {
    }

    public void setPrice(String beans, int i) {
        price.put(beans, i);
    }

    public void addItem(String item) {
        addItem(price.get(item));
    }

    public void checkAddition(String expected, int... prices) {
        for (int price : prices) {
            addItem(price);
        }
        Assert.assertEquals(expected, getTotal());
    }

    public String getTotal() {
        return new DecimalFormat("0.00").format(((float) total) / 100);
    }

    public void addItem(int v) {
        total += v;
    }
}