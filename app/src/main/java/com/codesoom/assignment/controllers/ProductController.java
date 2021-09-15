package com.codesoom.assignment.controllers;

import com.codesoom.assignment.application.AuthenticationService;
import com.codesoom.assignment.application.ProductService;
import com.codesoom.assignment.domain.Product;
import com.codesoom.assignment.dto.ProductData;
import com.codesoom.assignment.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final AuthenticationService authenticationService;
    private final ProductService productService;

    public ProductController(AuthenticationService authenticationService, ProductService productService) {
        this.authenticationService = authenticationService;
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {

        return productService.getProducts();

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestHeader("Authorization") String authorization, @RequestBody @Valid ProductData source) {

        System.out.println("authorization = " + authorization);

        String accessToken = authorization.substring("bearer ".length());

        Long userId = authenticationService.parseToken(accessToken);

        return productService.createProduct(source);

    }

    @GetMapping("{id}")
    public Product getProduct(@PathVariable Long id) {

        return productService.getProduct(id);

    }

    @RequestMapping(value = "{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public Product updateProduct(@PathVariable Long id, @RequestBody ProductData source) {

        return productService.updateProduct(id, source);

    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);

    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void handleMissingRequestHeaderException() {

    }
}

