package br.com.arkson.springhibernatevalidation.commands;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author arkson
 * @date 30/04/2021
 */
@Data
public class OwnerCommand {

    private Long id;

    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    @Size(min = 3, max = 255)
    private String address;

    @Email
    private String email;

    @CPF
    private String cpf;

    public static OwnerCommand newOwnerCommand(String name, String address, String email, String cpf) {
        var ownerCommand = new OwnerCommand();
        ownerCommand.setName(name);
        ownerCommand.setAddress(address);
        ownerCommand.setEmail(email);
        ownerCommand.setCpf(cpf);
        return ownerCommand;
    }
}
