package com.greenacademy.api;


import com.greenacademy.model.ProductRequest;
import com.greenacademy.model.ProductResponse;
import com.greenacademy.service.ProductService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
@CrossOrigin("*")
public class ProductApi {
    private static final Logger logger = LoggerFactory.getLogger(ProductApi.class);
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> getProduct() {
        logger.info("getProduct");
        List<ProductResponse> product = productService.findAll();
        logger.info("getProduct");
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> save(@RequestBody ProductRequest request) {
        logger.info("save");
        ProductResponse productResponse = productService.save(request);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@RequestBody ProductRequest request) {
        logger.info("update");
        ProductResponse productResponse = productService.save(request);
        System.out.println("productResponse: " + productResponse);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public boolean delete(@PathVariable String id) {
        logger.info("delete");
    return productService.delete(id);
    }
}
