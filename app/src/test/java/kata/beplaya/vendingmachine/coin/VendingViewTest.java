package kata.beplaya.vendingmachine.coin;

import android.app.Activity;
import android.widget.Button;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import kata.beplaya.vendingmachine.VendingController;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class VendingViewTest {

    private VendingView vendingView;
    private VendingController vendingController;
    @Mock
    private Activity mockActivity;
    private Map<CoinMachine.Coin, Button> mockInsertButtons;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vendingController = new VendingController();
        vendingView = new VendingView(mockActivity, vendingController);

        mockInsertButtons = new HashMap<>();
        CoinMachine.Coin[] coins = CoinMachine.Coin.values();
        for (CoinMachine.Coin coin : coins) {
            mockInsertButtons.put(coin, Mockito.mock(Button.class));
            when(mockActivity.findViewById(vendingView.insertCoinResourceIds.get(coin))).thenReturn(mockInsertButtons.get(coin));
        }

        vendingView.init();
    }

    @Test
    public void itFindsTheInsertButtons() {
        CoinMachine.Coin[] coins = CoinMachine.Coin.values();
        for (CoinMachine.Coin coin : coins) {
            assertEquals(mockInsertButtons.get(coin), vendingView.insertButtons.get(coin));
        }
    }


    @Test
    public void itBindsTheInsertButtons() {
        CoinMachine.Coin[] coins = CoinMachine.Coin.values();
        for (CoinMachine.Coin coin : coins) {
            verify(mockInsertButtons.get(coin)).setOnClickListener(vendingController.getInsertClickHandler(coin));
        }
    }

}