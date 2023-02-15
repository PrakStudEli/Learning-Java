package classes;
import java.util.Random;

public class Foods implements CalcPriceInterface {
    public double CalcPrice(double cost_price, double demand) {
        return (Math.cos(cost_price * demand)/demand) + demand + cost_price/demand;
    }

    public double GetRandomDemand() {
        Random rand = new Random();
        return rand.nextDouble() * 10;
    }

    public double GetRandomCostPrice() {
        Random rand = new Random();
        return rand.nextDouble() * 100;
    }
};
