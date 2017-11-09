package kata.beplaya.vendingmachine.coin;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static kata.beplaya.vendingmachine.coin.CoinMachine.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class CoinMachineTest {

    private CoinMachine coinMachine;

    @Mock
    private ReturnTray returnTray;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
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

    @Test
    public void itCalculatesAmountInserted(){
        coinMachine.insert(Coin.QUARTER);
        coinMachine.insert(Coin.QUARTER);
        coinMachine.insert(Coin.DIME);
        coinMachine.insert(Coin.NICKEL);
        assertEquals(65f, coinMachine.getCurrentAmount(), 0f);
    }

    @Test
    public void itHasTheCorrectRejectedCoinsInReturn(){
        coinMachine.insert(Coin.PENNY);
        coinMachine.insert(Coin.QUARTER);
        coinMachine.insert(Coin.QUARTER);
        coinMachine.insert(Coin.UNRECOGNIZED);
        coinMachine.insert(Coin.DIME);
        coinMachine.insert(Coin.NICKEL);
        coinMachine.insert(Coin.PENNY);
        verify(returnTray, times(2)).onReturn(Coin.PENNY);
        verify(returnTray, times(2)).onReturn(Coin.UNRECOGNIZED);

    }
}