package classes;
import java.util.Random;

public class Dishes implements CalcPriceInterface {
    public double CalcPrice(double cost_price, double demand) {
        return Math.pow((demand + cost_price), 2)/demand;
    }
    
    public double GetRandomDemand() {
        Random rand = new Random();
        return rand.nextDouble() * 7;
    }

    public double GetRandomCostPrice() {
        Random rand = new Random();
        return rand.nextDouble() * 500;
    }
};
