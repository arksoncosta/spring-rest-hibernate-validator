package br.com.arkson.springhibernatevalidation.controllers;

import br.com.arkson.springhibernatevalidation.commands.OwnerCommand;
import br.com.arkson.springhibernatevalidation.model.Owner;
import br.com.arkson.springhibernatevalidation.services.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * @author arkson
 * @date 30/04/2021
 */
@RestController
@RequestMapping("owners")
@AllArgsConstructor
@Validated
public class OwnerController {

    private final OwnerService ownerService;

    @PostMapping
    public OwnerCommand saveOwner(@Valid @RequestBody OwnerCommand ownerCommand) {
        return ownerService.save(ownerCommand);
    }

    @GetMapping("{id}")
    public Owner findById(@PathVariable @Min(1) Long id) {
        return ownerService.findById(id);
    }

    @GetMapping("by-name/{name}")
    public Owner findByName(@PathVariable @NotBlank String name) {
        return ownerService.findByName(name);
    }
}
