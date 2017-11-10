package kata.beplaya.vendingmachine.store;

public class Product {
    private String id;
    private String name;
    private int priceInCents;

    public Product(String id, String name, int priceInCents) {
        this.id = id;
        this.name = name;
        this.priceInCents = priceInCents;
    }

    public String getID() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equals = false;
        if(obj instanceof Product) {
            Product other = (Product) obj;
            equals = id.equals(other.id);
        }
        return equals;
    }
}
