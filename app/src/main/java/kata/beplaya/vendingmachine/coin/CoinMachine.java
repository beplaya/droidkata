package kata.beplaya.vendingmachine.coin;

public class CoinMachine {
    public boolean insert(Coin coin) {
        return true;
    }

    public enum Coin{
        NICKEL, QUARTER, DIME, PENNY, Coin
    }

    public float getCurrentAmount() {
        return 0f;
    }
}
