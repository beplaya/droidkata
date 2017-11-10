package kata.beplaya.vendingmachine;

import android.view.View;
import android.widget.AdapterView;

import java.util.HashMap;
import java.util.Map;

import kata.beplaya.vendingmachine.coin.CoinMachine;
import kata.beplaya.vendingmachine.coin.ControlBoard;
import kata.beplaya.vendingmachine.store.Catalogue;
import kata.beplaya.vendingmachine.store.Product;

import static kata.beplaya.vendingmachine.coin.CoinMachine.*;

public class VendingController {

    private Map<Coin, InsertCoinListener> insertCoinListenerMap;
    private ControlBoard controlBoard;
    private VendingView vendingView;
    private Catalogue catalogue;

    public VendingController(ControlBoard controlBoard, VendingView vendingView, Catalogue catalogue){
        this.controlBoard = controlBoard;
        this.vendingView = vendingView;
        this.catalogue = catalogue;
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

    public Product[] getProducts() {
        return catalogue.getAvailableProducts();
    }

    public AdapterView.OnItemClickListener getProductClickHandler() {
        return null;
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
