package org.dojo;

import junit.framework.Assert;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Till {

    public int total = 0;

    protected Map<String, Integer> price = new HashMap<String, Integer>();

    protected Map<String, Integer> bag = new HashMap<String, Integer>();   //item to quantity

    protected Map<String, Integer> specials = new HashMap<String, Integer>();   //item to quantity


    public Till() {
    }


    public void addSpecial(String item, int number) {
        specials.put(item, number);
    }


    public void setPrice(String beans, int i) {
        price.put(beans, i);
    }


    public void addItem(String item) {
        if (bag.containsKey(item)) {
            bag.put(item, bag.get(item) + 1);
        }
        else {
            bag.put(item, 1);
        }

        if (specials.containsKey(item)) {
            if (bag.get(item) % specials.get(item) == 0) {
                return;
            }
        }

        addPriceToTotal(price.get(item));
    }


    public void checkAddition(String expected, int... prices) {
        for (int price : prices) {
            addPriceToTotal(price);
        }
        Assert.assertEquals(expected, getTotal());
    }


    public String getTotal() {
        return getDecimalFormat(total);
    }


    private String getDecimalFormat(int value) {
        return new DecimalFormat("0.00").format(((float) value) / 100);
    }


    public void addPriceToTotal(int v) {
        total += v;
    }


    public void createItem(String s, int v) {
        price.put(s, v);
    }


    public String printReceipt() {
        String receipt = "";
        for (String s : bag.keySet()) {
            receipt += bag.get(s);
            receipt += " * ";
            receipt += s;
            receipt += " ";
            receipt += getDecimalFormat(price.get(s));
        }
        receipt += "\n==========\nTotal ";
        receipt += getTotal();

        return receipt;
    }


    protected void addItem(String item, int number) {
        for (int i = 0; i < number; i++) {
            addItem(item);
        }
    }
}