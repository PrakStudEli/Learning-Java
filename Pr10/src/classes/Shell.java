package classes;

import java.util.Scanner;
import java.util.Vector;
import java.util.HashMap;
import java.util.LinkedList;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Date;
import java.util.Random;

import classes.command.Command;

public class Shell {
    // Переменная, контролирующая вывод некоторых отладочных сообщений
    private static boolean DEBUG_MODE = false;

    // Переменная, контроллирущая вывод ответных сообщений
    private static boolean ENABLE_MESSAGES = true;

    // Хранит списки поименно: Имя -> Список
    private HashMap<String, List<Goods>> stored_lists_with_names_ = new HashMap<String, List<Goods>>();
    
    // Штатный ГПСЧ
    private static Random rand_ = new Random((new Date()).getTime());

    // Выводит ответные сообщения с символом новой строки в конце
    private void MessagePrintln(String message) {
        if (ENABLE_MESSAGES) System.out.println(message);
    }

    // Выводит ответные сообщения
    private void MessagePrint(String message) {
        if (ENABLE_MESSAGES) System.out.print(message);
    }

    // Выводит отладочную информацию с символом новой строки в конце
    private void DebugInfoPrintln(String info) {
        if (DEBUG_MODE) System.out.println(info);
    }

    // Выводит отладочную информацию
    private void DebugInfoPrint(String info) {
        if (DEBUG_MODE) System.out.print(info);
    }

    // Проверяет, имеется ли в хранении указанный список
    private boolean IsListStored(String name) {
        return stored_lists_with_names_.containsKey(name);
    }
    
    // Удаляет список с предварительной проверкой оного на существование
    private boolean DeleteListSafe(String name) {
        return stored_lists_with_names_.remove(name) != null;
    }

    // Возвращает список по имени, предварительно проверяя существование оного и оповещает если списка нет
    private List<Goods> GetListSafe(String list_name) {
        if (!IsListStored(list_name)) {
            System.out.println("No such list");
            return null;
        }
        return stored_lists_with_names_.get(list_name);
    }

    private boolean IsObjNull(Object el) {
        if (el == null) {
            DebugInfoPrintln("Object is null");
            return true;
        }
        return false;
    }

    // Находит элемент и выдать его. Не очень эффективно
    private Goods GetElementFromList(List<Goods> list, int id) {
        for (var element : list) {
            if (element.GetId() == id) {
                DebugInfoPrintln("Found");
                return element;
            }
        }
        return null;
    }

    // Выводит цену товара
    private void PrintElementPrice(Goods el) {
        System.out.printf("Price of the element with id %d is %.2f\n", el.GetId(), el.ComputeIndicator());
    }

    // Находит элемент в списке и выдать его с предварительными проверками
    private Goods GetElementFromListSafe(String list_name, int id) {
        var list = GetListSafe(list_name);

        if (IsObjNull(list)) return null;

        var el = GetElementFromList(list, id);

        return el;
    }

    // Удаляет элемент из списка с предварительной проверкой
    private boolean DeleteElementFromListSafe(String list_name, Goods el) {
        var list = GetListSafe(list_name);
        return list.remove(el);
    }

    // Выводит суммарную стоимость всех товаров в списке
    private void PrintListPrice(double price) {
        System.out.printf("List price is %.2f\n", price);
    }

    // Вычисляет суммарную стоимость всех товаров в списке
    private double ComputeListPrice(List<Goods> list) {
        double cart = 0.0;
        
        for (var el : list) {
            cart += el.ComputeIndicator();
        }

        return cart;
    }

    // Выводит приглашение в начале строки
    private void ShowPrompt(String prompt) {
        System.out.print(prompt + " ");
    }

    // Добавляет в конец списка указанное количество элементов
    private boolean AppendElementsToList(String list_name, int el_num) {
        var list = GetListSafe(list_name);

        if (IsObjNull(list)) return false;

        for (int i = 0; i < el_num; ++i) {
            if(rand_.nextBoolean()) {
                Food f = new Food();
                f.SetRandomParameters(i);
                list.add(f);
            } else {
                Dish d = new Dish();
                d.SetRandomParameters(i);
                list.add(d);
            }
        }
        return true;
    }

    // Добавляет новый связанный список
    private boolean AddLinkedList(String name, int length) {
        LinkedList<Goods> list = new LinkedList<Goods>();
        
        if (IsObjNull(list)) return false; 

        for (int i = 0; i < length; ++i) {
            if(rand_.nextBoolean()) {
                Food f = new Food();
                f.SetRandomParameters(i);
                list.add(f);
            } else {
                Dish d = new Dish();
                d.SetRandomParameters(i);
                list.add(d);
            }
        }
        stored_lists_with_names_.putIfAbsent(name, list);
        return true;
    }

