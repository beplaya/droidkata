package kata.beplaya.vendingmachine.coin;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

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
    public static final int ID_STATUS_DISPLAY = 5;
    public static final int ID_INSERT_COIN_STR = 323123;

    Map<Coin, Integer> insertCoinResourceIds;
    Map<Coin, Button> insertButtons;
    TextView statusDisplay;

    private Activity activity;
    private VendingController vendingController;

    public VendingView(Activity activity) {
        this.activity = activity;
        insertCoinResourceIds = new HashMap<>();
        insertCoinResourceIds.put(Coin.QUARTER, ID_QUARTER);
        insertCoinResourceIds.put(Coin.DIME, ID_DIME);
        insertCoinResourceIds.put(Coin.NICKEL, ID_NICKEL);
        insertCoinResourceIds.put(Coin.PENNY, ID_PENNY);
        insertCoinResourceIds.put(Coin.OTHER, ID_OTHER);
        insertButtons = new HashMap<>();
    }

    public void init(VendingController vendingController) {
        this.vendingController = vendingController;
        findViews();
        bindViews();
    }

    private void findViews() {
        insertButtons = new HashMap<>();
        Coin[] coins = Coin.values();
        for (Coin coin : coins) {
            insertButtons.put(coin, (Button) activity.findViewById(insertCoinResourceIds.get(coin)));
        }
        statusDisplay = activity.findViewById(ID_STATUS_DISPLAY);
    }

    private void bindViews() {
        Coin[] coins = Coin.values();
        for (Coin coin : coins) {
            insertButtons.get(coin).setOnClickListener(vendingController.getInsertClickHandler(coin));
        }
    }

    public void updateVendStatus(ControlBoard.VendState vendState, float currentAmountAccepted) {
        if(vendState.equals(ControlBoard.VendState.INSERT_COIN)){
            statusDisplay.setText(activity.getString(ID_INSERT_COIN_STR));
        } else {
            statusDisplay.setText(formatAmount(currentAmountAccepted));
        }
    }

    private String formatAmount(float amount) {
        return "$" + amount;
    }
}
