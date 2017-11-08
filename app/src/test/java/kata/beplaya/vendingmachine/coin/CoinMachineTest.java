package kata.beplaya.vendingmachine.coin;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoinMachineTest {

    private CoinMachine coinMachine;

    @Before
    public void setUp() throws Exception {
        coinMachine = new CoinMachine();
    }

    @Test
    public void itDefaultsCurrentAmountToZero(){
        assertEquals(0f, coinMachine.getCurrentAmount(), 0f);
    }

}