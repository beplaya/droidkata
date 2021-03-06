package kata.beplaya.vendingmachine;

import android.view.View;

import java.util.HashMap;
import java.util.Map;

import kata.beplaya.vendingmachine.coin.CoinMachine;
import kata.beplaya.vendingmachine.coin.ControlBoard;

import static kata.beplaya.vendingmachine.coin.CoinMachine.*;

public class VendingController {

    private Map<Coin, InsertCoinListener> insertCoinListenerMap;
    private ControlBoard controlBoard;
    private VendingView vendingView;

    public VendingController(ControlBoard controlBoard, VendingView vendingView){
        this.controlBoard = controlBoard;
        this.vendingView = vendingView;
        insertCoinListenerMap = new HashMap<>();
        Coin[] coins = Coin.values();
        for (Coin coin: coins) {
            insertCoinListenerMap.put(coin, new InsertCoinListener(coin));
        }
        this.vendingView.init(this);
        this.vendingView.updateVendStatus(getControlBoard().determineVendState(), getControlBoard().getCurrentAmountAccepted());
    }

    public InsertCoinListener getInsertClickHandler(Coin coin) {
        return insertCoinListenerMap.get(coin);
    }

    public ControlBoard getControlBoard() {
        return controlBoard;
    }

    public class InsertCoinListener implements View.OnClickListener {

        private Coin coin;

        public InsertCoinListener(Coin coin) {
            this.coin = coin;
        }

        @Override
        public void onClick(View view) {
            controlBoard.getCoinMachine().insert(coin);
            vendingView.updateVendStatus(controlBoard.determineVendState(), controlBoard.getCurrentAmountAccepted());
        }
    }
}
