package com.diferdin.basket;

import java.util.List;

/**
 * Created by antonio on 01/06/2016.
 */

public interface Charge {

    List<DeliveryChargeRule> getRuleSet();

    ChargeType getChargeType();

    double applyToAmount(double totalBill);

}
