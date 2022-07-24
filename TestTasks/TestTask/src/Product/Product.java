package Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.naming.ldap.ManageReferralControl;
import com.mysql.cj.xdevapi.AddResult;
import User.User;

public class Product{
    private String id;
    private String name;
    private Double price;

    public Product(String id_,String name_,Double price_ ){
        name=name_;
        id=id_;
        price=price_;
    }

    public String getId(){
        return id;
    }
    public void setId(String a){
        id=a;
    }
    public String getName(){
        return name;
    }
    public void setName(String a){
        name=a;
    }
    public Double getPrice(){
        return price;
    }
    public void setPrice(Double a){
        price=a;
    }
    public String toStringProduct(){

        return this.name+" "+ this.price+" "+this.id; 
    }
}


