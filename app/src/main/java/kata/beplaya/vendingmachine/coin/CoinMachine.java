package kata.beplaya.vendingmachine.coin;

public class CoinMachine {
    public boolean insert(Coin coin) {
        return isValid(coin);
    }

    private boolean isValid(Coin coin) {
        return coin.equals(Coin.QUARTER) ||  coin.equals(Coin.DIME) ||  coin.equals(Coin.NICKEL);
    }

    public enum Coin{
        NICKEL, QUARTER, DIME, PENNY, UNRECOGNIZED, Coin
    }

    public float getCurrentAmount() {
        return 0f;
    }
}
