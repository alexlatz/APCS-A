import java.util.ArrayList;

public class Customer {
    private static boolean[] inventory = new boolean[10];
    private static double[] inventoryPrices = new double[10];
    private static int customerCount = 0;
    private ArrayList<Integer> cart;
    private double price;

    public Customer() {
        cart = new ArrayList<>();
        price = 0;
        customerCount++;
    }

    public void addItems(int item) {
        if (item <= 10 && item >= 0 && inventory[item]) {
            cart.add(item);
            price += inventoryPrices[item];
        }
    }

    public void removeItems(int item) {
        if (!cart.contains(item)) System.out.println("This item is not in the cart!");
        else {
            cart.remove(item);
            price -= inventoryPrices[item];
        }
    }

    public double checkOut() {
        customerCount--;
        System.out.printf("Thank you for shopping! Your total is $%.2f.",price);
        return price;
    }

    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.checkOut();
    }
}
