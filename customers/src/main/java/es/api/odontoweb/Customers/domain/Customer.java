package es.api.odontoweb.Customers.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "Datos personales de un paciente")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Customer {
    @Schema(description = "Identificador único de paciente.", example = "20179408")
    @NonNull
    private Long dni;
    @Schema(description = "Nombre y apellido de un paciente.", example = "Alberto Gismano")
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
