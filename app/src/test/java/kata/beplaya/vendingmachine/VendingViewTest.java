package kata.beplaya.vendingmachine;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import kata.beplaya.vendingmachine.coin.CoinMachine;
import kata.beplaya.vendingmachine.coin.ControlBoard;
import kata.beplaya.vendingmachine.store.Catalogue;
import kata.beplaya.vendingmachine.store.Product;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class VendingViewTest {

    private VendingView vendingView;
    private VendingController vendingController;
    private Map<CoinMachine.Coin, Button> mockInsertButtons;
    private Map<String, Button> mockProductButtons;
    private Catalogue catalogue;
    @Mock
    private Activity mockActivity;
    @Mock
    private TextView mockStatusDisplay;
    @Mock
    private CoinMachine mockCoinMachine;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        catalogue = new Catalogue();
        vendingView = new VendingView(mockActivity);

        setupMockButtons();

        when(mockActivity.findViewById(VendingView.ID_STATUS_DISPLAY)).thenReturn(mockStatusDisplay);
        when(mockActivity.getString(VendingView.ID_INSERT_COIN_STR)).thenReturn("INSERT COIN");

        vendingController = new VendingController(new ControlBoard(mockCoinMachine), vendingView);
    }

    private void setupMockButtons() {
        mockInsertButtons = new HashMap<>();
        CoinMachine.Coin[] coins = CoinMachine.Coin.values();
        for (CoinMachine.Coin coin : coins) {
            mockInsertButtons.put(coin, mock(Button.class));
            when(mockActivity.findViewById(vendingView.insertCoinResourceIds.get(coin))).thenReturn(mockInsertButtons.get(coin));
        }
        mockProductButtons = new HashMap<>();
        Product[] products = catalogue.getAvailableProducts();
        for (Product product : products) {
            mockProductButtons.put(product.getID(), mock(Button.class));
        }
    }

    @Test
    public void itSetsTheTextOfTheInsertButtons() {
        CoinMachine.Coin[] coins = CoinMachine.Coin.values();
        for (CoinMachine.Coin coin : coins) {
            verify(mockInsertButtons.get(coin)).setText(coin.name());
        }
    }

    @Test
    public void itFindsTheProductButtons() {
        Product[] products = catalogue.getAvailableProducts();
        for (Product product : products) {
            assertEquals(mockProductButtons.get(product.getID()), vendingView.productButtons.get(product.getID()));
        }
    }

    @Test
    public void itBindsTheProductButtons() {
        Product[] products = catalogue.getAvailableProducts();
        for (Product product : products) {
            verify(mockProductButtons.get(product.getID())).setOnClickListener(vendingController.getProductClickHandler(product.getID()));
        }
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
    public void itFindsTheStatusDisplay() {
        assertEquals(mockStatusDisplay, vendingView.statusDisplay);
    }

    @Test
    public void itUpdatesTheStatusDisplayForNoCoins() {
        vendingView.updateVendStatus(ControlBoard.VendState.INSERT_COIN, 0);
        verify(mockStatusDisplay, times(2)).setText(mockActivity.getString(VendingView.ID_INSERT_COIN_STR));
    }

    @Test
    public void itUpdatesTheStatusDisplayBasedOnVendState() {
        vendingView.updateVendStatus(ControlBoard.VendState.INSERT_COIN, 185);
        verify(mockStatusDisplay, times(2)).setText(mockActivity.getString(VendingView.ID_INSERT_COIN_STR));
    }

    @Test
    public void itUpdatesTheStatusDisplayWhenCoinsAreInserted() {
        vendingView.updateVendStatus(ControlBoard.VendState.COINS_INSERTED, 25);
        verify(mockStatusDisplay).setText("$0.25");
    }

    @Test
    public void itFormatsAmountCorrectly() {
        vendingView.updateVendStatus(ControlBoard.VendState.COINS_INSERTED, 5);
        verify(mockStatusDisplay).setText("$0.05");
    }
}