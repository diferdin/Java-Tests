package com.diferdin.basket;

import java.util.*;

/**
 * Created by antonio on 01/06/2016.
 */
public class DeliveryCharge implements Charge {
    List<DeliveryChargeRule> rules;

    public DeliveryCharge(List<DeliveryChargeRule> newRules) {
        rules = newRules;
    }

    @Override
    public List<DeliveryChargeRule> getRuleSet() {
        return rules;
    }

    @Override
    public ChargeType getChargeType() {
        return ChargeType.DELIVERY_CHARGE;
    }

    @Override
    public double applyToAmount(double billTotal) {
        return rules.stream().mapToDouble(r -> r.isInRange(billTotal) ? r.getCharge() : 0).sum();
    }


}
