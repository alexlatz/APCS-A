public class Main {

    public static void main(String[] args) {
        Customer customer = new Customer();
        System.out.println(Customer.getCustomerCount());
        customer.addItem("Orange");
        customer.addItem("Carrot");
        customer.addItem("Broccoli");
        customer.removeItem("Orange");
        customer.removeItem("Broccoli");
        customer.removeItem("Broccoli");
        customer.checkOut();
    }
}
