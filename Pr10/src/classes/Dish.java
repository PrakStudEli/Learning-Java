package classes;

import java.util.Random;

public class Dish extends Goods {
    private double brand_recognition_coeff_;

    public Dish() {
        this(0.0, 0.0, 0.0);
    }

    public Dish(double cost_price, 
                double demand, 
                double brand_recognition) {
        super(cost_price, demand);
        this.brand_recognition_coeff_ = brand_recognition;
    }

    public final double GetBrandRecognitionCoeff() {
        return this.brand_recognition_coeff_;
    }

    public void SetBrandRecognitionCoeff(
                double brand_recognition) {
        this.brand_recognition_coeff_ = brand_recognition;
    }

    public void SetRandomParameters(long seed) {
        Random rand = new Random(seed);
        double rand_cost_price = rand.nextDouble() * rand.nextInt(300);
        double rand_demand = rand.nextDouble() * 4;
        double rand_brand = rand.nextDouble() * 10;
        
        this.SetCostPrice(rand_cost_price);
        this.SetDemand(rand_demand);
        this.SetBrandRecognitionCoeff(rand_brand);
    }

    public final double ComputeIndicator() {
        double cost_price = this.GetCostPrice();
        double demand = this.GetDemand();
        double brand = this.brand_recognition_coeff_;
        
        return brand* Math.pow((demand + cost_price), 2)/demand;
    }

    public final String toString() {
        String result = "{ id: " + this.GetId() + ", Costprice: " + this.GetCostPrice() + ", Demand: " + this.GetDemand() + ", Brand: " + this.brand_recognition_coeff_ + " }"; 
        
        return result;
    }
}