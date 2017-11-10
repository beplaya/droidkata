package kata.beplaya.vendingmachine;

import android.app.Activity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import kata.beplaya.vendingmachine.coin.ControlBoard;
import kata.beplaya.vendingmachine.store.Product;

import static kata.beplaya.vendingmachine.coin.CoinMachine.*;

public class VendingView {

    public static final int ID_QUARTER = R.id.btn_insert_quarter;
    public static final int ID_DIME = R.id.btn_insert_dime;
    public static final int ID_NICKEL = R.id.btn_insert_nickel;
    public static final int ID_PENNY = R.id.btn_insert_penny;
    public static final int ID_OTHER = R.id.btn_insert_other;
    public static final int ID_STATUS_DISPLAY = R.id.tv_status;
    public static final int ID_INSERT_COIN_STR = R.string.status_insert_coin;
    public static final int ID_PRODUCT_LIST_VIEW = R.id.lv_products;

    Map<Coin, Integer> insertCoinResourceIds;
    Map<Coin, Button> insertButtons;
    TextView statusDisplay;
    ListView productListView;

    private Activity activity;
    private VendingController vendingController;
    Map<String, Button> productButtons;

    public VendingView(Activity activity) {
        this.activity = activity;
        insertCoinResourceIds = new HashMap<>();
        insertCoinResourceIds.put(Coin.QUARTER, ID_QUARTER);
        insertCoinResourceIds.put(Coin.DIME, ID_DIME);
        insertCoinResourceIds.put(Coin.NICKEL, ID_NICKEL);
        insertCoinResourceIds.put(Coin.PENNY, ID_PENNY);
        insertCoinResourceIds.put(Coin.OTHER, ID_OTHER);
        insertButtons = new HashMap<>();
        productButtons = new HashMap<>();
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
            insertButtons.get(coin).setText(coin.name());
        }
        Product[] products = vendingController.getProducts();

        statusDisplay = activity.findViewById(ID_STATUS_DISPLAY);

        productListView = activity.findViewById(ID_PRODUCT_LIST_VIEW);
    }

    private void bindViews() {
        Coin[] coins = Coin.values();
        for (Coin coin : coins) {
            insertButtons.get(coin).setOnClickListener(vendingController.getInsertClickHandler(coin));
        }

        productListView.setOnItemClickListener(vendingController.getProductClickHandler());
    }

    public void updateVendStatus(ControlBoard.VendState vendState, int currentAmountAccepted) {
        if (vendState.equals(ControlBoard.VendState.INSERT_COIN)) {
            statusDisplay.setText(activity.getString(ID_INSERT_COIN_STR));
        } else {
            statusDisplay.setText(formatAmount(currentAmountAccepted));
        }
    }

    private String formatAmount(float amount) {
        double usd = amount / 100d;
        return NumberFormat.getCurrencyInstance().format(usd);
    }

}
