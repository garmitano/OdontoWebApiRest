package es.api.odontoweb.Customers.controllers;

import es.api.odontoweb.Customers.domain.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerRestController {
    ArrayList<Customer> customers = new ArrayList<>(
            List.of(new Customer(25173444L, "Gustavo", "3834454555"),
                    new Customer(23828733L, "Cecilia", "3834565656"),
                    new Customer(43750988L, "Benjamin", "3834787878"),
                    new Customer(46595395L, "Josefina", "3834232323"))
    );

    @GetMapping("/customers/{dni}")
    public ResponseEntity<?> getCustomerByDni(@PathVariable Long dni){
        //usado para evitar hacer la busqueda de datos fuera de rango
        if(dni<0){
            ResponseEntity.badRequest().build();
        }

        for(Customer customer : this.customers){
            if(customer.getDni().equals(dni)){
                return ResponseEntity.ok(customer);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/customers")
    public ResponseEntity<?> listCustomers(){
        return ResponseEntity.ok(this.customers);
    }

    @PostMapping("/customers")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer){
        this.customers.add(customer);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{dni}")
                .buildAndExpand(customer.getFullName())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/customers")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer){

        for(Customer customerTmp: this.customers){
            if(customerTmp.getDni().equals(customer.getDni())){
                customerTmp.setFullName(customer.getFullName());
                customerTmp.setPhoneNumber(customer.getPhoneNumber());
                return ResponseEntity.ok(customer);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/customers/{dni}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long dni){
        for(Customer customer : this.customers)
            if (customer.getDni().equals(dni)){
                this.customers.remove(customer);
                return ResponseEntity.noContent().build();
            }
        return ResponseEntity.notFound().build();
    }
}