    // Добавляет новый массив
    private boolean AddArrayList(String name, int length) {
        ArrayList<Goods> list = new ArrayList<Goods>();
        
        if (IsObjNull(list)) return false;

        for (int i = 0; i < length; ++i) {
            if(rand_.nextBoolean()) {
                Food f = new Food();
                f.SetRandomParameters(i);
                list.add(f);
            } else {
                Dish d = new Dish();
                d.SetRandomParameters(i);
                list.add(d);
            }
        }
        stored_lists_with_names_.putIfAbsent(name, list);
        return true;
    }

    // Выводит все списки и значения в них
    private void PrintLists() {
        for (var entry : stored_lists_with_names_.entrySet()) {
            System.out.println(entry.getKey() + ":");
            for (var element : entry.getValue()) {
                System.out.println("  -  " + element);
            }
        }
    }

    // Получает число из строки и не боится ошибок. Выводит -1 если в строке нет цифр
    private int GetIntegerFromStringSafe(String str) {
        try {
            return Integer.valueOf(str);
        } catch (NumberFormatException e) {
            MessagePrintln("One of the arguments is not an integer");
            return -1;
        }
    }

    // Проводит тесты
    private boolean RunDiagnosticsList(int length, List<Goods> list) {
        if (length > 100000) {
            DebugInfoPrintln("Length is too big, it will be set to 100000 for safety reasons");
            length = 100000;
        }
        
        DebugInfoPrintln("    Testing list creation...");

        Date array_list_start_time = new Date();
        
        for (int i = 0; i < length; ++i) {
            if(rand_.nextBoolean()) {
                Food f = new Food();
                f.SetRandomParameters(i);
                list.add(f);
            } else {
                Dish d = new Dish();
                d.SetRandomParameters(i);
                list.add(d);
            }
        }

        Date array_list_end_time = new Date();
        long array_list_diff = array_list_end_time.getTime() - array_list_start_time.getTime();

        DebugInfoPrintln("      Result: " + array_list_diff + " ms.");

        DebugInfoPrintln("    Appending elements");
        array_list_start_time = new Date();

        Food f = new Food();
        f.SetRandomParameters(array_list_diff);
        list.add(f);

        array_list_end_time = new Date();
        array_list_diff = array_list_end_time.getTime() - array_list_start_time.getTime();

        DebugInfoPrintln("      Result: " + array_list_diff + " ms.");

        DebugInfoPrintln("    Accessing random elements");
        array_list_start_time = new Date();

        list.get(rand_.nextInt(length));
        
        array_list_end_time = new Date();
        array_list_diff = array_list_end_time.getTime() - array_list_start_time.getTime();

        DebugInfoPrintln("      Result: " + array_list_diff + " ms.");

        DebugInfoPrintln("    Testing contains() method");

        array_list_start_time = new Date();

        list.contains(f);

        array_list_end_time = new Date();
        array_list_diff = array_list_end_time.getTime() - array_list_start_time.getTime();

        DebugInfoPrintln("      Result: " + array_list_diff + " ms.");
        
        return true;
    }

