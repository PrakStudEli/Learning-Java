package classes;

import java.util.Vector;
import java.util.HashMap;

public class CountingTypesThread<T extends Goods<? extends Product>> 
extends Thread {

    // Передаваемый в поток вектор экземпляров шаблонного класса Goods
    private Vector<T> goods_vector_;

    // Словарь, применяемый для расчета количества экземпляров каждого типа в векторе
    private HashMap<String, Integer> types_to_quantities_;
    
    // Конструктор
    public CountingTypesThread(Vector<T> goods_vector) {
        this.goods_vector_ = goods_vector;
        this.types_to_quantities_ = new HashMap<String, Integer>();
    }

    // Геттеры
    public final HashMap<String, Integer> GetTypesToQuantities() {
        return this.types_to_quantities_;
    }

    public final Vector<T> GetGoodsVector() {
        return goods_vector_;
    }

    // Сеттеры
    public void SetGoodsVector(Vector<T> goods_vector) {
        this.goods_vector_ = goods_vector;
    }

    // Выводит словарь в особом формате
    private final static void PrintHashMapFormatted(HashMap<?, ?> map) {
        for (var key : map.keySet()) {
            var value = map.get(key);
            System.out.println(key + ": " + value);
        }
    }

    // Глава угла
    @Override
    public void run() {
        System.out.println("Counting types");

        // Считаем количество товаров каждого типа
        for (var goods_element : this.goods_vector_) {

            // Текущий тип продукта
            String product_type = goods_element.GetProductType();
            
            // Создание записи для типа продукта в таблице, если ее еще нет
            this.types_to_quantities_.putIfAbsent(product_type, 0);

            // Обновляем количество товаров в таблице
            int product_quantity = this.types_to_quantities_.get(product_type) + 1;
            this.types_to_quantities_.put(product_type, product_quantity);            
        }

        PrintHashMapFormatted(types_to_quantities_);

        System.out.println("Done counting types!");
    }
}