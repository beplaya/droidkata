package kata.beplaya.vendingmachine.coin;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

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
    private Map<CoinMachine.Coin, Button> mockInsertButtons;
    @Mock
    private Activity mockActivity;
    @Mock
    private TextView mockStatusDisplay;
    @Mock
    private CoinMachine mockCoinMachine;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vendingController = new VendingController(mockCoinMachine, vendingView);
        vendingView = new VendingView(mockActivity);

        mockInsertButtons = new HashMap<>();
        CoinMachine.Coin[] coins = CoinMachine.Coin.values();
        for (CoinMachine.Coin coin : coins) {
            mockInsertButtons.put(coin, Mockito.mock(Button.class));
            when(mockActivity.findViewById(vendingView.insertCoinResourceIds.get(coin))).thenReturn(mockInsertButtons.get(coin));
        }

        when(mockActivity.findViewById(VendingView.ID_STATUS_DISPLAY)).thenReturn(mockStatusDisplay);

        vendingView.init(vendingController);
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

    @Test
    public void itFindsTheStatusDisplay(){
        assertEquals(mockStatusDisplay, vendingView.statusDisplay);
    }

}