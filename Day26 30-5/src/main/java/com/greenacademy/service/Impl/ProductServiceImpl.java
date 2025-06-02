package com.greenacademy.service.Impl;

import com.greenacademy.entity.SanPham;
import com.greenacademy.model.ProductRequest;
import com.greenacademy.model.ProductResponse;
import com.greenacademy.repository.ProductRepository;
import com.greenacademy.service.ProductService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<ProductResponse> findAll() {
        LOGGER.info("[START] ProductServiceImpl findAll");
        try {
            List<ProductResponse> responses = productRepository.findAll().stream()
                    .map(x -> modelMapper.map(x, ProductResponse.class))
                    .collect(Collectors.toList());
            LOGGER.info("[END] ProductServiceImpl findAll success {}", responses);
            return responses;
        } catch (Exception e) {
            LOGGER.error("[END] ProductServiceImpl findAll error {}", e);
            return null;
        }
    }

    @Override
    public ProductResponse findById(String id) {
        LOGGER.info("[START] ProductServiceImpl findById",id);
        try {
            return productRepository.findById(id)
                    .map(prd -> modelMapper.map(prd, ProductResponse.class))
                    .orElseGet(() -> {LOGGER.warn("[NOT FOUND] BookServiceImpl findId with id: {}", id);
                        return null;
                    });
        }catch (Exception e) {
            LOGGER.error("[END] ProductServiceImpl findById error {}", e);
        }
        return null;
    }

    @Override
    public ProductResponse save(ProductRequest product) {
        LOGGER.info("[START] ProductServiceImpl save",product);
        try {
            SanPham prd = modelMapper.map(product, SanPham.class);
            SanPham savePrd = productRepository.save(prd);
            return modelMapper.map(savePrd, ProductResponse.class);
        }catch (Exception e) {
            LOGGER.error("[END] ProductServiceImpl save error {}", e);
        }
        return null;
    }

    @Override
    public Boolean delete(String id) {
        SanPham prd = productRepository.findById(id).orElse(null);
        if(prd != null) {
            productRepository.delete(prd);
            return true;
        }
        return false;
    }

}
