package com.greenacademy.service;

import com.greenacademy.entity.SanPham;
import com.greenacademy.model.ProductRequest;
import com.greenacademy.model.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> findAll();
    ProductResponse findById(String id);
    ProductResponse save(ProductRequest product);
    Boolean delete(String id);
}
