package br.com.arkson.springhibernatevalidation.services;

import br.com.arkson.springhibernatevalidation.commands.OwnerCommand;
import br.com.arkson.springhibernatevalidation.converters.OwnerCommandToOwner;
import br.com.arkson.springhibernatevalidation.converters.OwnerToOwnerCommand;
import br.com.arkson.springhibernatevalidation.exceptions.NotFoundException;
import br.com.arkson.springhibernatevalidation.model.Owner;
import br.com.arkson.springhibernatevalidation.repositories.OwnerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author arkson
 * @date 30/04/2021
 */
@Service
@AllArgsConstructor
@Slf4j
public class OwnerServiceImpl implements OwnerService {

    private final OwnerCommandToOwner ownerCommandToOwner;
    private final OwnerToOwnerCommand ownerToOwnerCommand;
    private final OwnerRepository ownerRepository;

    @Override
    public OwnerCommand save(OwnerCommand ownerCommand) {
        var detachedOwner = ownerCommandToOwner.convert(ownerCommand);

        var savedOwner = ownerRepository.save(detachedOwner);
        log.info("Saved Owner: " + savedOwner.getId());
        return ownerToOwnerCommand.convert(savedOwner);
    }

    @Override
    public Owner findById(Long id) {
        return ownerRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Owner not found. For ID " + id.toString()));
    }

    @Override
    public Owner findByName(String name) {
        return ownerRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Owner not found. For Name " + name));
    }
}
