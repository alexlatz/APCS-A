import java.util.ArrayList;

public class Customer {
    private static final String[] inventory = new String[]{"Apple", "Banana", "Carrot", "Broccoli", "Cereal", "Pasta", "Bread", "Egg", "Cheese", "Milk"};
    private static final double[] inventoryPrices = new double[]{0.5, 1, 0.25, 0.5, 2.5, 5, 2.5, 3, 1.5, 6};
    private static int customerCount = 0;
    private final ArrayList<String> cart;
    private double price;

    public Customer() {
        cart = new ArrayList<>();
        price = 0;
        customerCount++;
    }

    public static int getCustomerCount() {
        return customerCount;
    }

    public void addItem(String item) {
        int index = findIndex(item);
        if (index < 0) System.out.println("This item is not in the store inventory!");
        else {
            cart.add(item);
            price += inventoryPrices[index];
            System.out.println(item + " added to the cart.");
        }
    }

    public void removeItem(String item) {
        int index = findIndex(item);
        if (index < 0) System.out.println("This item is not in the store inventory!");
        else if (cart.contains(item)) {
            cart.remove(item);
            price -= inventoryPrices[index];
            System.out.println(item + " removed from the cart.");
        } else System.out.println(item + " is not in the cart!");
    }

    public double checkOut() {
        customerCount--;
        System.out.printf("Thank you for shopping! Your total is $%.2f.", price);
        return price;
    }

    private int findIndex(String item) {
        int index = -1;
        for (int i = 0; i < inventory.length; i++) {
            if (item.equals(inventory[i])) {
                index = i;
                break;
            }
        }
        return index;
    }
}
