import java.util.Scanner;

class CoffeeMachine {
    public static void main(String[] agrs) {
        CoffeeMachineMaker.launchCoffeeMachine();
    }
}

class CoffeeMachineMaker {
    // Expresso quantities
    private static final int QUANTITY_OF_EXPRESSO_WATER = 250;
    private static final int QUANTITY_OF_EXPRESSO_MILK = 0;
    private static final int QUANTITY_OF_EXPRESSO_COFFEE_BEANS = 16;
    private static final int EXPRESSO_COST = 4; // in dollars($)
    // Latte quantities
    private static final int QUANTITY_OF_LATTE_WATER = 350;
    private static final int QUANTITY_OF_LATTE_MILK = 75;
    private static final int QUANTITY_OF_LATTE_COFFEE_BEANS = 20;
    private static final int LATTE_COST = 7; // in dollars($)
    // Cappucino quantities
    private static final int QUANTITY_OF_CAPPUCCINO_WATER = 200;
    private static final int QUANTITY_OF_CAPPUCCINO_MILK = 100;
    private static final int QUANTITY_OF_CAPPUCCINO_COFFEE_BEANS = 12;
    private static final int CAPPUCCINO_COST = 6; // in dollars($)
    private static int currentMoneyInMachine = 550; // in dollars($)
    private static int currentWaterInMachine = 400; // in ml
    private static int currentMilkInMachine = 540; // in ml
    private static int currentCoffeeBeansInMachine = 120; // in g
    private static int currentDisposableInMachine = 9; // in g

    public static boolean useMachine = true;

    public static void launchCoffeeMachine() {
        Scanner scanner = new Scanner(System.in);

        while (useMachine) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String action = scanner.nextLine();

            switch (action.toLowerCase()) {
                case "buy":
                    buyCoffee();
                    break;
                case "fill":
                    fillCoffeeMachine();
                    break;
                case "take":
                    takeMoneyFromCoffeeMachine();
                    break;
                case "remaining":
                    displayCoffeeMachineCurrentContent();
                    break;
                case "exit":
                    useMachine = false;
                    break;
                default:
                    System.out.println("Bad selection");
            }
        }
    }


    public static void displayCoffeeMachineCurrentContent() {
        System.out.println("The coffee machine has:");
        System.out.printf("%s ml of water%n", currentWaterInMachine);
        System.out.printf("%s ml of milk%n", currentMilkInMachine);
        System.out.printf("%s g of coffee beans%n", currentCoffeeBeansInMachine);
        System.out.printf("%s disposable cups%n", currentDisposableInMachine);
        System.out.printf("$%s of money%n", currentMoneyInMachine);
    }


    public static void fillCoffeeMachine() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Write how many ml of water you want to add:");
        int water = scanner.nextInt();

        System.out.println("Write how many ml of milk you want to add:");
        int milk = scanner.nextInt();

        System.out.println("Write how many grams of coffee beans you want to add:");
        int coffeeBeans = scanner.nextInt();

        System.out.println("Write how many disposable cups you want to add: ");
        int disposableCups = scanner.nextInt();
        System.out.println();

        updateCoffeeMachineContentByAddingContent(water, milk, coffeeBeans, disposableCups);
    }

    public static void updateCoffeeMachineContentByAddingContent(int water, int milk, int coffeeBeans, int disposableCups) {
        currentWaterInMachine = currentWaterInMachine + water;
        currentMilkInMachine = currentMilkInMachine + milk;
        currentCoffeeBeansInMachine = currentCoffeeBeansInMachine + coffeeBeans;
        currentDisposableInMachine = currentDisposableInMachine + disposableCups;
    }


    public static void takeMoneyFromCoffeeMachine() {
        System.out.printf("I gave you $%s", currentMoneyInMachine);
        currentMoneyInMachine = 0;
    }

    public static void buyCoffee() {

        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu ");

        Scanner scanner = new Scanner(System.in);
        String typeOfCoffee = scanner.nextLine();

        switch (typeOfCoffee) {
            case "1":
                makeCoffee(QUANTITY_OF_EXPRESSO_WATER, QUANTITY_OF_EXPRESSO_MILK, QUANTITY_OF_EXPRESSO_COFFEE_BEANS, 1);
                break;
            case "2":
                makeCoffee(QUANTITY_OF_LATTE_WATER, QUANTITY_OF_LATTE_MILK, QUANTITY_OF_LATTE_COFFEE_BEANS, 2);
                break;
            case "3":
                makeCoffee(QUANTITY_OF_CAPPUCCINO_WATER, QUANTITY_OF_CAPPUCCINO_MILK, QUANTITY_OF_CAPPUCCINO_COFFEE_BEANS, 3);
                break;
            case "back":
                launchCoffeeMachine();
                break;
            default:
                buyCoffee();
        }

    }

    public static void makeCoffee(int quantityOfWater, int quantityOfMilk, int quantityOfCoffeeBeans, int coffeeType) {

        if (currentWaterInMachine < quantityOfWater || currentMilkInMachine < quantityOfMilk || currentCoffeeBeansInMachine < quantityOfCoffeeBeans) {
            checkQuantity(quantityOfWater, quantityOfMilk, quantityOfCoffeeBeans);
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            currentWaterInMachine = currentWaterInMachine - quantityOfWater;
            currentMilkInMachine = currentMilkInMachine - quantityOfMilk;
            currentCoffeeBeansInMachine = currentCoffeeBeansInMachine - quantityOfCoffeeBeans;
            currentDisposableInMachine = currentDisposableInMachine - 1;
            updateCurrentMoneyInMachine(coffeeType);
        }

    }

    public static void checkQuantity(int quantityOfWater, int quantityOfMilk, int quantityOfCoffeeBeans) {
        if (currentWaterInMachine < quantityOfWater) {
            System.out.println("Sorry, not enough water!");
        } else if (currentMilkInMachine < quantityOfMilk) {
            System.out.println("Sorry, not enough milk!");
        } else if (currentCoffeeBeansInMachine < quantityOfCoffeeBeans) {
            System.out.println("Sorry, not enough coffee beans!");
        } else {
        }

    }

    public static void updateCurrentMoneyInMachine(int coffeeType) {
        switch (coffeeType) {
            case 1:
                currentMoneyInMachine = currentMoneyInMachine + EXPRESSO_COST;
                break;
            case 2:
                currentMoneyInMachine = currentMoneyInMachine + LATTE_COST;
                break;
            case 3:
                currentMoneyInMachine = currentMoneyInMachine + CAPPUCCINO_COST;
                break;
            default:
                System.out.println("We cannot update money");
        }
    }
}