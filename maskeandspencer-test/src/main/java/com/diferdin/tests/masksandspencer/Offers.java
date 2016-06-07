package com.diferdin.tests.masksandspencer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by LONADF on 02/06/2016.
 */

public class Offers {

    private static Set<Offer> offers = new HashSet<>();

    public static List<Offer> getForCatalog(Catalog catalog) {
        List<Offer> matchingOffers = new ArrayList<>();

        for(Offer offer : offers) {
            if(catalog.contains(offer.offerSubject)) {
                matchingOffers.add(offer);
            }
        }

        return matchingOffers;
    }

    public static void add(Offer offer) {

        if(contains(offer)) {
            return;
        }

        offers.add(offer);
    }

    private static boolean contains(Offer offer) {
        for(Offer storedOffer : offers) {
            if(offer.offerName.equalsIgnoreCase(storedOffer.offerName)) {
                return true;
            }
        }

        return false;
    }

    public static void erase() {
        offers.clear();
    }
}
