package kata.beplaya.vendingmachine;

import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kata.beplaya.vendingmachine.coin.CoinMachine;
import kata.beplaya.vendingmachine.coin.ControlBoard;
import kata.beplaya.vendingmachine.coin.ReturnTray;

import static org.mockito.Mockito.*;

public class VendingControllerTest {

    private VendingController vendingController;
    private ControlBoard controlBoard;

    @Mock
    private VendingView mockVendingView;
    @Mock
    private View mockView;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controlBoard = new ControlBoard(new CoinMachine(new ReturnTray()));
        vendingController = new VendingController(controlBoard, mockVendingView);
    }

    @Test
    public void itUpdatesVendStatusOnInit(){
        verify(mockVendingView).updateVendStatus(vendingController.getControlBoard().determineVendState(), vendingController.getControlBoard().getCurrentAmountAccepted());
    }

    @Test
    public void itUpdatesVendStatusOnInsertEvents(){
        vendingController.getInsertClickHandler(CoinMachine.Coin.QUARTER).onClick(mockView);
        vendingController.getInsertClickHandler(CoinMachine.Coin.NICKEL).onClick(mockView);
        vendingController.getInsertClickHandler(CoinMachine.Coin.NICKEL).onClick(mockView);
        vendingController.getInsertClickHandler(CoinMachine.Coin.DIME).onClick(mockView);
        vendingController.getInsertClickHandler(CoinMachine.Coin.PENNY).onClick(mockView);
        vendingController.getInsertClickHandler(CoinMachine.Coin.OTHER).onClick(mockView);
        //45
        verify(mockVendingView, times(1)).updateVendStatus(ControlBoard.VendState.COINS_INSERTED, 25);
        verify(mockVendingView, times(1)).updateVendStatus(ControlBoard.VendState.COINS_INSERTED, 30);
        verify(mockVendingView, times(1)).updateVendStatus(ControlBoard.VendState.COINS_INSERTED, 35);
        verify(mockVendingView, times(3)).updateVendStatus(ControlBoard.VendState.COINS_INSERTED, 45);
    }

    @Test
    public void itInitializesTheView(){
        verify(mockVendingView).init(vendingController);
    }
}