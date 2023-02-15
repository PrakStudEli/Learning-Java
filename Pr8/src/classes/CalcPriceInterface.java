package classes;

public interface CalcPriceInterface {
    public double CalcPrice(double cost_price, double demand);
    public double GetRandomDemand();
    public double GetRandomCostPrice();
};
