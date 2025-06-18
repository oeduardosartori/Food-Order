package com.sartori.food_order.service.impl;

import com.sartori.food_order.config.MessageResolver;
import com.sartori.food_order.dto.product.ProductInputDTO;
import com.sartori.food_order.dto.product.ProductOutputDTO;
import com.sartori.food_order.entity.Product;
import com.sartori.food_order.exception.BusinessException;
import com.sartori.food_order.helper.ErrorMessage;
import com.sartori.food_order.mapper.ProductMapper;
import com.sartori.food_order.repository.ProductRepository;
import com.sartori.food_order.service.ProductService;
import com.sartori.food_order.validator.product.ProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final MessageResolver messageResolver;
    private final ProductValidator productValidator;

    @Override
    public ProductOutputDTO createProduct(ProductInputDTO productInputDTO) {
        productValidator.validateProductCreate(productInputDTO);

        Product product = productMapper.toEntity(productInputDTO);
        product.setName(productInputDTO.getName().trim());

        Product savedProduct = productRepository.save(product);
        return productMapper.toOutputDTO(savedProduct);
    }

    @Override
    public ProductOutputDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                      messageResolver.getMessage(ErrorMessage.PRODUCT_NOT_FOUND_BY_ID.code())
                ));

        return productMapper.toOutputDTO(product);
    }

    @Override
    public Page<ProductOutputDTO> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::toOutputDTO);
    }

    @Override
    public ProductOutputDTO updateProduct(Long id, ProductInputDTO productInputDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new BusinessException(
                        messageResolver.getMessage(ErrorMessage.PRODUCT_NOT_FOUND_BY_ID.code())
                ));
        productValidator.validateProductUpdate(productInputDTO, id);

        existingProduct.setName(productInputDTO.getName().trim());
        existingProduct.setDescription(productInputDTO.getDescription());
        existingProduct.setPrice(productInputDTO.getPrice());

        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.toOutputDTO(updatedProduct);
    }

    @Override
    public void delete(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
    }
}
