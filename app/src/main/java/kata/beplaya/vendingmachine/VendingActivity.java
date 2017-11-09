package kata.beplaya.vendingmachine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kata.beplaya.vendingmachine.coin.CoinMachine;
import kata.beplaya.vendingmachine.coin.ReturnTray;
import kata.beplaya.vendingmachine.coin.VendingView;

public class VendingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vending);
        new VendingController(new CoinMachine(new ReturnTray()), new VendingView(this));
    }
}
