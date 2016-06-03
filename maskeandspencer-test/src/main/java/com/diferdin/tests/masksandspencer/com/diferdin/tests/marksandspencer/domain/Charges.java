package com.diferdin.tests.masksandspencer.com.diferdin.tests.marksandspencer.domain;

import com.diferdin.tests.masksandspencer.com.diferdin.tests.marksandspencer.domain.Charge;
import com.diferdin.tests.masksandspencer.com.diferdin.tests.marksandspencer.domain.ChargeType;
import com.diferdin.tests.masksandspencer.com.diferdin.tests.marksandspencer.domain.DeliveryCharge;
import com.diferdin.tests.masksandspencer.exception.ShoppingException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by LONADF on 02/06/2016.
 */

public class Charges {
    private static Set<Charge> charges = new HashSet<>();

    public static DeliveryCharge getDeliveryCharge() {
        List<Charge> matchingCharges = new ArrayList<>();
        for(Charge charge : charges) {
            if(ChargeType.DELIVERY_CHARGE.getName().equalsIgnoreCase(charge.getChargeType().getName())) {
                matchingCharges.add(charge);
            }
        }

        if(matchingCharges.size() == 0) {
            return null;
        }

        if(matchingCharges.size() > 1) {
            throw new ShoppingException("Ambiguous delivery charges spotted.");
        }

        return (DeliveryCharge) matchingCharges.get(0);
    }

    public static void add(Charge charge) {

        if(contains(charge)) {
            return;
        }

        charges.add(charge);
    }

    private static boolean contains(Charge charge) {
        for(Charge storedCharge : charges) {
            if(charge.getChargeType().getName().equalsIgnoreCase(storedCharge.getChargeType().getName())) {
                return true;
            }
        }

        return false;
    }
}
