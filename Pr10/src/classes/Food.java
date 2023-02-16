package classes;

import java.util.Random;

public class Food extends Goods {
    private int days_since_manufactured_;

    public Food() {
        this(0.0, 0.0, 0);
    }

    public Food(double cost_price, 
                 double demand, 
                 int days) {
        super(cost_price, demand);
        this.days_since_manufactured_ = days;
    }

    public int GetDaysSinceManufacture() {
        return this.days_since_manufactured_;
    }

    public void SetDaysSinceManufactured(int days) {
        this.days_since_manufactured_ = days;
    }

    public void SetRandomParameters(long seed) {
        Random rand = new Random(seed);
        double rand_cost_price = rand.nextDouble() * rand.nextInt(200);
        double rand_demand = rand.nextDouble() * 10;
        int rand_days = rand.nextInt(365);
        
        this.SetCostPrice(rand_cost_price);
        this.SetDemand(rand_demand);
        this.SetDaysSinceManufactured(rand_days);
    }
    
    public double ComputeIndicator() {
        double demand = this.GetDemand();
        double cost_price = this.GetCostPrice();
        int days = this.days_since_manufactured_;

        return (1/days) * Math.cos(demand * cost_price) + demand + cost_price/demand;
    }

    public String toString() {
        String result = "{ id: " + this.GetId() + ", Costprice: " + this.GetCostPrice() + ", Demand: " + this.GetDemand() + ", Days: " + this.days_since_manufactured_ + " }"; 
        
        return result;
    }
}