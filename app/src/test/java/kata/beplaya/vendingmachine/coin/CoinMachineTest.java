package kata.beplaya.vendingmachine.coin;

import org.junit.Before;
import org.junit.Test;

import static kata.beplaya.vendingmachine.coin.CoinMachine.*;
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

    @Test
    public void itCanAcceptValidCoins(){
        assertTrue(coinMachine.insert(Coin.NICKEL));
        assertTrue(coinMachine.insert(Coin.DIME));
        assertTrue(coinMachine.insert(Coin.QUARTER));
    }

    @Test
    public void itDoesNotAcceptInvalidCoins(){
        assertFalse(coinMachine.insert(Coin.PENNY));
        assertFalse(coinMachine.insert(Coin.UNRECOGNIZED));
    }
}