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
public class OwnerToOwnerCommand implements Converter<Owner, OwnerCommand> {

    @Override
    public OwnerCommand convert(Owner owner) {
        if (owner == null) {
            return null;
        }

        final var ownerCommand = new OwnerCommand();
        ownerCommand.setId(owner.getId());
        ownerCommand.setName(owner.getName());
        ownerCommand.setAddress(owner.getAddress());
        ownerCommand.setEmail(owner.getEmail());
        ownerCommand.setCpf(owner.getCpf());

        return ownerCommand;
    }
}
