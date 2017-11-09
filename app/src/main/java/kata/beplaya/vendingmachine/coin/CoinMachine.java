package kata.beplaya.vendingmachine.coin;

public class CoinMachine {
    public boolean insert(Coin coin) {
        return true;
    }

    public enum Coin{
        NICKEL, QUARTER, DIME, Coin
    }

    public float getCurrentAmount() {
        return 0f;
    }
}
