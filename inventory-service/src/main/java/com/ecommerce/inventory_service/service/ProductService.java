package com.ecommerce.inventory_service.service;

import com.ecommerce.inventory_service.dto.OrderRequestDto;
import com.ecommerce.inventory_service.dto.OrderRequestItemDto;
import com.ecommerce.inventory_service.dto.ProductDto;
import com.ecommerce.inventory_service.entity.Product;
import com.ecommerce.inventory_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public List<ProductDto> getAllInventory(){
        log.info("Fetching all inventory items");
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
    }

    public ProductDto getProductById(Long id){
        log.info("Fetching Product with ID: {} ", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));

        return modelMapper.map(product, ProductDto.class);
    }

    @Transactional
    public Double reduceStocks(OrderRequestDto orderRequestDto) {
        log.info("Reducing the stocks");
        Double totalPrice = 0.0;
        for(OrderRequestItemDto orderRequestItemDto: orderRequestDto.getItems()){
                Long productId = orderRequestItemDto.getProductId();
                Integer quantity = orderRequestItemDto.getQuantity();

                Product product = productRepository.findById(productId)
                        .orElseThrow(() -> new RuntimeException("Product not found with id " + productId));

                if(product.getStock() < quantity)
                    throw new RuntimeException("Product cannot be fulfilled for given quantity");

                product.setStock(product.getStock() - quantity);
                productRepository.save(product);
                totalPrice += product.getPrice() * quantity;

        }
        return totalPrice;
    }
}
