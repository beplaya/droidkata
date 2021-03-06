package kata.beplaya.vendingmachine.coin;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static kata.beplaya.vendingmachine.coin.CoinMachine.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class CoinMachineTest {

    private CoinMachine coinMachine;

    @Mock
    private ReturnTray mockReturnTray;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        coinMachine = new CoinMachine(mockReturnTray);
    }

    @Test
    public void itDefaultsCurrentAmountToZero() {
        assertEquals(0, coinMachine.getCurrentAmount());
    }

    @Test
    public void itCanAcceptValidCoins() {
        assertTrue(coinMachine.insert(Coin.NICKEL));
        assertTrue(coinMachine.insert(Coin.DIME));
        assertTrue(coinMachine.insert(Coin.QUARTER));
    }

    @Test
    public void itDoesNotAcceptInvalidCoins() {
        assertFalse(coinMachine.insert(Coin.PENNY));
        assertFalse(coinMachine.insert(Coin.OTHER));
    }

    @Test
    public void itCalculatesAmountInserted() {
        coinMachine.insert(Coin.QUARTER);
        coinMachine.insert(Coin.QUARTER);
        coinMachine.insert(Coin.DIME);
        coinMachine.insert(Coin.NICKEL);
        assertEquals(65, coinMachine.getCurrentAmount());
    }

    @Test
    public void itHasTheCorrectRejectedCoinsInReturn() {
        coinMachine.insert(Coin.PENNY);
        coinMachine.insert(Coin.QUARTER);
        coinMachine.insert(Coin.QUARTER);
        coinMachine.insert(Coin.OTHER);
        coinMachine.insert(Coin.DIME);
        coinMachine.insert(Coin.NICKEL);
        coinMachine.insert(Coin.PENNY);
        verify(mockReturnTray, times(2)).onReturn(Coin.PENNY);
        verify(mockReturnTray, times(1)).onReturn(Coin.OTHER);
    }
}