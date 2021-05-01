package br.com.arkson.springhibernatevalidation.services;

import br.com.arkson.springhibernatevalidation.commands.OwnerCommand;
import br.com.arkson.springhibernatevalidation.model.Owner;

/**
 * @author Tribus
 * @author arkson
 * @date 30/04/2021
 */
public interface OwnerService {

    OwnerCommand save(OwnerCommand ownerCommand);
    Owner findById(Long id);
    Owner findByName(String name);

}
