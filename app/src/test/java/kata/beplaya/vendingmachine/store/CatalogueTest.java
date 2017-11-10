package kata.beplaya.vendingmachine.store;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CatalogueTest {

    private  Catalogue catalogue;
    private Product[] expectedProducts;

    @Before
    public void setUp() throws
            Exception {
        catalogue = new Catalogue();
        expectedProducts = new Product[3];
        expectedProducts[0] = new Product("1", "cola", 100);
        expectedProducts[1] = new Product("2", "chips", 50);
        expectedProducts[2] = new Product("3", "candy", 65);
    }

    @Test
    public void itHasTheCorrectProducts(){
        Product[] actualProducts = catalogue.getAvailableProducts();
        assertEquals(expectedProducts.length, actualProducts.length);
        for (int i = 0; i < expectedProducts.length; i++) {
            assertEquals(expectedProducts[i], actualProducts[i]);
        }
    }

}