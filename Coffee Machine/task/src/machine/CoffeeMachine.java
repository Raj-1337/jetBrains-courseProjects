package machine;

import java.util.Scanner;

class CoffeeMachineInstance {
    int availableWater = 400;
    int availableMilk = 540;
    int availableCoffee = 120;
    int availableCups = 9;
    int availableMoney = 550;
    enum State {
        waiting,
        buy_menu,
        buy_variant,
        start_fill,
        fill_water,
        fill_milk,
        fill_coffee,
        fill_cups,
        take,
        remaining,
        stop;
    }
    State state = State.waiting;

    public boolean runMachine(String input_string) {
        boolean flag = true;

        if (input_string.equals("buy")) {
            state = State.buy_menu;
        } else if (input_string.equals("fill")) {
            state = State.start_fill;
        } else if (input_string.equals("take")) {
            state = State.take;
        } else if (input_string.equals("remaining")) {
            state = State.remaining;
        } else if (input_string.equals("exit")) {
            state = State.stop;
        }

        switch (state) {
            case waiting:
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                break;
            case remaining:
                displayStatus();
                state = State.waiting;
                System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
                break;
            case buy_menu:
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                state = State.buy_variant;
                break;
            case buy_variant:
                buyCoffee(input_string);
                state = State.waiting;
                System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
                break;
            case start_fill:
                System.out.println("\nWrite how many ml of water do you want to add:");
                state = State.fill_water;
                break;
            case fill_water:
                setAvailableWater(input_string);
                state = State.fill_milk;
                System.out.println("Write how many ml of milk do you want to add:");
                break;
            case fill_milk:
                setAvailableMilk(input_string);
                state = State.fill_coffee;
                System.out.println("Write how many grams of coffee beans do you want to add:");
                break;
            case fill_coffee:
                setAvailableCoffee(input_string);
                state = State.fill_cups;
                System.out.println("Write how many disposable cups of coffee do you want to add:");
                break;
            case fill_cups:
                setAvailableCups(input_string);
                state = State.waiting;
                System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
                break;
            case take:
                takeMoney();
                state = State.waiting;
                System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
                break;
            default:
                state = State.stop;
                flag = false;
        }
        return flag;
    }

    public  void displayStatus() {
        System.out.println("\nThe coffee machine has:");
        System.out.println(availableWater + " of water");
        System.out.println(availableMilk + " of milk");
        System.out.println(availableCoffee + " of coffee beans");
        System.out.println(availableCups + " of disposable cups");
        System.out.println(availableMoney + " of money");
    }

    public void setAvailableWater(String availableWater) {
        this.availableWater += Integer.parseInt(availableWater);
    }

    public void setAvailableMilk(String availableMilk) {
        this.availableMilk += Integer.parseInt(availableMilk);
    }

    public void setAvailableCoffee(String availableCoffee) {
        this.availableCoffee += Integer.parseInt(availableCoffee);
    }

    public void setAvailableCups(String availableCups) {
        this.availableCups += Integer.parseInt(availableCups);
    }

    public void takeMoney() {
        System.out.println("\nI gave you $" + availableMoney);
        availableMoney = 0;
    }

    public void buyCoffee(String op) {
        switch (op) {
            case "1":
                coffeeRun(250, 16, 0, 4);
                break;
            case "2":
                coffeeRun(350, 20, 75, 7);
                break;
            case "3":
                coffeeRun(200, 12, 100, 6);
                break;
            default:
                break;
        }
    }

    public void coffeeRun(int water, int coffee, int milk, int money) {
        boolean flag = false;
        if (availableWater < water) {
            System.out.println("Sorry, not enough water!");
            flag = true;
        }
        if (!flag && (availableCoffee < coffee)) {
            System.out.println("Sorry, not enough coffee!");
            flag = true;
        }
        if (!flag && availableMilk < milk) {
            System.out.println("Sorry, not enough milk!");
            flag = true;
        }
        if (!flag && (availableCups < 1)) {
            System.out.println("Sorry, not enough cups!");
            flag = true;
        }
        if (!flag) {
            System.out.println("I have enough resources, making you a coffee!");
            availableWater -= water;
            availableCoffee -= coffee;
            availableMilk -= milk;
            availableCups -= 1;
            availableMoney += money;
        }
    }

}

 public class CoffeeMachine {
     public static void main(String[] args) {
         boolean running;
         Scanner scanner = new Scanner(System.in);
         CoffeeMachineInstance coffeeMachineInstance = new CoffeeMachineInstance();
         running = coffeeMachineInstance.runMachine("");
         while (running) {
             running = coffeeMachineInstance.runMachine(scanner.next());
         }
     }
}