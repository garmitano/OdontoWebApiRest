package es.api.odontoweb.Customers.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Customer {
    @NonNull
    private Long dni;
    @NonNull
    private String fullName;
    private String address;
    private String city;
    @NonNull
    private String phoneNumber;
    private String email;
    @JsonIgnore
    private String socialSecurity;
}
