package kata.beplaya.vendingmachine.coin;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static kata.beplaya.vendingmachine.coin.ControlBoard.*;
import static org.junit.Assert.*;

public class ControlBoardTest {

    private ControlBoard controlBoard;
    private CoinMachine coinMachine;
    @Mock
    private ReturnTray mockReturnTray;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        coinMachine = new CoinMachine(mockReturnTray);
        controlBoard = new ControlBoard(coinMachine);
    }

    @Test
    public void itHasTheCorrectInitialState() {
        assertEquals(VendState.INSERT_COIN, controlBoard.determineVendState());
    }

    @Test
    public void itRefectsCorrectStateAfterInsert_ValidCoin(){
        coinMachine.insert(CoinMachine.Coin.DIME);
        assertEquals(VendState.COINS_INSERTED, controlBoard.determineVendState());
    }

    @Test
    public void itRefectsCorrectStateAfterInsert_InvalidCoin(){
        coinMachine.insert(CoinMachine.Coin.DIME);
        assertEquals(VendState.COINS_INSERTED, controlBoard.determineVendState());
    }

}