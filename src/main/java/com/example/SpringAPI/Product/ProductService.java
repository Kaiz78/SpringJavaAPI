package com.example.SpringAPI.Product;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

  private final ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }
    public void addNewProduct(Product product) {
        Optional<Product> productOptional = productRepository
                .findProductBySku(product.getSku());
        // Si le SKU existe déjà
        if(productOptional.isPresent()){
            throw new IllegalStateException("Sku taken");
        }
        // Si le SKU est vide
        if(product.getSku() == null || product.getSku().length() == 0){
            throw new IllegalStateException("The SKU cannot be empty");
        }

        product.setStatus(product.getStatus() != null ? product.getStatus() : "DISABLED");

        productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        boolean exists = productRepository.existsById(productId);
        if(!exists){
            throw new IllegalStateException(
                    "product with id " + productId + " does not exists"
            );
        }
        productRepository.deleteById(productId);
    }

    @Transactional
    public void updateProduct(long id,Product product) {
        Product productUpdate = productRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException(
                        "product with id " + id + " does not exists"
                ));

        productUpdate.setName(product.getName() != null ? product.getName() : productUpdate.getName());
        productUpdate.setDescription(product.getDescription() != null ? product.getDescription() : productUpdate.getDescription());
        productUpdate.setPrice(product.getPrice() != null ? product.getPrice() : productUpdate.getPrice());
        productUpdate.setImage(product.getImage() != null ? product.getImage() : productUpdate.getImage());
        productUpdate.setCategory(product.getCategory() != null ? product.getCategory() : productUpdate.getCategory());
        productUpdate.setStatus(product.getStatus() != null ? product.getStatus() : productUpdate.getStatus());
        productUpdate.setUpdated_at(Date.from(LocalDateTime.now().atZone(
                java.time.ZoneId.systemDefault()).toInstant()));

        if(product.getSku() != null &&
                product.getSku().length() > 0 &&
                !product.getSku().equals(productUpdate.getSku())){
            Optional<Product> productOptional = productRepository
                    .findProductBySku(product.getSku());
            if(productOptional.isPresent()){
                throw new IllegalStateException("Sku taken");
            }
            productUpdate.setSku(product.getSku());
        }

        productRepository.save(productUpdate);
    }


    public void uploadImage(MultipartFile file) throws IOException {
        String UPLOAD_DIR = "C:\\Users\\User\\Desktop\\SpringAPI\\src\\main\\resources\\static\\images";
        Path uploadPath = Path.of(UPLOAD_DIR);
        if (!uploadPath.toFile().exists()) {
            uploadPath.toFile().mkdir();
        }

        String fileName = file.getOriginalFilename();

        Path filePath = Path.of(UPLOAD_DIR, fileName);
        file.transferTo(filePath);

    }

}
