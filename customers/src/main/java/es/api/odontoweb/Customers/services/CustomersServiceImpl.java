package es.api.odontoweb.Customers.services;

import es.api.odontoweb.Customers.domain.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomersServiceImpl {
    ArrayList<Customer> customers = new ArrayList<>(
            List.of(new Customer(25173444L, "Gustavo", "3834454555"),
                    new Customer(23828733L, "Cecilia", "3834565656"),
                    new Customer(43750988L, "Benjamin", "3834787878"),
                    new Customer(46595395L, "Josefina", "3834232323"))
    );

    public List<Customer> listAllCustomers(){
       return this.customers;
    }
}