    // Отвечает на ввод пользователя
    public boolean RunSession() {
        ShowPrompt("PR10-SH>");
        UserInput usr_input = GetInput(System.in);

        if (IsObjNull(usr_input)) return true;

        switch(usr_input.cmd) {
            case APPEND: {
                usr_input.args.setSize(Command.CREATE_LIST.GetArgs());
                
                String name = usr_input.args.get(0);

                int length = GetIntegerFromStringSafe(usr_input.args.get(1));

                if (length < 0) return true;

                if(AppendElementsToList(name, length)) MessagePrintln(name + ": List expanded successfully");

                return true;
            }
            case CREATE_LIST: {
                usr_input.args.setSize(Command.CREATE_LIST.GetArgs());
                
                String name = usr_input.args.get(0);

                int length = GetIntegerFromStringSafe(usr_input.args.get(1));

                if (length < 0) return true;

                AddLinkedList(name, length);

                if(AddLinkedList(name, length)) MessagePrintln(name + ": List created successfully");

                return true;
            }   
            case CREATE_ARRLIST: {
                usr_input.args.setSize(Command.CREATE_ARRLIST.GetArgs());

                String name = usr_input.args.get(0);
                int length = GetIntegerFromStringSafe(usr_input.args.get(1));

                if (length < 0) return true;

                if(AddArrayList(name, length)) MessagePrintln(name + ": ArrayList created successfully");

                return true;
            }
            case FIND: {
                usr_input.args.setSize(Command.FIND.GetArgs());

                String name = usr_input.args.get(0);
                int id = GetIntegerFromStringSafe(usr_input.args.get(1));

                if (id < 0) return true;
                
                var el = GetElementFromListSafe(name, id);

                if (!IsObjNull(el)) {
                    MessagePrintln(name + ": Element with id " + id + " was found in list " + name);
                } else {
                    MessagePrintln(name + ": No such element in list " + name);
                    return true;
                }
                return true;
            }
            case DELETE: {
                usr_input.args.setSize(Command.DELETE.GetArgs());

                String name = usr_input.args.get(0);

                int id = GetIntegerFromStringSafe(usr_input.args.get(1));

                if (id < 0) return true;

                var el = GetElementFromListSafe(name, id);

                if (IsObjNull(el)) {
                    MessagePrintln("An error occured");
                    return true;
                } 

                if (DeleteElementFromListSafe(name, el)) MessagePrintln(name + ": Element successfully deleted");
                return true;
            }
            case DELETE_LIST: {
                usr_input.args.setSize(Command.DELETE_LIST.GetArgs());
                String name = usr_input.args.get(0);

                if (DeleteListSafe(name)) MessagePrintln(name + ": List successfully deleted");
                else MessagePrintln(name + ": List doesn't exist");

                return true;
            }
            case COMPUTE: {
                usr_input.args.setSize(Command.COMPUTE.GetArgs());

                String name = usr_input.args.get(0);
                int id = GetIntegerFromStringSafe(usr_input.args.get(1));

                if (id < 0) return true;
                
                var el = GetElementFromListSafe(name, id);

                if (IsObjNull(el)) return true;

                PrintElementPrice(el);
                return true;
            }
            case COMPUTE_LIST: {
                usr_input.args.setSize(Command.COMPUTE_LIST.GetArgs());

                String name = usr_input.args.get(0);
                
                var list = GetListSafe(name);

                if (IsObjNull(list)) return true;
                
                double price = ComputeListPrice(list);

                PrintListPrice(price);
                return true;
            }
            case PRINT_LISTS:
                PrintLists();
                return true;
            case EXIT:
                usr_input.args.setSize(Command.EXIT.GetArgs());
                return false;
            case HELP:
                usr_input.args.setSize(Command.HELP.GetArgs());
                ShowOptions();
                return true;
            case NULL_CMD:
                return true;
            case ERR:
                usr_input.args.setSize(Command.NULL_CMD.GetArgs());
                MessagePrintln("Unknown command");
                return true;
            case DEBUG:
                DEBUG_MODE = true;
                MessagePrintln("Debug mode enabled");
                return true;
            case NO_DEBUG:
                DEBUG_MODE = false;
                MessagePrintln("Debug mode disabled");
                return true;
            case MESSAGE:
                ENABLE_MESSAGES = true;
                MessagePrintln("Messages enabled");
                return true;
            case NO_MESSAGE:
                ENABLE_MESSAGES = false;
            case DIAGNOSE:
                if (!DEBUG_MODE) {
                    MessagePrintln("This command is only available in DEBUG mode");
                    return true;
                }

                usr_input.args.setSize(Command.DIAGNOSE.GetArgs());

                int length = GetIntegerFromStringSafe(usr_input.args.get(0));

                if (length < 0) return true;

                LinkedList<Goods> linked_list = new LinkedList<Goods>();
                ArrayList<Goods> array_list = new ArrayList<Goods>();
                try {
                    DebugInfoPrintln("ArrayList tests:");
                    RunDiagnosticsList(length, array_list);

                    DebugInfoPrintln("LinkedList tests:");
                    RunDiagnosticsList(length, linked_list);
                } catch (OutOfMemoryError e) {
                    DebugInfoPrintln("System error: Out of memory.\nTest has been aborted.");
                    return true;
                }
                return true;
            default: 
                return true;
        }
    }

    // Считывает ввод пользователя и не пугается, если что-то не так
    private String GetLineSafe(InputStream in) {
        Scanner read_line = new Scanner(in);
        String input = new String();
        try {
            input = read_line.nextLine();
        } catch (NoSuchElementException e) {
            System.out.println("Wrong input");
            return "";
        }
        return input;
    }

    // Обрабатывает ввод пользователя
    public UserInput GetInput(InputStream in) {
        String input = GetLineSafe(in);
        Vector<String> args = new Vector<String>();

        if (input.isEmpty()) return new UserInput(Command.NULL_CMD, args);

        Scanner input_analyzer = new Scanner(input);
        String command_name = input_analyzer.next();
        

        Command command = Command.ERR;

        for (Command cmd : Command.values()) {
            if (!command_name.equals(cmd.GetName())) continue;
            command = cmd;
        }

        for (int i = 0; i < command.GetArgs() && input_analyzer.hasNext(); ++i) {
            args.add(input_analyzer.next());
        }

        return new UserInput(command, args);
    }

    // Вывести помощь
    public void ShowOptions() {
        for (Command cmd : Command.values()) {
            if (!cmd.IsPrintable()) continue;
            System.out.println(cmd.GetCompleteDescription());
        }
    }
}
