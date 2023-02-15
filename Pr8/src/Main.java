import classes.*;

public class Main {
    public static void main(String[] args) {
        Goods foods[] = new Goods[3];
        Goods dishes[] = new Goods[3];
        
        Foods foods_interface = new Foods();
        Dishes dishes_interface = new Dishes(); 

        for (int i = 0; i < foods.length && i < dishes.length; ++i) {
            foods[i] = new Goods(0.0, 0.0, GoodsType.FOODS);
            foods[i].SetRandomAttributes(foods_interface);
            
            System.out.println("Foods #" + (i + 1));

            foods[i].PrintOut();

            System.out.printf("Price: %.2f\n", foods[i].GetPrice(foods_interface));

            dishes[i] = new Goods(0.0, 0.0, GoodsType.DISHES);
            dishes[i].SetRandomAttributes(dishes_interface);

            System.out.println("Dishes #" + (i + 1));

            dishes[i].PrintOut();

            System.out.printf("Price: %.2f\n", dishes[i].GetPrice(dishes_interface));
        }

        
    }
};

