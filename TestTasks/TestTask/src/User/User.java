package User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.naming.ldap.ManageReferralControl;
import com.mysql.cj.xdevapi.AddResult;
import Product.Product;

public class User{
    private String id;
    private String firstName;
    private String lastName;
    private Double amountOfMoney;
    private ArrayList<Product> products=new ArrayList<Product>();

    
    public User(String id_,String firstName_,String lastName_ ,Double amountOfMOney_){
        firstName=firstName_;
        id=id_;
        lastName=lastName_;
        amountOfMoney=amountOfMOney_;
    }

    public String getId(){
        return id;
    }
    public void setId(String a){
        id=a;
    }
    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String a){
        firstName=a;
    }
    public String getLastName(){
        return lastName;
    }
    public void setLastName(String a){
        lastName=a;
    }
    public Double getAmountOfMoney(){
        return amountOfMoney;
    }
    public void setAmountOfMoney(Double a){
        amountOfMoney=a;
    }
    public void toStringUser(){

        System.out.println(this.firstName+" "+ this.lastName+" "+this.id+" "+this.amountOfMoney); 
    }
    public void toStringUserFull(){

        System.out.println(this.firstName+" "+ this.lastName+" "+this.id+" "+this.amountOfMoney+"\nProducts:");
        for (Product product : products) {
            System.out.println(product.toStringProduct());
        } 
        System.out.println("-----------------------------------");
    }
    public void buyNewProduct(Product a){

        products.add(a);
    }

    public void deleteProductFromList(Product a){
        for(int i =0;i<products.size();++i)
        {
            if(products.get(i).getId()==a.getId()){
                products.remove(i);
            }
        }    
        
    }

    public void hasProductBought(Product a){

        for(int i =0;i<products.size();++i){
            if(products.get(i).getId().compareTo(a.getId())==0){
                this.toStringUser();
            }
        }

    }
}