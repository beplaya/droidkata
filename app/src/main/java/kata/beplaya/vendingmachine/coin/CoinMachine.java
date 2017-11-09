package kata.beplaya.vendingmachine.coin;

public class CoinMachine {

    private int currentAmount = 0;
    private ReturnTray returnTray;

    public CoinMachine(ReturnTray returnTray) {
        this.returnTray = returnTray;
    }

    public enum Coin{
        NICKEL(5), QUARTER(25), DIME(10), PENNY(1), OTHER(0);
        private float value;

        Coin(float value){
            this.value = value;
        }
    }

    public boolean insert(Coin coin) {
        boolean isValid = isValid(coin);
        if(isValid){
            currentAmount += coin.value;
        } else{
            returnTray.onReturn(coin);
        }
        return isValid;
    }

    private boolean isValid(Coin coin) {
        return coin.equals(Coin.QUARTER) ||  coin.equals(Coin.DIME) ||  coin.equals(Coin.NICKEL);
    }

    public int getCurrentAmount() {
        return currentAmount;
    }
}
