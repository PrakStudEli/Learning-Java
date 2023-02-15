package classes;

// Абстрактный класс "Продукт", от которого наследуются другие типы товаров
public abstract class Product {
    private double cost_price_;  // Себестоимость
    private double demand_;      // Спрос

    // Конструкторы
    public Product() {
        this(0.0, 0.0);
    }

    public Product(double cost_price, double demand) {
        this.cost_price_ = cost_price;
        this.demand_ = demand;
    }

    // Геттеры
    public double GetCostPrice() {
        return this.cost_price_;
    }

    public double GetDemand() {
        return this.demand_;
    }

    // Сеттеры
    public void SetCostPrice(double cost_price) {
        this.cost_price_ = cost_price;
    }

    public void SetDemand(double demand) {
        this.demand_ = demand;
    }

    // Метод для присвоения случайных параметров полям класса
    // У каждого класса он свой, поэтому тут я сделал его абстрактным
    public abstract void SetRandomParameters(long seed);
    
    // Метод для удобного вывода объекта метода в System.out 
    public abstract String toString();
}
