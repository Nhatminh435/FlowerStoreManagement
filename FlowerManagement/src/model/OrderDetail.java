package model;

import java.io.Serializable;

/**
 * This is a class representing an Order Detail.
 * It contains properties such as orderDetailID, flower, quantity.
 * It implements the Serializable interface to enable object serialization.
 * The class provides methods to get and set these properties.
 */
public class OrderDetail implements Serializable{
    private String orderDetailID;
    private Flower flower;
    private int quantity;

    /**
     * Default constructor of OrderDetail.
     */
    public OrderDetail() {
    }

    /**
     * Constructor of OrderDetail.
     * @param orderDetailID
     * @param flower
     * @param quantity 
     */
    public OrderDetail(String orderDetailID, Flower flower, int quantity) {
        this.orderDetailID = orderDetailID;
        this.flower = flower;
        this.quantity = quantity;
    }

    /**
     * Set the OrderDetail id of OrderDetail
     * @param orderDetailID 
     */
    public void setOrderDetailID(String orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    /**
     * Set the flower of OrderDetail
     * @param flower 
     */
    public void setFlower(Flower flower) {
        this.flower = flower;
    }

    /**
     * Set the quantity of flower of OrderDetail
     * @param quantity 
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Return the OrderDetail ID of OrderDetail
     * @return the OrderDetail ID of OrderDetail
     */
    public String getOrderDetailID() {
        return orderDetailID;
    }

    /**
     * Return the flower of OrderDetail
     * @return  the Flower of OrderDetail
     */
    public Flower getFlower() {
        return flower;
    }
    
    /**
     * Return the quantity of flower of OrderDetail
     * @return the quantity of flower of OrderDetail
     */
    public int getQuantity() {
        return quantity;
    }
    
    /**
     * Return the total cost of flowers in an order detail.
     * @return 
     */
    public double getFlowerCost(){
        return flower.getUnitPrice() * getQuantity() * 1.0;
    }

    @Override
    public String toString() {
        return getOrderDetailID()+ ", " + flower.getId() + ", " + getQuantity() + ", " + getFlowerCost();
    }
    
}
