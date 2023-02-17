package classes;

public abstract class Goods 
implements ComputeIndicatorIface {
    private static int global_id_;
    private int id_;
    private double cost_price_;
    private double demand_;

    public Goods() {
        this(0.0, 0.0);
    }

    public Goods(double cost_price, double demand) {
        this.cost_price_ = cost_price;
        this.demand_ = demand;
        this.id_ = global_id_;
        global_id_++;
    }

    public final int GetId() {
        return this.id_;
    }

    public final double GetCostPrice() {
        return this.cost_price_;
    }

    public final double GetDemand() {
        return this.demand_;
    }

    public void SetCostPrice(double cost_price) {
        this.cost_price_ = cost_price;
    }

    public void SetDemand(double demand) {
        this.demand_ = demand;
    }

    public void ResetId() {
        global_id_ = 0;
    }

    public void SetId(int id) {
        global_id_ = id;
    }

    public abstract void SetRandomParameters(long seed);
    
    public abstract String toString();
}
