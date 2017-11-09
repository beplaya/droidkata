package kata.beplaya.vendingmachine;

import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kata.beplaya.vendingmachine.coin.CoinMachine;

import static org.mockito.Mockito.*;

public class VendingControllerTest {

    private VendingController vendingController;

    @Mock
    private VendingView mockVendingView;
    @Mock
    private View mockView;
    @Mock
    private CoinMachine mockCoinMachine;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vendingController = new VendingController(mockCoinMachine, mockVendingView);
    }

    @Test
    public void itUpdatesVendStatusOnInit(){
        verify(mockVendingView).updateVendStatus(vendingController.getControlBoard().determineVendState(), vendingController.getControlBoard().getCurrentAmountAccepted());
    }

    @Test
    public void itUpdatesVendStatusOnInsertEvents(){
        vendingController.getInsertClickHandler(CoinMachine.Coin.QUARTER).onClick(mockView);
        verify(mockCoinMachine, times(2)).getCurrentAmount();
        verify(mockVendingView, times(2)).updateVendStatus(vendingController.getControlBoard().determineVendState(), vendingController.getControlBoard().getCurrentAmountAccepted());
    }

    @Test
    public void itInitializesTheView(){
        verify(mockVendingView).init(vendingController);
    }
}