import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.naming.ldap.ManageReferralControl;
import com.mysql.cj.xdevapi.AddResult;
import User.User;
import Product.Product;
import java.util.Random;


public class MainBody{
    
    
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        Random random = new Random();
        MainBody mainBody=new MainBody();
        
        
        ArrayList<Product> allProducts = new ArrayList<Product>();
        ArrayList<User> allUsers = new ArrayList<User>();
        ArrayList<String> ID=new ArrayList<String>();
        
        allUsers.add(new User("123", "Artur", "Solodovnikov", 5000.0));
        allUsers.add(new User("212313", "Delroyka", "Semen", 9000.0));
        allProducts.add(new Product("11232132", "Youghurt", 500.0));
        allProducts.add(new Product("321", "Milk", 1000.0));
        
        for(int i =99999;i>0;--i){


        }
        while(true){

            System.out.print("Choose action\n1.Show all users\n2.Show all products\n3.Buy Product\n4.Add new user\n5.Add new product\n6.Delete User\n7.Delete Product\n->");
            int action=0;
            try {
                
                action=Integer.parseInt(in.nextLine());
                
            }catch(Exception e){

                System.out.println("Wrong Input");
                mainBody.sleep();
                System.out.print("\033[H\033[2J");
                continue;
            }
            if(action < 1 || action>7)
                {
                    System.out.print("\033[H\033[2J");
                    System.out.println("Wrong action.Try one more time");
                    mainBody.sleep();
                    System.out.print("\033[H\033[2J");
                    
                    continue;
                }
            switch(action){
                ///////////////////////////////////////////Action 1      Show All Users
                case 1:
                    System.out.print("\033[H\033[2J");
                    mainBody.showAllUsers(allUsers,true);//Outputs all users with all their products
                    mainBody.sleep();
                    System.out.print("\033[H\033[2J");
                break;
                ///////////////////////////////////////////Action 2      Show All Products
                case 2:
                System.out.print("\033[H\033[2J");
                for(int i=0;i<allProducts.size();++i){
                    mainBody.userHasProductOutput(allUsers, allProducts.get(i));//Outputs all products,and users which have bought them
                }
                    mainBody.sleep();
                    System.out.print("\033[H\033[2J");
                break;
                ///////////////////////////////////////////Action 3      Buy Product
                case 3:
                System.out.print("\033[H\033[2J");// Clear console
                mainBody.showAllUsers(allUsers,false);//Show all users from list
                System.out.print("(ID)->");//tip
                String idUser =in.nextLine();//get users input 

                if(!mainBody.idExistsUser(idUser, allUsers)){//If doesn't exist 

                    System.out.print("\033[H\033[2J");
                    System.out.println("Wrong ID");
                    mainBody.sleep();//Sleep 2 seconds.I hade a headache that's why i didn't have enough time to read some articles to understand why Thread.sleep(2000) causes problems.I decided to create function to make my code clearer
                    System.out.print("\033[H\033[2J");
                    continue;
                }
                System.out.print("\033[H\033[2J");
                mainBody.showAllProducts(allProducts);//Show all products which user can buy
                System.out.print("(ID)->");//tip
                String idProduct =in.nextLine();//get users input
                //Product idExistsProduct=mainBody.idExistsProduct(idProduct, allProducts);//check if id exists
                
                if(!mainBody.idExistsProduct(idProduct,allProducts)){//if id doesn't exist

                    System.out.print("\033[H\033[2J");
                    System.out.println("Wrong id");
                    mainBody.sleep();//Sleep 2 seconds
                    System.out.print("\033[H\033[2J");
                    continue;
                }
                User user = mainBody.getUserWithId(idUser, allUsers);
                Product product = mainBody.getProductWithId(idProduct, allProducts);
                if(user.getAmountOfMoney() < product.getPrice()){//check if user has enough money to buy product
                    System.out.print("\033[H\033[2J");
                    System.out.println("Not enough money");
                    mainBody.sleep();
                    System.out.print("\033[H\033[2J");
                    continue;
                }
                mainBody.buyProduct(user, product);//buying product
                System.out.print("\033[H\033[2J");
                System.out.print("Product was successfully bought");
                mainBody.sleep();
                System.out.print("\033[H\033[2J");
                break;
                ///////////////////////////////////////////Action 4      Add User
                case 4:
                System.out.print("\033[H\033[2J");
                String generatedUserID;
                while(true){
                    int a = random.nextInt(1000000); 
                    if(!mainBody.idExistsUser(String.valueOf(a), allUsers));
                    {    
                        generatedUserID=String.valueOf(a);
                        break;
                    }
                }
                
                System.out.print("Enter first name of user\n->");
                String firstName=in.nextLine();
                System.out.print("Enter last name of user\n->");
                String lastName=in.nextLine();
                System.out.print("Enter amount of money\n->");
                
                
                try{
                    Double amountOfMoney=Double.parseDouble(in.nextLine());
                    allUsers.add(new User(generatedUserID, firstName, lastName, amountOfMoney));
                    System.out.println("User was added");
                    mainBody.sleep();
                    System.out.print("\033[H\033[2J");
                }catch (IllegalArgumentException e) {
                    System.out.print("\033[H\033[2J");
                    System.out.println("The Amount of money you entered is InValid!"); 
                    mainBody.sleep();
                    System.out.print("\033[H\033[2J");
                    continue;

                }
                break;
                ///////////////////////////////////////////Action 5      Add Product
                case 5:
                System.out.print("\033[H\033[2J");
                String generatedProductID;
                while(true){
                    int a = random.nextInt(1000000); 
                    if(!mainBody.idExistsProduct(String.valueOf(a), allProducts));
                    {    
                        generatedProductID=String.valueOf(a);
                        break;
                    }
                }
                System.out.print("Enter name of product\n->");
                String productName=in.nextLine();
                
                try{
                    System.out.print("Enter price of product\n->");
                    Double productPrice=Double.parseDouble(in.nextLine());
                    allProducts.add(new Product(generatedProductID, productName, productPrice));
                    System.out.println("Product was added");
                    mainBody.sleep();
                    System.out.print("\033[H\033[2J");
                }catch (IllegalArgumentException e) {
                    System.out.print("\033[H\033[2J");
                    System.out.println("The Amount of money you entered is InValid!"); 
                    mainBody.sleep();
                    System.out.print("\033[H\033[2J");
                    continue;

                }
                break;
                case 6:
                System.out.print("\033[H\033[2J");// Clear console
                mainBody.showAllUsers(allUsers,false);//Show all users from list
                System.out.print("(ID)->");//tip
                String idUserToDelete =in.nextLine();//get users input 

                if(!mainBody.idExistsUser(idUserToDelete, allUsers)){//If doesn't exist 

                    System.out.print("\033[H\033[2J");
                    System.out.println("Wrong ID");
                    mainBody.sleep();//Sleep 2 seconds.I hade a headache that's why i didn't have enough time to read some articles to understand why Thread.sleep(2000) causes problems.I decided to create function to make my code clearer
                    System.out.print("\033[H\033[2J");
                    continue;
                }
                int index = mainBody.returnIDExistsUser(idUserToDelete, allUsers);

                allUsers.remove(index);
                System.out.print("\033[H\033[2J");// Clear console
                System.out.print("User was succesfully deleted");
                mainBody.sleep();
                System.out.print("\033[H\033[2J");// Clear console
                break;
                ///////////////////////////////////////////Action 7     Delete Product
                case 7:
                System.out.print("\033[H\033[2J");
                mainBody.showAllProducts(allProducts);//Show all products which user can buy
                System.out.print("(ID)->");//tip
                String idProductToDelete =in.nextLine();
                if(!mainBody.idExistsProduct(idProductToDelete,allProducts)){//if id doesn't exist

                    System.out.print("\033[H\033[2J");
                    System.out.println("Wrong id");
                    mainBody.sleep();//Sleep 2 seconds
                    System.out.print("\033[H\033[2J");
                    continue;
                }
                Product productToDelete = mainBody.getProductWithId(idProductToDelete, allProducts);
                mainBody.deleteProduct(productToDelete, allUsers);
                allProducts.remove(productToDelete);
                System.out.print("\033[H\033[2J");
                System.out.print("Product succesfully deleted");
                mainBody.sleep();
                System.out.print("\033[H\033[2J");
            }
            
        }
        
        
    }

    public void showAllUsers(ArrayList<User> a,boolean full){
        if(full==true){
        for(int i=0;i<a.size();++i)
        {
            a.get(i).toStringUserFull();
        }
        }
        else{
            for(int i=0;i<a.size();++i)
        {
            a.get(i).toStringUser();
        }
        }
    }

    public void showAllProducts(ArrayList<Product> a){

        for(int i=0;i<a.size();++i)
        {
            System.out.println(a.get(i).toStringProduct());
        }
        
        
    }

    public User getUserWithId(String id,List<User>a){

        for(int i =0;i<a.size();++i){
            String b=a.get(i).getId();
            if(id.compareTo(b)==0){
                return a.get(i);
            }
        }
        return null;

    }
    public Product getProductWithId(String id,List<Product>a){

        for(int i =0;i<a.size();++i){

            String b=a.get(i).getId();
            if(id.compareTo(b)==0){
                return a.get(i);
            }
        }
        return null;

    }

    public boolean idExistsUser(String id,List<User> a){
        for(int i =0;i<a.size();++i){
            String b=a.get(i).getId();
            if(id.compareTo(b)==0){
                return true;
            }
        }
        return false;

    }

    public int returnIDExistsUser(String id,List<User> a){
        for(int i =0;i<a.size();++i){
            String b=a.get(i).getId();
            if(id.compareTo(b)==0){
                return i;
            }
        }
        return -1;

    }
   

    public boolean idExistsProduct(String id,List<Product> a){
        for(int i =0;i<a.size();++i){
            String b=a.get(i).getId();
            if(id.compareTo(b)==0){
                return true;
            }
        }
        return false;

    }
    public void buyProduct(User user, Product product){

        user.setAmountOfMoney(user.getAmountOfMoney() - product.getPrice());
        user.buyNewProduct(product);

    }

    public void deleteProduct(Product product,List<User> a){
        
        for(int i =0;i<a.size();++i){

            a.get(i).deleteProductFromList(product);
        }

    }
    public void sleep(){

        try {
            Thread.sleep(2000);   

        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }


    public void userHasProductOutput(List<User> a,Product product){
        System.out.println(product.toStringProduct());
        
        for(int i=0;i<a.size();++i){
            a.get(i).hasProductBought(product);

        }
        System.out.println("-----------------------------------");
    }
}

