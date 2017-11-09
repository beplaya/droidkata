package kata.beplaya.vendingmachine.coin;

import android.app.Activity;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

import kata.beplaya.vendingmachine.VendingController;

import static kata.beplaya.vendingmachine.coin.CoinMachine.*;

public class VendingView {

    public static final int ID_QUARTER = 0;
    public static final int ID_DIME = 1;
    public static final int ID_NICKEL = 2;
    public static final int ID_PENNY = 3;
    public static final int ID_OTHER = 4;

    Map<Coin, Integer> insertCoinResourceIds;
    Map<Coin, Button> insertButtons;

    private Activity activity;
    private VendingController vendingController;

    public VendingView(Activity activity, VendingController vendingController) {
        this.activity = activity;
        this.vendingController = vendingController;
        insertCoinResourceIds = new HashMap<>();
        insertCoinResourceIds.put(Coin.QUARTER, ID_QUARTER);
        insertCoinResourceIds.put(Coin.DIME, ID_DIME);
        insertCoinResourceIds.put(Coin.NICKEL, ID_NICKEL);
        insertCoinResourceIds.put(Coin.PENNY, ID_PENNY);
        insertCoinResourceIds.put(Coin.OTHER, ID_OTHER);
        insertButtons = new HashMap<>();
    }

    public void init() {
        findViews();
        bindViews();
    }

    private void findViews() {
        insertButtons = new HashMap<>();
        Coin[] coins = Coin.values();
        for (Coin coin : coins) {
            insertButtons.put(coin, (Button) activity.findViewById(insertCoinResourceIds.get(coin)));
        }
    }

    private void bindViews() {
        Coin[] coins = Coin.values();
        for (Coin coin : coins) {
            insertButtons.get(coin).setOnClickListener(vendingController.getInsertClickHandler(coin));
        }
    }

}
