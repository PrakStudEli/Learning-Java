package classes;

public class Goods {
    private double _cost_price;
    private double _demand;
    private GoodsType _type;

    public Goods() {
        this(0.0, 0.0, GoodsType.FOODS);
    }
    
    public Goods(double cost_price, double demand, GoodsType type) {
        this._demand = demand;
        this._cost_price = cost_price;
        this._type = type;
    }

    public double GetCostPrice() {
        return this._cost_price;
    }

    public double GetDemand() {
        return this._demand;
    }
    
    public GoodsType GetType() {
        return this._type;
    }
    
    public void SetRandomAttributes(CalcPriceInterface calc_price_callback) {
        this._cost_price = calc_price_callback.GetRandomCostPrice();
        this._demand = calc_price_callback.GetRandomDemand();
    }

    public void SetCostPrice(double cost_price) {
        this._cost_price = cost_price;
    }

    public void SetDemand(double demand) { 
        this._demand = demand;
    }
    
    public void SetType(GoodsType type) {
        this._type = type;
    }
    
    public void PrintOut() {
        System.out.printf(
                "Costprice: %.2f\nDemand: %.2f\n", 
                this._cost_price, this._demand
                );
    }

    public double GetPrice(CalcPriceInterface calc_price_callback) {
        return calc_price_callback.CalcPrice(this._cost_price, this._demand);
    }
};
