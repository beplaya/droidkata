package kata.beplaya.vendingmachine.store;

public class Catalogue {
    private Product[] products;

    public Catalogue(){
        products = new Product[3];
        products[0] = new Product("1", "cola", 100);
        products[1] = new Product("2", "chips", 50);
        products[2] = new Product("3", "candy", 65);
    }
    public Product[] getAvailableProducts() {
        return products;
    }
}
