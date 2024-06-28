package com.caito.departamentsertvice.api.models.externals;

import com.caito.departamentsertvice.utils.enums.GenderName;
import com.caito.departamentsertvice.utils.enums.PositionName;
import com.caito.departamentsertvice.utils.enums.SeniorityName;
import com.caito.departamentsertvice.utils.enums.StatusName;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor@AllArgsConstructor
@Getter@Setter
@Builder
public class Employee implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;
    private GenderName gender;
    private String address;
    private String phoneNumber;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate hiredDate;
    private PositionName position;
    private SeniorityName seniority;
    private StatusName status;
}
