 package model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * This is a class representing an Order.
 * It contains properties such as {@linkplain OrderDetail} and HashMap of [String, {@linkplain OrderHeader}].
 * It implements the Serializable interface to enable object serialization.
 * The class provides methods to get and set these properties.
 */
public class Order implements Serializable{
    private OrderHeader orderHeader;
    private HashMap<String, OrderDetail> orderDetails;
    
    /**
     * Default constructor of Order class.
     */
    public Order() {
    }
    
    /**
     * Constructor of Order class.
     * @param orderHeader
     * @param orderDetails 
     */
    public Order(OrderHeader orderHeader, HashMap<String, OrderDetail> orderDetails) {
        this.orderHeader = orderHeader;
        this.orderDetails = orderDetails;
    }

    /**
     * Set the HashMap of [String, OrderHeader] of Order.
     * @param orderHeader 
     */
    public void setOrderHeader(OrderHeader orderHeader) {
        this.orderHeader = orderHeader;
    }
        
    /**
     * Set the OrderDetail of Order
     * @param orderDetails 
     */
    public void setOrderDetails(HashMap<String, OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
    /**
     * Return the OrderHeader of Order
     * @return the OrderHeader of Order
     */
    public OrderHeader getOrderHeader() {
        return orderHeader;
    }
    /**
     * Return the HashMap of [String, OrderHeader] of Order
     * @return 
     */
    public HashMap<String, OrderDetail> getOrderDetails() {
        return orderDetails;
    }
    
    /**
     * Return the total flowers in an order.
     * @return the total flowers in an order.
     */
    public int getTotalFlower(){
        int count = 0;
        for(OrderDetail orderDetail : orderDetails.values()){
            count += orderDetail.getQuantity();
        }        
        return count;
    }
    
    /**
     * Return the total cost of flowers in an order.
     * @return the total cost of flowers in an order.
     */
    public double getTotalFlowerPrice(){
        double totalPrice = 0.0;
        for(OrderDetail subOrderDetail : orderDetails.values()){
            totalPrice += subOrderDetail.getFlowerCost();
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        return orderHeader.toString() + ", " + getTotalFlowerPrice();
    }
    
}
