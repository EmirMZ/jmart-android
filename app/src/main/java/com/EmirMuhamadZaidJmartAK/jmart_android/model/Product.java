package com.EmirMuhamadZaidJmartAK.jmart_android.model;

/**
 * Product Class Model
 */
public class Product extends Serializable
{
    public int accountId;
    public ProductCategory category;
    public boolean conditionUsed;
    public double discount;
    public String name;
    public double price;
    public byte shipmentPlans;
    public int weight;

    public String toString(){
        return this.name;
    }
}