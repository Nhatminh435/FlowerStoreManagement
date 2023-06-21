package business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import model.Order;
import model.Flower;
import model.OrderDetail;
import model.OrderHeader;
import tool.Adder;

public class OrderSet extends HashSet<Order> implements Serializable{
    
    /**
     * Return true if Order header ID already exist in OrderSet, and vice versa.
     * @param orderHeaderID
     * @param orderSet
     * @return Return true if Order header ID already exist in OrderSet, and vice versa.
     */
    public boolean isOrderHeaderIDExist(String orderHeaderID, OrderSet orderSet){
        int count = 0;
        for (Order order : orderSet) {
            if (order.getOrderHeader().getOrderHeaderID().equalsIgnoreCase(orderHeaderID)) {
                count++;
            }
        }
        return (count > 0);
    }
    
    /**
     * Return true if Order detail ID already exist in OrderSet, and vice versa.
     * @param orderDetailID
     * @param orderSet
     * @return Return true if Order detail ID already exist in OrderSet, and vice versa.
     */
    public boolean isOrderDisOrderDetailIDExistInOrderSet(String orderDetailID, OrderSet orderSet){
        int count = 0;
        for (Order order : orderSet) {
            if (order.getOrderDetails().containsKey(orderDetailID)) {
                count++;
            }
        }
        return (count > 0);
    }
    
    /**
     * Create and return new order detail with conditions (see more in {@linkplain Adder})
     * @param flowerSet
     * @param orderSet
     * @return return new order detail
     */
    public OrderDetail createOrderDetail(FlowerSet flowerSet, OrderSet orderSet){
        System.out.print("➜ ID: ");
        String orderDetailID = Adder.addOrderDetailID(orderSet);
        Flower flower = Adder.addFlowerIfExist(flowerSet);
        System.out.print("➜ Flower's quantity: ");
        int orderQuantity = Adder.addIntegerNumber();
        OrderDetail orderDetail = new OrderDetail(orderDetailID, flower, orderQuantity);
        return orderDetail;
    }
    
    /**
     * Create and return new HashMap which has key representing
     * for order detail id, value representing for order detail.
     * @param flowerSet
     * @param orderSet
     * @return return new HashMap which has key representing for order detail id, value representing for order detail.
     * @see OrderSet#createOrderDetail(business.FlowerSet, business.OrderSet) 
     */
    public HashMap<String, OrderDetail> createOrderDetailMap(FlowerSet flowerSet, OrderSet orderSet){
        //Ask to over the 
        int i = 0;
        HashMap<String, OrderDetail> orderDetailMap = new HashMap<String, OrderDetail>();
        String choice = "y";
        if (choice.equalsIgnoreCase("y")) {
            do {            
            System.out.println("Order detail " + (++i) + ": ");
            OrderDetail orderDetail = createOrderDetail(flowerSet, orderSet);
            orderDetailMap.put(orderDetail.getOrderDetailID(), orderDetail);
            System.out.println(" ----------------- ");
            System.out.println("| Continue to add |");
            System.out.println(" ----------------- ");
            System.out.println("| y: Yes          |");
            System.out.println("| Other: Exist    |");
            System.out.println(" ----------------- ");
                System.out.print("➜ Your choice: ");
            choice = Adder.addStringWithBlank();
            } while (choice.equalsIgnoreCase("y"));
        }
        return orderDetailMap;
    }
    
    /**
     * Return true if adding new order to orderSet 
     * successfully with conditions 
     * (see more in {@linkplain Adder})
     * @param flowerSet
     * @param orderSet
     * @return Return true if adding new order to orderSet successfully
     */
    public boolean addOrder (FlowerSet flowerSet, OrderSet orderSet){
        System.out.print("➜ Order header id: ");
        String orderHeaderID = Adder.addOrderHeaderID(orderSet);
        System.out.print("➜ Order date: ");
        String orderDate = Adder.addDate();
        System.out.print("➜ Customer's name: ");
        String orderCustomer = Adder.addStringWithBlank();
        OrderHeader orderHeader = new OrderHeader(orderHeaderID, orderDate, orderCustomer);
        HashMap<String, OrderDetail> orderDetailMap = new HashMap<String, OrderDetail>();
        orderDetailMap = createOrderDetailMap(flowerSet, orderSet);
        Order order = new Order(orderHeader, orderDetailMap);
        return (orderSet.add(order));
    }
    
