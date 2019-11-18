package com.accredilink.bgv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
    
    //Validations needs to added to all the required fields (phone Number, SSN, email id etc)
    //SSN field should be masked while typing on registration screen.
    
    //We need to come up with strong encryption mechanism to store the SSN field in the database.
    //Need to handle security while passing SSN field from UI to backend while registration form submit.
    //Rename Organization name as Agency.
    //Mastan Hcl Watsup: SSN needs be secured by encrypting in service layers
}
