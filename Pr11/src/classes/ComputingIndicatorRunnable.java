package classes;

import java.util.Vector;

public class ComputingIndicatorRunnable
implements Runnable {
    private Vector<Goods<Product>> goods_vector_;
    private double total_;

    // Конструктор
    public ComputingIndicatorRunnable(Vector<Goods<Product>> goods_vector) {
        this.goods_vector_ = goods_vector;
        this.total_ = 0.0;
    }

    // Геттеры
    public final double GetTotal() {
        return this.total_;
    }

    public final Vector<Goods<Product>> GetGoodsVector() {
        return this.goods_vector_;
    }

    // Сеттеры
    public void SetGoodsVector(Vector<Goods<Product>> goods_vector) {
        this.goods_vector_ = goods_vector;
    }

    // Шаблонный метод для расчета и вывода цены продукта.
    private final static <T extends Product & CalcPriceInterface> 
    double GetPriceAndPrint(T product) {
        double price = product.CalcPrice();
        System.out.println(product);            
        System.out.printf("Price: %.2f\n", price);
        return price;
    }

    // Во главе угла
    public void run() {
        System.out.println("Computing an indicator!");

        // Рассчитываем стоимость корзины
        for (var element : this.goods_vector_) {
            var product = element.GetProduct();
            this.total_ += GetPriceAndPrint(product);
        }

        // Вывод стоимости корзины
        System.out.printf("Total cart price: %.2f\n", this.total_);

        System.out.println("Done computing prices!");
    }
}   
