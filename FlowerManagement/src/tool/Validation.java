package tool;

import business.FlowerSet;
import business.OrderSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class provides a method to check if a string matches certain conditions.
 * @author N.Minh
 */
public class Validation {
    /**
     * Return true if string has special character(s).
     *
     * @param str
     * @return true if string has special character(s).
     */
    public static boolean checkSpecialCharacter(String str) {
        String regex = "[!@#$%^&*(),'?\":{}|<>]";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        return matcher.find();
    }
    
    /**
     * Return true if flower ID meet the conditions
     * in {@linkplain Validation#checkValidStringWithoutBlank(java.lang.String)}
     * and does not exist in flowerSet.
     * @param flowerID
     * @param flowerSet
     * @return true if flower ID meet the conditions and vice versa.
     */
    public static boolean checkFlowerID(String flowerID, FlowerSet flowerSet){
        boolean check1 = false;
        boolean check2 = false;
        if (Validation.checkValidStringWithoutBlank(flowerID)) {
            check1 = true;
        }
        if (!flowerSet.isExistID(flowerID, flowerSet)) {
            check2 = true;
        }else{
            System.out.println("Flower ID exist.");
        }
        return (check1 && check2);
    }

    /**
     * Return true if Order Detail ID meet the conditions
     * in {@linkplain Validation#checkValidStringWithoutBlank(java.lang.String)}
     * and does not exist in orderSet.
     * @param orderDetailID
     * @param orderSet
     * @return true if flower ID meet the conditions and vice versa.
     */
    public static boolean checkOrderDetailID(String orderDetailID, OrderSet orderSet){
        boolean check1 = false;
        boolean check2 = false;
        if (Validation.checkValidStringWithoutBlank(orderDetailID)) {
            check1 = true;
        }
        if (!orderSet.isOrderDisOrderDetailIDExistInOrderSet(orderDetailID, orderSet)) {
            check2 = true;
        }else{
            System.out.println("Order detail ID exists.");
        }
        return (check1 && check2);
    }
    
    /**
     * Return true if Order Header ID meet the conditions
     * in {@linkplain Validation#checkValidStringWithoutBlank(java.lang.String)}
     * and does not exist in orderSet.
     * @param orderHeaderID
     * @param orderSet
     * @return true if flower ID meet the conditions and vice versa.
     */
    public static boolean checkOrderHeaderID(String orderHeaderID, OrderSet orderSet){
        boolean check1 = false;
        boolean check2 = false;
        if (Validation.checkValidStringWithoutBlank(orderHeaderID)) {
            check1 = true;
        }
        if (!orderSet.isOrderHeaderIDExist(orderHeaderID, orderSet)) {
            check2 = true;
        }else{
            System.out.println("Order header ID exists.");
        }
        return (check1 && check2);
    }
    /**
     * Return true if imported string is not empty, does not have special
     * character(s).
     *
     * @param str
     * @return true if imported string is not empty, does not have special
     * character(s).
     */
    public static boolean checkValidStringWithBlank(String str) {

        boolean check1 = false;
        boolean check2 = false;
        if (!str.isEmpty()) {
            check1 = true;
        } else {
            System.out.println("Cannot be left blank.");
        }

        if (!checkSpecialCharacter(str)) {
            check2 = true;
        } else {
            System.out.println("Cannot contain special character.");
        }
        return check1 && check2;
    }

