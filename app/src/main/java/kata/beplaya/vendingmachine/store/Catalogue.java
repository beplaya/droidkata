package kata.beplaya.vendingmachine.store;

import java.util.UUID;

public class Catalogue {
    private Product[] products;

    public Catalogue(){
        products = new Product[3];
        products[0] = new Product("0", "cola", 100);
        products[1] = new Product("1", "chips", 50);
        products[2] = new Product("2", "candy", 65);
    }
    public Product[] getAvailableProducts() {
        return products;
    }
}
