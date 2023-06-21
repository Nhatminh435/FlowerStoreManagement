package model;

import java.io.Serializable;

/**
 * This is a class representing an Order Header.
 * It contains properties such as orderID, orderDate, orderCustomer.
 * It implements the Serializable interface to enable object serialization.
 * The class provides methods to get and set these properties.
 */
public class OrderHeader implements Serializable{
    private String orderID;
    private String orderDate;
    private String orderCustomer;

    /**
     * Default constructor of OrderHeader.
     */
    public OrderHeader() {
    }

    /**
     * Constructor of OrderHeader.
     * @param orderID
     * @param orderDate
     * @param orderCustomer 
     */
    public OrderHeader(String orderID, String orderDate, String orderCustomer) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.orderCustomer = orderCustomer;
    }
    
    /**
     * Set OrderHeader ID of OrderHeader
     * @param idOrder 
     */
    public void setOrderHeaderID(String idOrder) {
        this.orderID = idOrder;
    }
    
    /**
     * Set order date of OrderHeader
     * @param orderDate 
     */
    public void setDateOrder(String orderDate) {
        if ((orderDate.matches("[0-3][0-9]/[0-1][0-9]/[0-9]{4}") && !orderDate.matches("00/00/0000"))) {
            this.orderDate = orderDate;
        }else{
            this.orderDate = "Null";
        }
    }
    
    /**
     * Set customer's name of OrderHeader
     * @param orderCustomer 
     */
    public void setCustomerName(String orderCustomer) {
        this.orderCustomer = orderCustomer;
    }
    
    /**
     * Get OrderHeader ID of OrderHeader.
     * @return 
     */
    public String getOrderHeaderID() {
        return orderID;
    }
    
    /**
     * Get order date of OrderHeader
     * @return 
     */
    public String getDateOrder() {
        return orderDate;
    }
    
    /**
     * Get customer's name of OrderHeader
     * @return 
     */
    public String getCustomerName() {
        return orderCustomer;
    }

    @Override
    public String toString() {
        return getOrderHeaderID()+ ", " + getDateOrder() + ", " + getCustomerName();
    }
    
    
}
