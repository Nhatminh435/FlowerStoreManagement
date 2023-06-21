package tool;

import business.FlowerSet;
import business.OrderSet;
import java.util.Scanner;
import model.Flower;

/**
 * This class provides a method to scan and parse string using conditions in {@linkplain Validation}
 * @author N.Minh
 */
public class Adder {
    public static Scanner sc = new Scanner(System.in);
    
    /**About this method:
     * <ul>
     * <li>A text scanner which takes {@linkplain Scanner} as basic and can parse new flower ID whether
     * it does not meet conditions and return flower ID.
     * <li>If flower ID does not meet conditions, this method
     * will force user re-enter until it is right.
     * <li>To see the conditions, click this 
     * {@linkplain Validation#checkFlowerID(java.lang.String, business.FlowerSet)}
     * </ul>
     * @param flowerSet
     * @return flower ID
     */
    public static String addFlowerID(FlowerSet flowerSet){
        String flowerID = sc.nextLine().trim();      
        if (!Validation.checkFlowerID(flowerID, flowerSet)) {
            do {                
                System.out.print("➜ Please enter again: ");
                flowerID = sc.nextLine().trim();
            } while (!Validation.checkFlowerID(flowerID, flowerSet));
        }
        return flowerID;
    }
    
    /**
     * About this method:
     * <ul>
     * <li>A text scanner which takes {@linkplain Scanner} as basic and can parse new order detail ID whether
     * it does not meets conditions and return order detail ID.
     * <li>If order detail ID does not meet conditions, this method
     * will force user re-enter until it is right.
     * <li>To see the conditions, click this 
     * {@linkplain Validation#checkOrderDetailID(java.lang.String, business.OrderSet)}
     * </ul>
     * @param orderSet
     * @return order detail ID.
     */
    public static String addOrderDetailID(OrderSet orderSet){
        String orderDetailID = sc.nextLine().trim();
        if (!Validation.checkOrderDetailID(orderDetailID, orderSet)) {
            do {                
                System.out.print("➜ Please enter again: ");
                orderDetailID = sc.nextLine().trim();
            } while (!Validation.checkOrderDetailID(orderDetailID, orderSet));
        }
        return orderDetailID;
    }
    
    /**
     * About this method:
     * <ul>
     * <li>A text scanner which takes {@linkplain Scanner} as basic and can parse new order header ID whether
     * it does not meets conditions and return order header ID.
     * <li>If order header ID does not meet conditions, this method
     * will force user re-enter until it is right.
     * <li>To see the conditions, click this 
     * {@linkplain Validation#checkOrderHeaderID(java.lang.String, business.OrderSet)}
     * </ul>
     * @param orderSet
     * @return order header ID.
     */
    public static String addOrderHeaderID(OrderSet orderSet){
        String orderDetailID = sc.nextLine().trim();
        if (!Validation.checkOrderHeaderID(orderDetailID, orderSet)) {
            do {                
                System.out.print("➜ Please enter again: ");
                orderDetailID = sc.nextLine().trim();
            } while (!Validation.checkOrderHeaderID(orderDetailID, orderSet));
        }
        return orderDetailID;
    }
    
    /**
     * About this method:
     * <ul>
     * <li>A text scanner which can parse string whether
     * it meets conditions and return string.
     * <li>If string does not meet conditions, this method
     * will force user re-enter until it is right.
     * <li>To see the conditions, click this 
     * {@linkplain Validation#checkValidStringWithBlank(java.lang.String) }
     * </ul>
     * @return string
     */
    public static String addStringWithBlank(){
        String str = sc.nextLine().trim();

        if (!Validation.checkValidStringWithBlank(str)) {
            do {                    
                System.out.print("➜ Please enter again: ");
                str = sc.nextLine().trim();
            } while (!Validation.checkValidStringWithBlank(str));
        }
        return str;
    }
    
    /**
     * About this method:
     * <ul>
     * <li>A text scanner which takes {@linkplain Scanner} as basic and can parse integer number whether
     * it meets conditions and return integer number.
     * <li>If integer number does not meet conditions, this method
     * will force user re-enter until it is right.
     * <li>To see the conditions, click this 
     * {@linkplain Validation#checkPositiveIntegerNumber(java.lang.String)}
     * </ul>
     * @return integer number
     */
    public static int addIntegerNumber(){
        String number = sc.nextLine().trim();
        if (!Validation.checkPositiveIntegerNumber(number)) {
                do {                    
                    System.out.print("➜ Please enter again: ");
                    number = sc.nextLine().trim();
                } while (!Validation.checkPositiveIntegerNumber(number));
            }
        return Integer.parseInt(number);
    }
    
     /**
     * About this method:
     * <ul>
     * <li>A text scanner which takes {@linkplain Scanner} as basic and can parse date (string) whether
     * it meets conditions and return date.
     * <li>If date does not meet conditions, this method
     * will force user re-enter until it is right.
     * <li>To see the conditions, click this 
     * {@linkplain Validation#checkDateFormat(java.lang.String)}
     * </ul>
     * @return date (string)
     */
    public static String addDate(){
        String date = sc.nextLine().trim();
        if (!Validation.checkDateFormat(date)) {
                do {                    
                    System.out.print("➜ Please enter again: ");
                    date = sc.nextLine();
                } while (!Validation.checkDateFormat(date));
            }
        return date;
    }
    
     /**
     * About this method:
     * <ul>
     * <li>A text scanner which takes {@linkplain Scanner} as basic and can parse double number whether
     * it meets conditions and return double number.
     * <li>If double number does not meet conditions, this method
     * will force user re-enter until it is right.
     * <li>To see the conditions, click this 
     * {@linkplain Validation#checkValidPositiveDoubleNumber(java.lang.String)}
     * </ul>
     * @return double number
     */
    //Return the entered Price of Flower
    public static double addPrice() {
        String priceStr = sc.nextLine().trim();          
            if (!Validation.checkValidPositiveDoubleNumber(priceStr)) {
                do {
                    System.out.print("➜ Please try again: ");
                    priceStr = sc.nextLine().trim();
                } while (!Validation.checkValidPositiveDoubleNumber(priceStr));
            }
        return Double.parseDouble(priceStr);
    }
    
    /**
     * About this method:
     * <ul>
     * <li>A text scanner which takes {@linkplain Scanner} as basic and can parse Flower whether
     * it meets conditions and exists in floerSet and return Flower.
     * <li>If Flower does not meet conditions, this method
     * will force user re-enter until it is right.
     * <li>To see the conditions, click this 
     * {@linkplain Validation#checkValidStringWithBlank(java.lang.String)}
     * </ul>
     * @param flowerSet
     * @return 
     * @see Adder#addStringWithBlank() 
     * @see FlowerSet#isExistID(java.lang.String, business.FlowerSet) 
     */
    public static Flower addFlowerIfExist(FlowerSet flowerSet){
        System.out.print("➜ Flower ID: ");
        String flowerID = addStringWithBlank();
        if (!flowerSet.isExistID(flowerID, flowerSet)) {
            do {                
                System.out.println("Flower does not exist.");
                System.out.print("➜ Please enter again: ");
                flowerID = sc.nextLine().trim();
            } while (!flowerSet.isExistID(flowerID, flowerSet));
        }
        Flower findFlower = new Flower();
        for (Flower flower : flowerSet) {
            if (flower.getId().equals(flowerID)) {
                findFlower = flower;
            }
        }
        return findFlower;
    }
}
