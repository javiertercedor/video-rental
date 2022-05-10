package com.video.rental.njcode.customer.infraestructure.rest;

import com.video.rental.njcode.customer.application.CustomerService;
import com.video.rental.njcode.customer.domain.Customer;
import com.video.rental.njcode.customer.infraestructure.rest.mapper.CustomerToCustomerResponse;
import com.video.rental.njcode.customer.infraestructure.rest.model.CustomerPostRequest;
import com.video.rental.njcode.customer.infraestructure.rest.model.CustomerPutRequest;
import com.video.rental.njcode.customer.infraestructure.rest.model.CustomerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
@Slf4j
@SecurityRequirement(name = "njcode")
public class CustomerController {

    private final CustomerService service;

    @Operation(summary = "Create Customer Object")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success Request",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "Content not authorized",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<CustomerResponse> create(@Valid @RequestBody CustomerPostRequest request) {
        log.info("Customer API Create method");
        return ResponseEntity.ok().body(CustomerToCustomerResponse.getCustomerResponseByCustomer(service.save(Customer.createCustomer(request.getName(),
                request.getSurname(),
                request.getAge()))));
    }

    @Operation(summary = "Update Customer Object")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success Request",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "Content not authorized",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> modify(@Valid @RequestBody CustomerPutRequest request, @PathVariable Long id) {
        log.info("Customer API Modify method");
        return ResponseEntity.ok().body(CustomerToCustomerResponse.getCustomerResponseByCustomer(service.modify(id, Customer.createCustomer(id,
                request.getName(),
                request.getSurname(),
                request.getAge(),
                request.getBonusPoints()))));
    }

    @Operation(summary = "Obtain Customer Object")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success Request",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "Content not authorized",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> get(@PathVariable Long id) {
        log.info("Customer API Get method");
        final Customer customer = service.get(id);

        return ResponseEntity.ok().body(CustomerToCustomerResponse.getCustomerResponseByCustomer(customer));
    }

    @Operation(summary = "Delete Customer Object")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Success Request",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Page not found",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "Content not authorized",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("Customer API Delete method");
        service.delete(id);
    }
}
