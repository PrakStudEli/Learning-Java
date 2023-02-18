import classes.*;

import java.util.Date;       // Для генератора случайных чисел 
import java.util.Vector;     // Без динамических массивов не надо, а с ними и пусть будет
import java.util.Random;     // Ну не в ручную же писать

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

    // Маин кампф
    public static void main(String args[]) {

        final int foods_count = rand.nextInt(10);   // Случайно создаваемое количество объектов класса "Еда"
        final int dishes_count = rand.nextInt(10);  // То же, но для посуды

        // Генерация массивов с едой/посудой
        Vector<Food> foods = GenerateFoodVector(foods_count);
        Vector<Dish> dishes = GenerateDishVector(dishes_count);

        // Массив продуктов, в котором может быть как экземпляры еды, так и 
        // посуды одновременно. Их и надо посчитать
        Vector<Goods<Product>> goods_vector = new Vector<Goods<Product>>();

        // Засовываем еду в вектор продуктов
        for (var food : foods) {
            goods_vector.add(new Goods<Product>(food));
        }
        
        // Засовываем посуду в вектор продуктов
        for (var dish : dishes) {
            goods_vector.add(new Goods<Product>(dish));
        }

        // Создаем экземпляр класса-задания
        ComputingIndicatorRunnable computing_indicator_task = new ComputingIndicatorRunnable(goods_vector);

        // Создаем экземпляры классов-потоков
        Thread counting_types = new CountingTypesThread<Goods<Product>>(goods_vector);
        Thread computing_indicator = new Thread(computing_indicator_task, "Computing indicator");

        // Запускаем потоки
        counting_types.start();
        computing_indicator.start();

        // Обязательно обрабатываем исключение, при котором главный поток завершается раньше
        try {
            counting_types.join();
            computing_indicator.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread was interrupted. Shutting down...");
        }

        // Сигнализируем о заврешении главного потока для наглядности
        System.out.println("The end!");
    }
}