package br.com.arkson.springhibernatevalidation.controllers;

import br.com.arkson.springhibernatevalidation.controllers.handler.ControllerExceptionHandler;
import br.com.arkson.springhibernatevalidation.model.Owner;
import br.com.arkson.springhibernatevalidation.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author arkson
 * @date 01/05/2021
 */
class OwnerControllerValidationParamsTest {

    MockMvc mockMvc;

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    static final Long OWNER_ID = 1L;

    Owner owner;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(ownerController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();

        owner = new Owner();
        owner.setId(OWNER_ID);
        owner.setName("Walter H. W.");
    }

    @Test
    void findById() throws Exception {
        when(ownerService.findById(ArgumentMatchers.anyLong()))
                .thenReturn(owner);

        mockMvc.perform(get("/owners/1")
                .header(HttpHeaders.ACCEPT_LANGUAGE, "en-US"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", containsString("Walter")));
    }

    @Test
    void findByIdValidationError() throws Exception {
        mockMvc.perform(get("/owners/abc")
                .header(HttpHeaders.ACCEPT_LANGUAGE, "en-US"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", containsString("For input string: \"abc\"")));
    }
}
