package es.api.odontoweb.Customers.controllers;

import es.api.odontoweb.Customers.domain.Customer;
import es.api.odontoweb.Customers.services.CustomersServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customers")
@Tag(name = "API customers",
        description = "CRUD de pacientes")
public class CustomerRestController {
    CustomersServiceImpl customersService = new CustomersServiceImpl();
    ArrayList<Customer> customers = new ArrayList<>(
            List.of(new Customer(25173444L, "Gustavo", "3834454555"),
                    new Customer(23828733L, "Cecilia", "3834565656"),
                    new Customer(43750988L, "Benjamin", "3834787878"),
                    new Customer(46595395L, "Josefina", "3834232323"))
    );
    @ApiResponse(responseCode = "200", description = "Operación exitosa")
    @ApiResponse(responseCode = "400", description = "Error de petición")
    @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    @Operation(summary = "Recupera un paciente por dni",
               description = "Recupera un paciente mediante el dni de tipo entero")
    @GetMapping("/{dni}")
    public ResponseEntity<?> getCustomerByDni(
            @Parameter(description="DNI de persona. Valor entero de 8 digitos", required=true,example = "25173444")
            @PathVariable Long dni){
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

    @Operation(summary = "Recupera todos los pacientes",
               description = "Lista todos los pacientes")
    @GetMapping
    public ResponseEntity<?> listCustomers(){
        List<Customer> customers = customersService.listAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @Operation(summary = "Agrega un nuevo paciente",
            description = "Agrega un nuevo paciente")
    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer){
        this.customers.add(customer);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{dni}")
                .buildAndExpand(customer.getFullName())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Actualiza un paciente",
            description = "Actualiza un paciente")
    @PutMapping
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

    @Operation(summary = "Elimina un paciente por dni",
               description = "Elimina un paciente mediante el dni de tipo entero")
    @DeleteMapping("/{dni}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long dni){
        for(Customer customer : this.customers)
            if (customer.getDni().equals(dni)){
                this.customers.remove(customer);
                return ResponseEntity.noContent().build();
            }
        return ResponseEntity.notFound().build();
    }
}
