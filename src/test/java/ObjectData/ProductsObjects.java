
package ObjectData;

import java.util.List;
import java.util.Map;

public class ProductsObjects {

//    public static final List<String> PRODUCTS = List.of(
//            "backpack",
//            "bike-light",
//            "bolt-t-shirt",
//            "fleece-jacket",
//            "onesie",
//            "test.allthethings()-t-shirt-(red)"
//    );

    public static final List<String> PRODUCTS = List.of(
            "backpack",
            "bike-light",
            "bolt-t-shirt",
            "fleece-jacket",
            "onesie",
            "test.allthethings()-t-shirt-(red)"
    );

    public static final Map<String, String> PRODUCT_NAMES = Map.of(
            "backpack", "Sauce Labs Backpack",
            "bike-light", "Sauce Labs Bike Light",
            "bolt-t-shirt", "Sauce Labs Bolt T-Shirt",
            "fleece-jacket", "Sauce Labs Fleece Jacket",
            "onesie", "Sauce Labs Onesie",
            "test.allthethings()-t-shirt-(red)", "Test.allTheThings() T-Shirt (Red)"
    );

    public static final Map<String, String> PRODUCT_BUTTON_IDS = Map.of(
            "backpack", "add-to-cart-sauce-labs-backpack",
            "bike-light", "add-to-cart-sauce-labs-bike-light",
            "bolt-t-shirt", "add-to-cart-sauce-labs-bolt-t-shirt",
            "fleece-jacket", "add-to-cart-sauce-labs-fleece-jacket",
            "onesie", "add-to-cart-sauce-labs-onesie",
            "test.allthethings()-t-shirt-(red)", "add-to-cart-test.allthethings()-t-shirt-(red)"
    );

    public static final Map<String, String> PRODUCT_REMOVE_BUTTON_IDS = Map.of(
            "backpack", "remove-sauce-labs-backpack",
            "bike-light", "remove-sauce-labs-bike-light",
            "bolt-t-shirt", "remove-sauce-labs-bolt-t-shirt",
            "fleece-jacket", "remove-sauce-labs-fleece-jacket",
            "onesie", "remove-sauce-labs-onesie",
            "test.allthethings()-t-shirt-(red)",
            "remove-test.allthethings()-t-shirt-(red)"
    );
}
