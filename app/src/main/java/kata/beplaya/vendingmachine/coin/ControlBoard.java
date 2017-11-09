package kata.beplaya.vendingmachine.coin;

public class ControlBoard {

    public enum VendState{
        COINS_INSERTED, INSERT_COIN
    }
    private CoinMachine coinMachine;

    public ControlBoard(CoinMachine coinMachine) {
        this.coinMachine = coinMachine;
    }

    public VendState determineVendState() {
        return coinMachine.getCurrentAmount() == 0 ? VendState.INSERT_COIN : VendState.COINS_INSERTED;
    }

}
