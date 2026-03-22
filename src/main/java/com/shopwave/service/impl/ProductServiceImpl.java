package com.shopwave.service.impl;

import com.shopwave.dto.CreateProductRequest;
import com.shopwave.dto.ProductDTO;
import com.shopwave.exception.ProductNotFoundException;
import com.shopwave.mapper.ProductMapper;
import com.shopwave.model.Category;
import com.shopwave.model.Product;
import com.shopwave.repository.ProductRepository;
import com.shopwave.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductDTO createProduct(CreateProductRequest request) {

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .category(Category.builder().id(request.getCategoryId()).build())
                .build();

        Product saved = productRepository.save(product);
        return ProductMapper.toDTO(saved);
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public Page<ProductDTO> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(ProductMapper::toDTO);
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return ProductMapper.toDTO(product);
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ProductDTO> searchProducts(String keyword, BigDecimal maxPrice) {

        List<Product> products = productRepository
                .findByNameContainingIgnoreCase(keyword);

        return products.stream()
                .filter(p -> p.getPrice().compareTo(maxPrice) <= 0)
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateStock(Long id, int delta) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        int newStock = product.getStock() + delta;

        if (newStock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }

        product.setStock(newStock);
        Product updated = productRepository.save(product);

        return ProductMapper.toDTO(updated);
    }
}