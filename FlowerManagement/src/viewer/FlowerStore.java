package viewer;

import business.FlowerSet;
import business.OrderSet;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.HashMap;
import model.Flower;
import model.Order;
import model.OrderDetail;
import model.OrderHeader;
import tool.Adder;

public class FlowerStore {

    public static void main(String[] args) throws ParseException, FileNotFoundException {
        String pathFlower = System.getProperty("user.dir") + "/src/output/flowers.dat";
        String pathOrder = System.getProperty("user.dir") + "/src/output/orders.dat";
        
        FlowerSet flowerSet = new FlowerSet();
        OrderSet orderSet = new OrderSet();
        
//        Flower flower1 = new Flower("A11", "Lactus", "12/05/2022", 12.5, "Wild plant");
//        Flower flower2 = new Flower("A12", "Blossom", "12/05/2022", 12.5, "Wild plant");
//        Flower flower3 = new Flower("A13", "Rose", "12/06/2023", 9.99, "Red");
//        Flower flower4 = new Flower("A14", "Tulip", "15/06/2023", 7.5, "Yellow");
//        Flower flower5 = new Flower("A15", "Lily", "20/06/2023", 12.99, "White");
//        flowerSet.add(flower1);
//        flowerSet.add(flower2);
//        flowerSet.add(flower3);
//        flowerSet.add(flower4);
//        flowerSet.add(flower5);
//        
//        OrderHeader orderHeader1 = new OrderHeader("0006", "10/01/2022", "John Smith");
//        OrderDetail orderDetail1 =  new OrderDetail("DT0006", flower1, 14);
//        OrderDetail orderDetail2 =  new OrderDetail("DT0007", flower2, 10);
//        OrderDetail orderDetail3 =  new OrderDetail("DT0008", flower3, 9);
//        HashMap<String, OrderDetail> orderDetailMap1 = new HashMap<String, OrderDetail>();
//        orderDetailMap1.put(orderDetail1.getOrderDetailID(), orderDetail1);
//        orderDetailMap1.put(orderDetail2.getOrderDetailID(), orderDetail2);
//        orderDetailMap1.put(orderDetail3.getOrderDetailID(), orderDetail3);
//        Order order1 = new Order(orderHeader1, orderDetailMap1);
//        
//        OrderHeader orderHeader2 = new OrderHeader("0007", "10/05/2023", "Lee Smith");
//        OrderDetail orderDetail4 =  new OrderDetail("DT0009", flower1, 1);
//        OrderDetail orderDetail5 =  new OrderDetail("DT0010", flower2, 1);
//        OrderDetail orderDetail6 =  new OrderDetail("DT0011", flower1, 1);
//        OrderDetail orderDetail10 =  new OrderDetail("DT0012", flower4, 1);
//        HashMap<String, OrderDetail> orderDetailMap2 = new HashMap<String, OrderDetail>();
//        orderDetailMap2.put(orderDetail4.getOrderDetailID(), orderDetail4);
//        orderDetailMap2.put(orderDetail5.getOrderDetailID(), orderDetail5);
//        orderDetailMap2.put(orderDetail6.getOrderDetailID(), orderDetail6);
//        orderDetailMap2.put(orderDetail10.getOrderDetailID(), orderDetail10);
//        Order order2 = new Order(orderHeader2, orderDetailMap2);
//        
//        OrderHeader orderHeader3 = new OrderHeader("0008", "10/06/2022", "John Hanah");
//        OrderDetail orderDetail7 =  new OrderDetail("DT0012", flower1, 5);
//        OrderDetail orderDetail8 =  new OrderDetail("DT0013", flower2, 7);
//        OrderDetail orderDetail9 =  new OrderDetail("DT0014", flower3, 4);
//        HashMap<String, OrderDetail> orderDetailMap3 = new HashMap<String, OrderDetail>();
//        orderDetailMap3.put(orderDetail7.getOrderDetailID(), orderDetail7);
//        orderDetailMap3.put(orderDetail8.getOrderDetailID(), orderDetail8);
//        orderDetailMap3.put(orderDetail9.getOrderDetailID(), orderDetail9);
//        Order order3 = new Order(orderHeader3, orderDetailMap3);
//        orderSet.add(order1);
//        orderSet.add(order2);
//        orderSet.add(order3);
        
        try {
            int choiceMain = 0;
            do {                
                System.out.println("");
                choiceMain = Menu.getChoice();
                System.out.println("");
                switch(choiceMain){
                    case 1:
                        String addFlowerChoice = "y";
                        do {                            
                            if (flowerSet.addFlower(flowerSet)) {
                                System.out.println("");
                                System.out.println("Add successfully.");
                            }else{
                                System.out.println("");
                                System.out.println("Add failed.");
                            }
                            System.out.println("\nEnter y to continue to add a flower.");
                            System.out.println("Enter any to exit.");
                            System.out.print("➜ Your choice: ");
                            addFlowerChoice = Adder.addStringWithBlank();
                        } while (addFlowerChoice.equalsIgnoreCase("y"));
                        
                        break;
                    case 2:
                        flowerSet.findFlower(flowerSet);
                        break;
                    case 3:
                        if (flowerSet.updateFlower(flowerSet)) {
                            System.out.println("");
                            System.out.println("Update successfull.");
                        }else{
                            System.out.println("");
                            System.out.println("Update failed.");
                        }
                        break;
                    case 4:
                        if (flowerSet.deleteFlower(flowerSet, orderSet)) {
                            System.out.println("");
                            System.out.println("Delete successfully.");
                        } else {
                            System.out.println("");
                            System.out.println("Delete failed.");
                        }
                        break;
                    case 5:
                        String addOrderChoice = "y";
                        do {                            
                            if (orderSet.addOrder(flowerSet, orderSet)) {
                                System.out.println("");
                                System.out.println("Add successfully.");
                            } else {
                                System.out.println("");
                                System.out.println("Add failed.");
                            }
                            System.out.println("\nEnter y to continue to add an order.");
                            System.out.println("Enter any to exit.");
                            System.out.print("➜ Your choice: ");
                            addOrderChoice = Adder.addStringWithBlank();
                        } while (addOrderChoice.equalsIgnoreCase("y"));
                        
                        break;
                    case 6:
                        orderSet.displayOrderWithDate(orderSet);
                        break;
                    case 7:
                        orderSet.sortOrder(orderSet);
                        break;
                    case 8:
                        if (flowerSet.saveFlowerToFile(pathFlower, flowerSet) && orderSet.saveOrderToFile(pathOrder, orderSet)) {
                            System.out.println("Save successfully.");
                        }
                        break;
                    case 9:
                        flowerSet.addAll(flowerSet.getFlowerFromFile(pathFlower, flowerSet));
                        orderSet.addAll(orderSet.getOrderFromFile(pathOrder));
                        System.out.println("");
                        break;
                    case 10:
                        System.out.println("➜ Do you still want to exist? (y/n)");
                        System.out.print("Your choice: ");
                        String existProgram = Adder.addStringWithBlank();
                        if (!existProgram.equalsIgnoreCase("y") && !existProgram.equalsIgnoreCase("n")) {
                            do {                                
                                System.out.println("Unvalid request! ");
                                System.out.println("➜ Please enter again: ");
                                existProgram = Adder.addStringWithBlank();
                            } while (!existProgram.equalsIgnoreCase("y") && !existProgram.equalsIgnoreCase("n"));
                        }
                        if (existProgram.equalsIgnoreCase("y")) {
                            choiceMain = 11;
                            if (flowerSet.saveFlowerToFile(pathFlower, flowerSet) && orderSet.saveOrderToFile(pathOrder, orderSet)) {
                                System.out.println("Save successfully.");
                            }
                        }
                        if (existProgram.equalsIgnoreCase("n")) {
                            choiceMain = 10;
                        }
                        break;
                }
            } while ((choiceMain >= 1) && (choiceMain <= 10));
            System.out.println("=====================================END PROGRAM=====================================================");
            System.out.print("\n");
            System.out.println("Thank you for using. Goodbye!");
        } catch (FileNotFoundException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
