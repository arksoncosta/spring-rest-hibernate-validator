package br.com.arkson.springhibernatevalidation.converters;

import br.com.arkson.springhibernatevalidation.commands.OwnerCommand;
import br.com.arkson.springhibernatevalidation.model.Owner;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author arkson
 * @date 30/04/2021
 */
@Component
public class OwnerCommandToOwner implements Converter<OwnerCommand, Owner> {

    @Override
    public Owner convert(OwnerCommand ownerCommand) {
        if (ownerCommand == null)
            return null;

        final var owner = new Owner();
        owner.setId(ownerCommand.getId());
        owner.setName(ownerCommand.getName());
        owner.setAddress(ownerCommand.getAddress());
        owner.setEmail(ownerCommand.getEmail());
        owner.setCpf(ownerCommand.getCpf());

        return owner;
    }
}
