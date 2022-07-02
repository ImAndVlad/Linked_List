package app;

public class Main {

    public static void main(String[] args) {
        Product product1 = new Product();
        product1.name = "One";
        product1.price = 13;

        Product product2 = new Product();
        product2.name = "Two";
        product2.price = 2.99;

        Linked_List list = new Linked_List();
        list.addFirst(product2);
        list.add(1, product1);
        System.out.println(list);
    }
}
