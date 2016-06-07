package com.diferdin.tests.masksandspencer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by LONADF on 02/06/2016.
 */

public class Catalogs {
    private static Set<Catalog> catalogs;

    public static boolean add(Catalog catalog) {
        if(catalogs == null) {
            catalogs = new HashSet<>();
        }

        if(contains(catalog)) {
            return false;
        }

        return catalogs.add(catalog);
    }

    public static Catalog getForProduct(Product product) {
        List<Catalog> rightCatalog = catalogs.stream()
                .filter(c -> c.contains(product))
                .collect(Collectors.toList());

        if( rightCatalog.size() != 1) {
            return null;
        }

        return rightCatalog.get(0);
    }

    private static boolean contains(Catalog catalog) {
        for(Catalog storedCatalog : catalogs) {
            if(catalog.getName().getName().equalsIgnoreCase(storedCatalog.getName().getName())) {
                return true;
            }
        }

        return false;
    }

    public static void eraseCatalogs() {
        catalogs.clear();
    }
}
