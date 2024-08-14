package com.nony.service;

import com.nony.model.*;
import com.nony.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ResponseEntity<Product> createProduct(ProductRequest productRequest) {
        return null;

    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> productPurchaseRequest) {
        return null;
    }

    public ProductResponse getProductById(Long id) {
        return null;
    }

    public List<ProductResponse> getAllProducts() {
    }
}
