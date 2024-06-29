package fpt.CapstoneSU24.controller;

import fpt.CapstoneSU24.dto.payload.AddProductRequest;
import fpt.CapstoneSU24.dto.payload.EditProductRequest;
import fpt.CapstoneSU24.dto.payload.FilterSearchRequest;
import fpt.CapstoneSU24.dto.payload.IdRequest;
import fpt.CapstoneSU24.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(@Valid @RequestBody AddProductRequest req) {
        return productService.addProduct(req);
    }

//    - có thêm trường product Id
//    - nếu không ko edit image (images, avatar) thì để là ""
    @PostMapping("/editProduct")
    public ResponseEntity<?> editProduct(@Valid @RequestBody EditProductRequest req) {
        return productService.editProduct(req);
    }


    @PostMapping("/findAllProductByManufacturerId")
    public ResponseEntity<?> findAllProductByManufacturerId(@Valid @RequestBody FilterSearchRequest req) {
        return productService.findAllProductByManufacturerId(req);

    }
    @PostMapping("/findImgByProductId")
    public ResponseEntity<?> findImgByProductId(@Valid @RequestBody IdRequest req) {
        return productService.findImgByProductId(req);

    }
    @PostMapping("/findProductById")
    public ResponseEntity<?> findProductById(@Valid @RequestBody IdRequest req) {
        return productService.findProductById(req);

    }
    @PostMapping("/deleteProductById")
    public ResponseEntity<?> deleteProductById(@Valid @RequestBody IdRequest req) {
        return productService.deleteProductById(req);

    }
}
