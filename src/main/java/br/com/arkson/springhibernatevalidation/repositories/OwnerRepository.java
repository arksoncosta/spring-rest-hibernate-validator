package br.com.arkson.springhibernatevalidation.repositories;

import br.com.arkson.springhibernatevalidation.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author Tribus
 * @author arkson
 * @date 30/04/2021
 */
public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Optional<Owner> findByName(String name);

}
