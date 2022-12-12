package models;

import lombok.Builder;
import lombok.ToString;
import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
@ToString
@Builder

public class Contact {
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private String addres;
    private String description;


}
