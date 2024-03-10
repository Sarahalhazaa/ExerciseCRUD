package com.example.exercisecrud.Controller;
import com.example.exercisecrud.Api.ApiResponse;
import com.example.exercisecrud.Model.Customers;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/api/v1/Customers")
public class CustomersController {


   ArrayList<Customers> customers = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Customers> getCustomers() {
        return customers;
    }

    @PostMapping("/add")
    public ApiResponse addCustomers(@RequestBody Customers customer) {
        customers.add(customer);
        return new ApiResponse("Customers added");
    }

    @PutMapping("/update/{index}")
    public  ApiResponse updatedCustomers(@PathVariable int index, @RequestBody Customers customer) {
       customers.set(index, customer);
        return new  ApiResponse(" Customers updated ");
    }


    @DeleteMapping("/delete/{index}")
    public  ApiResponse deletedCustomers(@PathVariable int index) {
        customers.remove(index);
        return  new  ApiResponse("Customers Deleted");
    }
    @GetMapping("/getdeposit/{id}/{amount}")
    public ApiResponse deposit(@PathVariable String id,@PathVariable int amount) {
        for (Customers customer : customers){
     if (customer.getID().equals(id)){
         int b = customer.getBalance()+amount;
         customer.setBalance(b);

         return new  ApiResponse("The money has been deposited");
     }

     }
        return new  ApiResponse("No money has been deposited");
    }

    @PutMapping("/withdraw/{id}/{amount}")
    public  ApiResponse withdraw(@PathVariable String id,@PathVariable int amount) {
        for (Customers customer : customers){
        if (customer.getID().equals(id)){
            if (amount<= customer.getBalance()){
                int b = customer.getBalance()-amount;
                customer.setBalance(b);
                return new  ApiResponse("The money has been withdrawn");

            }
        }
    }
        return new  ApiResponse("The money has not been withdrawn");
}
    }
