package classes;

import java.util.Random;

// Класс "Еда" расширяет класс "Продукт" и 
// реализует интерфейс расчета стоимости
public class Food extends Product {
    private int days_since_manufactured_; // Кол-во дней с момента выпуска

    // Конструкторы
    public Food() {
        this(0.0, 0.0, 0);
    }

    public Food(double cost_price, 
                 double demand, 
                 int days) {
        super(cost_price, demand);
        this.days_since_manufactured_ = days;
    }

    // Геттеры
    public int GetDaysSinceManufacture() {
        return this.days_since_manufactured_;
    }

    // Сеттеры
    public void SetDaysSinceManufactured(int days) {
        this.days_since_manufactured_ = days;
    }

    // Устанавливает случайные значения полей класса
    public void SetRandomParameters(long seed) {
        Random rand = new Random(seed);
        double rand_cost_price = rand.nextDouble() * 100;
        double rand_demand = rand.nextDouble() * 10;
        int rand_days = rand.nextInt(365);
        
        this.SetCostPrice(rand_cost_price);
        this.SetDemand(rand_demand);
        this.SetDaysSinceManufactured(rand_days);
    }
    
    // Расчет стоимости
    public double CalcPrice() {
        double demand = this.GetDemand();
        double cost_price = this.GetCostPrice();
        int days = this.days_since_manufactured_;

        return (1/days) * Math.cos(demand * cost_price) + demand + cost_price/demand;
    }

    // Преобразование в строку
    public String toString() {
        String result = "{ Costprice: " + this.GetCostPrice() + ", Demand: " + this.GetDemand() + ", Days: " + this.days_since_manufactured_ + " }"; 
        
        return result;
    }
}