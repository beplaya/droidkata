package kata.beplaya.vendingmachine.store;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void itEqualsOtherProductOfSameValues(){
        assertEquals(new Product("1", "cola", 144), (new Product("1", "cola", 144)));
    }
    @Test
    public void onlyIdMatters() {
        assertEquals(new Product("1", "cosla", 1123), (new Product("1", "cola", 144)));
    }
}