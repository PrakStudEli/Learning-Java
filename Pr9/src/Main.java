import classes.*;

import java.util.Date;       // Для генератора случайных чисел 
import java.util.Vector;     // Без динамических массивов не надо, а с ними и пусть будет
import java.util.Random;     // Ну не в ручную же писать
import java.util.HashMap;    // Для подсчета типов товаров, сопоставляет одно значение другому

public class Main {

    // Постоянно используемый в классе генератор случайных чиселок
    public static Random rand = new Random((new Date()).getTime());

    // Генерация и наполнение вектора еды
    public static Vector<Food> GenerateFoodVector(
                            int obj_count) {
        
        Vector<Food> foods = new Vector<Food>();

        for (int i = 0; i < obj_count; ++i) {
            long seed = rand.nextInt();
            Food food_obj = new Food();
            food_obj.SetRandomParameters(seed);
            foods.add(food_obj);
        }

        return foods;
    }

    // Генерация и наполнение вектора посуды
    public static Vector<Dish> GenerateDishVector(
                            int obj_count) {
              
        Vector<Dish> dishes = new Vector<Dish>();

        for (int i = 0; i < obj_count; ++i) {
            long seed = rand.nextInt();
            Dish dish_obj = new Dish();
            dish_obj.SetRandomParameters(seed);
            dishes.add(dish_obj);
        }

        return dishes;
    }

    // Шаблонный метод для вывода свойств массивов продуктов вместе с ценами на них
    public static <T extends Product & CalcPriceInterface> void PrintProductsWithPrices(Vector<T> products) {
        for (var product : products) {
            double price = product.CalcPrice();
            System.out.println(product);            
            System.out.printf("Price: %.2f\n", price);
        }
    }
    
    // Маин кампф
    public static void main(String args[]) {
        final int foods_count = rand.nextInt(10);   // Случайно создаваемое количество объектов класса "Еда"
        final int dishes_count = rand.nextInt(10);  // То же, но для посуды
        
        // Сопоставляет название класса количеству его экземпляров в массиве
        HashMap<String, Integer> types_to_quantities = new HashMap<String, Integer>();

        // Генерация массивов с едой/посудой
        Vector<Food> foods = GenerateFoodVector(foods_count);
        Vector<Dish> dishes = GenerateDishVector(dishes_count);

        // Стоимость покупок
        double cart = 0.0;

        // Массив продуктов, в котором может быть как экземпляры еды, так и 
        // посуды одновременно. Их и надо посчитать
        Vector<Goods<Product>> goods_vector = new Vector<Goods<Product>>();

        // Засовываем еду в вектор продуктов
        for (var food : foods) {
            double price = food.CalcPrice();
            goods_vector.add(new Goods<Product>(food));
            cart += price;
        }
        
        // Засовываем посуду в вектор продуктов
        for (var dish : dishes) {
            double price = dish.CalcPrice();
            goods_vector.add(new Goods<Product>(dish));
            cart += price;
        }

        // Считаем количество товаров каждого типа
        for (var goods_element : goods_vector) {

            // Текущий тип продукта
            String product_type = goods_element.GetProductType();
            
            // Создание записи для типа продукта в таблице, если ее еще нет
            types_to_quantities.putIfAbsent(product_type, 0);

            // Обновляем количество товаров в таблице
            int product_quantity = types_to_quantities.get(product_type) + 1;
            types_to_quantities.put(product_type, product_quantity);            
        }

        // Вывод всей еды и посуды
        PrintProductsWithPrices(foods);
        PrintProductsWithPrices(dishes);

        // Вывод стоимости корзины
        System.out.printf("Total cart price: %.2f\n", cart);

        // Вывод количества товаров каждого типа
        System.out.println(types_to_quantities);
    }
}