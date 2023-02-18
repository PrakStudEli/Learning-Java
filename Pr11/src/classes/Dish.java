package classes;

import java.util.Random;

// Класс "Посуда" расширяет класс "Продукт"
// и реализует интерфейс расчета стоимости
public class Dish extends Product {
    private double brand_recognition_coeff_;  // Коэффициент популярности бренда

    // Конструкторы
    public Dish() {
        this(0.0, 0.0, 0.0);
    }

    public Dish(double cost_price, 
                double demand, 
                double brand_recognition) {
        super(cost_price, demand);
        this.brand_recognition_coeff_ = brand_recognition;
    }

    // Геттеры
    public double GetBrandRecognitionCoeff() {
        return this.brand_recognition_coeff_;
    }

    // Сеттеры
    public void SetBrandRecognitionCoeff(
                double brand_recognition) {
        this.brand_recognition_coeff_ = brand_recognition;
    }

    // Устанавливает случайные параметры
    public void SetRandomParameters(long seed) {
        Random rand = new Random(seed);
        double rand_cost_price = rand.nextDouble() * 100;
        double rand_demand = rand.nextDouble() * 4;
        double rand_brand = rand.nextDouble() * 10;
        
        this.SetCostPrice(rand_cost_price);
        this.SetDemand(rand_demand);
        this.SetBrandRecognitionCoeff(rand_brand);
    }

    // Расчет стоимости
    public double CalcPrice() {
        double cost_price = this.GetCostPrice();
        double demand = this.GetDemand();
        double brand = this.brand_recognition_coeff_;
        
        return brand* Math.pow((demand + cost_price), 2)/demand;
    }

    // Преобразование в строку
    public String toString() {
        String result = "{ Costprice: " + this.GetCostPrice() + ", Demand: " + this.GetDemand() + ", Brand: " + this.brand_recognition_coeff_ + " }"; 
        
        return result;
    }
}