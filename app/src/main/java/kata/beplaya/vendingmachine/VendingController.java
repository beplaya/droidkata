package kata.beplaya.vendingmachine;

import android.view.View;

import java.util.HashMap;
import java.util.Map;

import kata.beplaya.vendingmachine.coin.CoinMachine;

import static kata.beplaya.vendingmachine.coin.CoinMachine.*;

public class VendingController {

    private Map<Coin, InsertCoinListener> listeners;

    public VendingController(){
        listeners = new HashMap<>();
        Coin[] coins = Coin.values();
        for (Coin coin: coins) {
            listeners.put(coin, new InsertCoinListener(coin));
        }
    }

    public InsertCoinListener getInsertClickHandler(Coin coin) {
        return listeners.get(coin);
    }

    public class InsertCoinListener implements View.OnClickListener {

        private Coin coin;

        public InsertCoinListener(Coin coin) {
            this.coin = coin;
        }

        @Override
        public void onClick(View view) {

        }
    }
}