    /**
     * Return true if imported string is not empty and is
     * a positive integer number, does not have special character(s) or blank inside.
     *
     * @param str
     * @return true if imported string is not empty is an integer number, does not have special character(s) or blank inside.
     */
    public static boolean checkPositiveIntegerNumber(String str) {
        boolean check1 = false;
        boolean check2 = false;
        boolean check3 = false;
        boolean check5 = false;
        boolean check4 = true;

        if (!str.isEmpty()) {
            check1 = true;
        } else {
            System.out.println("Cannot be left blank.");
        }

        if (!checkSpecialCharacter(str) && !str.contains("/") && !str.contains(".")) {
            check2 = true;
        } else {
            System.out.println("Number cannot have special character.");
        }

        if (!str.contains(" ")) {
            check3 = true;
        } else {
            System.out.println("Number must not contain blank.");
        }

        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i))) {
                System.out.println("Number cannot contain letter.");
                check4 = false;
                break;
            }
        }
        
        if (!str.contains("-")) {
            check5 = true;
        }else{
            System.out.println("Must be positive.");
        }
        return (check1 && check2 && check3 && check4 && check5);
    }

    /**
     * Return true if imported string is not empty and is
     * a positive  double number, does not have special character(s) or blank inside.
     *
     * @param str
     * @return true if imported string is not empty and is an double number, does not have special character(s) or blank inside.
     * character(s).
     */
    public static boolean checkValidPositiveDoubleNumber(String str) {
            boolean check1 = false;
            boolean check2 = false;
            boolean check3 = false;
            boolean check4 = true;
            boolean check5 = false;

            if (!str.isEmpty()) {
                check1 = true;
            } else {
                System.out.println("Cannot be left blank.");
            }
            if (!checkSpecialCharacter(str) && !str.contains("/")) {
                check2 = true;
            } else {
                System.out.println("Number cannot have special characters.");
            }
            if (!str.contains(" ")) {
                check3 = true;
            } else {
                System.out.println("Cannot contain spaces.");
            }
            for (int i = 0; i < str.length(); i++) {
                if (Character.isLetter(str.charAt(i))) {
                    System.out.println("Number cannot contain letters.");
                    check4 = false;
                    break;
                }
            }
            if (!str.contains("-")) {
                check5 = true;
            }else{
                System.out.println("Must be positive.");
            }
            return (check1 && check2 && check3 && check4 && check5);
        }

    /**
     * Return true if imported string is not empty, does not have special
     * character(s) and blank inside.
     *
     * @param str
     * @return true if imported string is not empty, does not have special
     * character(s) and blank inside.
     */
    public static boolean checkValidStringWithoutBlank(String str) {
        boolean check1 = false;
        boolean check2 = false;

        if (checkValidStringWithBlank(str)) {
            check1 = true;
        }
        if (!str.contains(" ")) {
            check2 = true;
        } else {
            System.out.println("Cannot contain blank.");
        }

        return check1 && check2;
    }

    /**
     * Return true if the imported phone number 
     * meets the {@linkplain Validation#checkValidStringWithoutBlank(java.lang.String)}
     * conditions in  and starts with "0".
     * @param PhoneNumber
     * @return true if the imported phone number meets the conditions in ... and starts with "0".
     */
    public static boolean checkValidPhoneNumber(String PhoneNumber) {
        boolean check1 = false;
        boolean check2 = false;

        if (checkValidStringWithoutBlank(PhoneNumber)) {
            check1 = true;
        }
        if (PhoneNumber.startsWith("0")) {
            check2 = true;
        } else {
            System.out.println("Invalid phone number.");
        }

        return check1 && check2;
    }

    /**
     * Return true if the imported date is valid with
     * formate [dd/MM/yyyy] and accuracy of date field.
     * @param date
     * @return 
     */
    public static boolean checkDateFormat(String date) {
        String dateFormatPattern = "dd/MM/yyyy";
        SimpleDateFormat dateFormate = new SimpleDateFormat(dateFormatPattern);
        dateFormate.setLenient(false);                          //prevent accepting invalid dateformat.
        
        try {
            if(date.matches("[0-9]{2}/[0-1][0-9]/[1-9][0-9]{3}")){
                dateFormate.parse(date);
                return true;
            }else{
                System.out.println("Wrong date format.");
                return false;
            }
            
        } catch (ParseException e) {
            System.out.println("Wrong date format.");
            return false;
        }
    }
}
