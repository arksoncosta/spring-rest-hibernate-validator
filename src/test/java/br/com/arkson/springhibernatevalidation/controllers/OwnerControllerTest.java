package br.com.arkson.springhibernatevalidation.controllers;

import br.com.arkson.springhibernatevalidation.commands.OwnerCommand;
import br.com.arkson.springhibernatevalidation.controllers.handler.CustomResponseEntityExceptionHandler;
import br.com.arkson.springhibernatevalidation.services.OwnerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author arkson
 * @date 01/05/2021
 */
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    MockMvc mockMvc;

    ObjectMapper objectMapper;

    static final int BAD_REQUEST_ERROR_CODE = 400;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(ownerController)
                .setControllerAdvice(new CustomResponseEntityExceptionHandler())
                .build();

        objectMapper = new ObjectMapper();
    }

    @Test
    void saveOwner() throws Exception {
        var ownerCommand =
                OwnerCommand.newOwnerCommand("Joe M. Long", "My address, JDO, CE, Brazil", "email@email.com", "433.086.800-84");
        var ownerCommandJson = objectMapper.writeValueAsString(ownerCommand);

        mockMvc.perform(post("/owners")
                .content(ownerCommandJson)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT_LANGUAGE, "en-US"))
                .andExpect(status().isOk());
    }

    @Test
    void saveOwnerValidationErrors() throws Exception {
        var ownerCommand = OwnerCommand.newOwnerCommand("Ma", "", "12312312312", "email");
        var ownerCommandJson = objectMapper.writeValueAsString(ownerCommand);

        mockMvc.perform(post("/owners")
                .content(ownerCommandJson)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT_LANGUAGE, "en-US"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", CoreMatchers.is(BAD_REQUEST_ERROR_CODE)))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors", hasItem("name: size must be between 3 and 255")))
                .andExpect(jsonPath("$.errors", hasItem("address: size must be between 3 and 255")))
                .andExpect(jsonPath("$.errors", hasItem("email: must be a well-formed email address")))
                .andExpect(jsonPath("$.errors", hasItem("cpf: invalid Brazilian individual taxpayer registry number (CPF)")));
    }

    @Test
    void saveOwnerValidationErrorsI18nPtBR() throws Exception {
        var ownerCommand = OwnerCommand.newOwnerCommand("Ma", "", "12312312312", "email");
        var ownerCommandJson = objectMapper.writeValueAsString(ownerCommand);

        mockMvc.perform(post("/owners")
                .content(ownerCommandJson)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT_LANGUAGE, "pt-BR"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", CoreMatchers.is(BAD_REQUEST_ERROR_CODE)))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors", hasItem("name: tamanho deve ser entre 3 e 255")))
                .andExpect(jsonPath("$.errors", hasItem("address: tamanho deve ser entre 3 e 255")))
                .andExpect(jsonPath("$.errors", hasItem("email: deve ser um endereço de e-mail bem formado")))
                .andExpect(jsonPath("$.errors", hasItem("cpf: número do registro de contribuinte individual brasileiro (CPF) inválido")));
    }

    @Test
    void saveOwnerValidationErrorsI18nES() throws Exception {
        var ownerCommand = OwnerCommand.newOwnerCommand("Ma", "", "12312312312", "email");
        var ownerCommandJson = objectMapper.writeValueAsString(ownerCommand);

        mockMvc.perform(post("/owners")
                .content(ownerCommandJson)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT_LANGUAGE, "es-ES"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", CoreMatchers.is(BAD_REQUEST_ERROR_CODE)))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors", hasItem("name: el tamaño debe estar entre 3 y 255")))
                .andExpect(jsonPath("$.errors", hasItem("address: el tamaño debe estar entre 3 y 255")))
                .andExpect(jsonPath("$.errors", hasItem("email: debe ser una dirección de correo electrónico con formato correcto")))
                .andExpect(jsonPath("$.errors", hasItem("cpf: número de registro de contribuyente individual de Brasil no válido (CPF)")));
    }
}