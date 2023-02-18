package classes;

// Тут начинается магия

/* Обобщенный класс Goods<T> в качестве типа 
 * Т может принимать любой класс, являющийся
 * наследником класса "Продукт". 
 * Это ограничения нужно, чтобы какой-нибудь 
 * малолетний ДЕБИЛ не отправил сюда число 
 * или, не дай Бог, массив... */
public class Goods<T extends Product> {
    public T product_; // Продукт, хранимый в обобщенном классе

    // Конструктор
    public Goods(T product) {
        this.product_ = product;
    }

    // Геттер
    public final T GetProduct() {
        return this.product_;
    }

    // Возвращает имя класса, который содержится внутри
    public String GetProductType() {
        return this.product_.getClass().getName();
    }
}