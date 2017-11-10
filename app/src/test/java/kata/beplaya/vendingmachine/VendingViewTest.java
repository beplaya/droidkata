package kata.beplaya.vendingmachine;

import android.app.Activity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import kata.beplaya.vendingmachine.coin.CoinMachine;
import kata.beplaya.vendingmachine.coin.ControlBoard;
import kata.beplaya.vendingmachine.store.Catalogue;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class VendingViewTest {

    private VendingView vendingView;
    private VendingController vendingController;
    private Map<CoinMachine.Coin, Button> mockInsertButtons;
    private Catalogue catalogue;
    @Mock
    private Activity mockActivity;
    @Mock
    private TextView mockStatusDisplay;
    @Mock
    private CoinMachine mockCoinMachine;
    @Mock
    private ListView mockProductListView;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        catalogue = new Catalogue();
        vendingView = new VendingView(mockActivity);

        setupMockButtons();

        when(mockActivity.findViewById(VendingView.ID_STATUS_DISPLAY)).thenReturn(mockStatusDisplay);
        when(mockActivity.getString(VendingView.ID_INSERT_COIN_STR)).thenReturn("INSERT COIN");
        when(mockActivity.findViewById(VendingView.ID_PRODUCT_LIST_VIEW)).thenReturn(mockProductListView);

        vendingController = new VendingController(new ControlBoard(mockCoinMachine), vendingView, new Catalogue());
    }

    private void setupMockButtons() {
        mockInsertButtons = new HashMap<>();
        CoinMachine.Coin[] coins = CoinMachine.Coin.values();
        for (CoinMachine.Coin coin : coins) {
            mockInsertButtons.put(coin, mock(Button.class));
            when(mockActivity.findViewById(vendingView.insertCoinResourceIds.get(coin))).thenReturn(mockInsertButtons.get(coin));
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
    public void itFindsTheProductListView() {
        assertEquals(mockProductListView, vendingView.productListView);
    }

    @Test
    public void itBindsTheProductListView() {
        verify(mockProductListView).setOnItemClickListener(vendingController.getProductClickHandler());
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