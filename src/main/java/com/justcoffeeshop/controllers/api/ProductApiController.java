package com.justcoffeeshop.controllers.api;

import com.justcoffeeshop.controllers.api.dto.ProductDTO;
import com.justcoffeeshop.errors.ObjectNotFoundException;
import com.justcoffeeshop.models.CategoryModel;
import com.justcoffeeshop.models.ProductModel;
import com.justcoffeeshop.models.SupplierModel;
import com.justcoffeeshop.services.CategoryService;
import com.justcoffeeshop.services.ProductService;
import com.justcoffeeshop.services.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductApiController {

    private final ProductService productService;
    private final SupplierService supplierService;
    private final CategoryService categoryService;

    public ProductApiController(ProductService productService, SupplierService supplierService, CategoryService categoryService) {
        this.productService = productService;
        this.supplierService = supplierService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<ProductModel>> list() {
        return new ResponseEntity(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductModel> findById(@PathVariable Long id) {
        ProductModel productModel = productService.findById(id).orElseThrow(() -> new ObjectNotFoundException("product"));
        return new ResponseEntity(productModel, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductModel> save(@RequestBody ProductDTO dto) {
        SupplierModel supplierModel = supplierService.findById(dto.getSupplierId()).orElseThrow(() -> new ObjectNotFoundException("supplier"));
        CategoryModel categoryModel = categoryService.findById(dto.getCategoryId()).orElseThrow(() -> new ObjectNotFoundException("category"));
        ProductModel productModel = new ProductModel();
        productModel.setPrice(dto.getPrice());
        productModel.setUnit(dto.getUnit());
        productModel.setProductName(dto.getProductName());
        productModel.setSupplierModel(supplierModel);
        productModel.setCategoryModel(categoryModel);
        return new ResponseEntity(productService.save(productModel), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductModel> update(@PathVariable Long id, @Valid @RequestBody ProductDTO dto) {
        ProductModel productModel = productService.findById(id).orElseThrow(() -> new ObjectNotFoundException("product"));
        SupplierModel supplierModel = supplierService.findById(dto.getSupplierId()).orElseThrow(() -> new ObjectNotFoundException("supplier"));
        CategoryModel categoryModel = categoryService.findById(dto.getCategoryId()).orElseThrow(() -> new ObjectNotFoundException("category"));
        productModel.setPrice(dto.getPrice());
        productModel.setUnit(dto.getUnit());
        productModel.setProductName(dto.getProductName());
        productModel.setSupplierModel(supplierModel);
        productModel.setCategoryModel(categoryModel);
        return new ResponseEntity(productService.save(productModel), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        productService.findById(id).orElseThrow(() -> new ObjectNotFoundException("product"));
        return new ResponseEntity<>(productService.delete(id), HttpStatus.OK);
    }
}
