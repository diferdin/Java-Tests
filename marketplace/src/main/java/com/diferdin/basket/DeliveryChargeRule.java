package com.diferdin.basket;

/**
 * Created by antonio on 01/06/2016.
 */

public final class DeliveryChargeRule implements ChargeRule {

    private double lowerLimit;
    private double upperLimit;
    private double charge;

    public DeliveryChargeRule(double lowerLimit, double upperLimit, double charge) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.charge = charge;
    }

    public DeliveryChargeRule(double lowerLimit, double charge) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = -1;
        this.charge = charge;
    }

    public double getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(double lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public double getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(double upperLimit) {
        this.upperLimit = upperLimit;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public boolean isInRange(double amount) {

        if(upperLimit == -1) {
            return amount > lowerLimit;
        }

        return amount > lowerLimit && amount < upperLimit;
    }
}
