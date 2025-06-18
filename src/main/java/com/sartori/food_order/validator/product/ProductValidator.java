package com.sartori.food_order.validator;

import com.sartori.food_order.config.MessageResolver;
import com.sartori.food_order.dto.product.ProductInputDTO;
import com.sartori.food_order.entity.Product;
import com.sartori.food_order.exception.BusinessException;
import com.sartori.food_order.helper.ErrorMessage;
import com.sartori.food_order.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductValidator {

    private final ProductRepository productRepository;
    private final MessageResolver messageResolver;

    public void validateProductCreate(ProductInputDTO dto) {
        validateNameNotDuplicate(dto.getName());
        validatePrice(dto.getPrice());
    }

    public void validateProductUpdate(ProductInputDTO dto, Long currentProductId) {
        validateNameNotDuplicate(dto.getName(), currentProductId);
        validatePrice(dto.getPrice());
    }

    private void validateNameNotDuplicate(String name) {
        if (productRepository.existsByNameIgnoreCase(name.trim())) {
            throw new BusinessException(messageResolver.getMessage(ErrorMessage.PRODUCT_NAME_DUPLICATE.code()));
        }
    }

    private void validateNameNotDuplicate(String name, Long currentProductId) {
        Optional<Product> existing = productRepository.findByNameIgnoreCase(name.trim());
        if (existing.isPresent() && !existing.get().getId().equals(currentProductId)) {
            throw new BusinessException(messageResolver.getMessage(ErrorMessage.PRODUCT_NAME_DUPLICATE.code()));
        }
    }

    private void validatePrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException(messageResolver.getMessage(ErrorMessage.PRODUCT_PRICE_INVALID.code()));
        }
        if (price.scale() > 2) {
            throw new BusinessException(messageResolver.getMessage(ErrorMessage.PRODUCT_PRICE_DECIMAL.code()));
        }
    }
}
