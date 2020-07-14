package br.com.nms.urlshortener.controller.payload;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import java.io.Serializable;

@Getter
@Setter
public class LoginForm implements Serializable {

    @NotBlank
    @Size(min=3, max = 60)
    private String username;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

}