    /**
     * Display list of orders, which has order date between
     * inputting startDate and endDate, with order ID, order
     * date, customer, number of flower and order total.
     * @param orderSet
     * @throws ParseException 
     */
    public void displayOrderWithDate(OrderSet orderSet) throws ParseException{
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            System.out.print("➜ Start date: ");
            String dateStartStr = Adder.addDate();
            Date dateStart = simpleDateFormat.parse(dateStartStr);
            System.out.print("➜ End date: ");
            String dateEndStr = Adder.addDate();
            Date dateEnd = simpleDateFormat.parse(dateEndStr);
            int i = 0;
            int countTotalOrderFlower = 0;
            double totalOrder = 0;
            System.out.println("LIST ORDERS FROM " + dateStartStr + " TO " + dateEndStr);
            System.out.println("");
            System.out.println(" -------------------------------------------------------------------------------------");
            System.out.printf("| %3s | %-10s | %10s | %-20s | %12s |  %12s |\n", "No.", "Order Id", "Order Date", "Customer", "Flower Count", "Order Total");
            System.out.println(" -------------------------------------------------------------------------------------");
            for (Order order : orderSet) {
                Date orderdate = simpleDateFormat.parse(order.getOrderHeader().getDateOrder());
                if (orderdate.equals(dateStart) || orderdate.equals(dateEnd) || orderdate.after(dateStart) || orderdate.before(dateEnd)) {
                    System.out.printf("| %3d | %-10s | %10s | %-20s | %12d | $%-12.0f |\n", ++i, order.getOrderHeader().getOrderHeaderID(), order.getOrderHeader().getDateOrder(), order.getOrderHeader().getCustomerName(), order.getTotalFlower(), order.getTotalFlowerPrice());
                    System.out.println(" -------------------------------------------------------------------------------------");
                    countTotalOrderFlower += order.getTotalFlower();
                    totalOrder += order.getTotalFlowerPrice();
                }
            }
            System.out.printf("| %3s | %-10s | %10s | %-20s | %12d | $%12.0f |\n", "", "Total", "", "", countTotalOrderFlower, totalOrder);
            System.out.println(" -------------------------------------------------------------------------------------");
        } catch (ParseException e) {
            System.out.println("Error occurs: " + e.getMessage());
        }
    }
    
    /**
     * Sort and display the list of orders:
     * <ul>
     * <li>Sort by order id/ order date/ customer's name/ order total. 
     * <li>Sort order by ASC (ascending) or DESC (descending).
     * </ul>
     * @param orderSet 
     * @see OrderSet#sortOrderByDateASC(java.util.ArrayList) 
     * @see OrderSet#sortOrderByIDASC(java.util.ArrayList) 
     * @see OrderSet#sortOrderByNameASC(java.util.ArrayList) 
     * @see OrderSet#sortOrderByTotalASC(java.util.ArrayList) 
     */
    public void sortOrder(OrderSet orderSet) throws ParseException{
        System.out.println("LIST OF ORDERS:");
        System.out.print("Sorted by (order id/ order date/ customer's name/ order total): ");
        String sortedField = Adder.addStringWithBlank();
        System.out.print("Sort order (ASC/ DESC): ");
        String sortedOrder = Adder.addStringWithBlank();
        System.out.println("");
        ArrayList<Order> list = new ArrayList<Order>(orderSet);
        if ((sortedField.equalsIgnoreCase("order id") || sortedField.equalsIgnoreCase("id"))) {
            list = sortOrderByIDASC(list);
        }
        if ((sortedField.equalsIgnoreCase("order date") || sortedField.equalsIgnoreCase("date"))) {
            try {
                list = sortOrderByDateASC(list);
            } catch (ParseException e) {
                System.out.println("Error occurs: " + e.getMessage());
            }
        }
        if ((sortedField.equalsIgnoreCase("customer's name") || sortedField.equalsIgnoreCase("name"))) {
            list = sortOrderByNameASC(list);
        }
        if ((sortedField.equalsIgnoreCase("order total") || sortedField.equalsIgnoreCase("total"))) {
            list = sortOrderByTotalASC(list);
        }
        if (sortedOrder.equalsIgnoreCase("DESC")) {
            Collections.reverse(list);
        }
        if ((sortedField.equalsIgnoreCase("order id") || sortedField.equalsIgnoreCase("id") || sortedField.equalsIgnoreCase("order date") || sortedField.equalsIgnoreCase("date") ||
              sortedField.equalsIgnoreCase("order name") || sortedField.equalsIgnoreCase("name") || sortedField.equalsIgnoreCase("order total") || sortedField.equalsIgnoreCase("total")) &&
             (sortedOrder.equalsIgnoreCase("ASC") || sortedOrder.equalsIgnoreCase("DESC"))) {
            displayOrderList(list);
        }
        if (!((sortedField.equalsIgnoreCase("order id") || sortedField.equalsIgnoreCase("id") || sortedField.equalsIgnoreCase("order date") || sortedField.equalsIgnoreCase("date") ||
              sortedField.equalsIgnoreCase("order name") || sortedField.equalsIgnoreCase("name") || sortedField.equalsIgnoreCase("order total") || sortedField.equalsIgnoreCase("total")) &&
             (sortedOrder.equalsIgnoreCase("ASC") || sortedOrder.equalsIgnoreCase("DESC")))) {
            System.out.println("Unvalid request");
        }
        
    }
    
    /**
     * Sort ascending the order by order id in inputting list. 
     * @param list
     * @return 
     */
    public ArrayList<Order> sortOrderByIDASC(ArrayList<Order> list){
        Comparator<Order> comparatorID = new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return o1.getOrderHeader().getOrderHeaderID().compareTo(o2.getOrderHeader().getOrderHeaderID());
            }
        };
        Collections.sort(list, comparatorID);
        return list;
    }
    
    /**
     * Sort ascending the order by customer's name in inputting list. 
     * @param list
     * @return 
     */
    public ArrayList<Order> sortOrderByNameASC(ArrayList<Order> list){
        Comparator<Order> comparatorName = new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return o1.getOrderHeader().getCustomerName().compareTo(o2.getOrderHeader().getCustomerName());
            }
        };
        Collections.sort(list, comparatorName);
        return list;
    }
    
    /**
     * Sort ascending the order by order date in inputting list. 
     * @param list
     * @return
     * @throws ParseException 
     */
    public ArrayList<Order> sortOrderByDateASC(ArrayList<Order> list) throws ParseException{
        try {
            Comparator<Order> comparatorName = new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date o1Date = simpleDateFormat.parse(o1.getOrderHeader().getDateOrder());
                    Date o2Date = simpleDateFormat.parse(o2.getOrderHeader().getDateOrder());
                    if (o1Date.before(o2Date)) {
                        return -1;
                    }else{
                        if (o1Date.after(o2Date)) {
                        return 1;
                        }else{
                            return 0;
                        }
                    }
                } catch (Exception e) {
                    return 0;
                }
            }
        };
        Collections.sort(list, comparatorName);
        } catch (Exception e) {
            System.out.println("Error occurs: " + e.getMessage());
        }
        return list;
    }
    
    /**
     * Sort ascending the order by order total in inputting list. 
     * @param list
     * @return 
     */
    public ArrayList<Order> sortOrderByTotalASC(ArrayList<Order> list){
        Comparator<Order> comparatorName = new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                if (o1.getTotalFlowerPrice()> o2.getTotalFlowerPrice()) {
                    return 1;
                }else{
                    if (o1.getTotalFlowerPrice() == o2.getTotalFlowerPrice()) {
                        return 0;
                    }
                    else{
                        return -1;
                    }
                }
                
            }
        };
        Collections.sort(list, comparatorName);
        return list;
    }
    
    /**
     * Display the list.
     * @param list 
     */
    public void displayOrderList(ArrayList<Order> list){
        int i = 0;
        int countTotalOrderFlower = 0;
        double totalOrder = 0;
        System.out.println(" -------------------------------------------------------------------------------------");
        System.out.printf("| %3s | %-10s | %10s | %-20s | %12s | $%-2s |\n", "No.", "Order Id", "Order Date", "Customer", "Flower Count", "Order Total");
        System.out.println(" --------------------------------------------------------------------------------------");
        for (Order order : list) {
            countTotalOrderFlower += order.getTotalFlower();
            totalOrder += order.getTotalFlowerPrice();
            System.out.printf("| %3d | %-10s | %10s | %-20s | %12d | $%12.0f |\n", ++i, order.getOrderHeader().getOrderHeaderID(), order.getOrderHeader().getDateOrder(), order.getOrderHeader().getCustomerName(), order.getTotalFlower(), order.getTotalFlowerPrice());
            System.out.println(" -------------------------------------------------------------------------------------");
        }
        System.out.printf("| %3s | %-10s | %10s | %-20s | %12d | $%12.0f |\n", "", "Total", "", "", countTotalOrderFlower, totalOrder);
        System.out.println(" -------------------------------------------------------------------------------------");
    }
    
    /**
     * Return the name of file through inputting file path [pathFile].
     * @param pathFile
     * @return Return the name of file by inputting file path [pathFile].
     */
    private String getFileName(String pathFile){
        String[] subPathFile = pathFile.split("/");
        return subPathFile[subPathFile.length - 1];
    }
    
    /**
     * This method will:
     * <ul>
     * <li>Saves the collection of orders one by one to the binary file orders.dat.
     * <li>Return true if it saves successfully.
     * <li>Return false with message "Fail to save orders' information to orders.dat." if it saves failed,
     * </ul>
     * @param pathOrder
     * @param orderSet
     * @return Return true if this method saves the collection of orders to the binary file orders.dat successfully.
     * @throws ParseException
     * @throws FileNotFoundException 
     * @see OrderSet#getFileName(java.lang.String) 
     */
    public boolean saveOrderToFile(String pathOrder, OrderSet orderSet) throws ParseException, FileNotFoundException{
        try {
            FileOutputStream fileOutput = new FileOutputStream(new File(pathOrder));
            ObjectOutputStream objOutput = new ObjectOutputStream(fileOutput);
            for (Order order : orderSet) {
                objOutput.writeObject(order);
            }
            objOutput.flush();
            objOutput.close();
            fileOutput.close();
            return true;
        } catch (FileNotFoundException f) {
            System.out.println("Fail to save orders' information to " + getFileName(pathOrder) + ": " + f.getMessage());
            return false;
        } catch (IOException e) {
            System.out.println("Fail to save orders' information to " + getFileName(pathOrder) + ": " + e.getMessage());
            return false;
        }
    }
    
    
    
    /**
     * This method will:
     * <ul>
     * <li>Get orders' information from the binary file orders.dat through
     * file path [pathOrder] and add them one by one to a orderSet.
     * <li>Return orderSet has orders' information with message "Load
     * information from orders.dat successfully!" if it successes.
     * <li>Return orderSet has no orders' information with message 
     * "Fail to load orders.dat"
     * </ul>
     * @param pathOrder
     * @return Return orderSet has orders' information with message "Load
     * information from orders.dat successfully!" if it successes.
     * @see OrderSet#getFileName(java.lang.String) 
     */
    public OrderSet getOrderFromFile(String pathOrder) {
        OrderSet orderSet = new OrderSet();
        try {
            FileInputStream fileInput = new FileInputStream(new File(pathOrder));
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            while (true) {
                try {
                    Object object = objectInput.readObject();
                    if (object != null) {
                        if (object instanceof Order) {
                            Order order = (Order) object;
                            orderSet.add(order);
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    break;
                }
            }
            objectInput.close();
            fileInput.close();
            System.out.println("Load information from " + getFileName(pathOrder) + " successfully!");
        } catch (FileNotFoundException f) {
            System.out.println("Fail to load file " + getFileName(pathOrder) + ": " + f.getMessage());
        } catch (IOException e) {
            System.out.println("Fail to load file " + getFileName(pathOrder) + ": " + e.getMessage());
        }
        return orderSet;
    }
}
