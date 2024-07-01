package fpt.CapstoneSU24.service;


import fpt.CapstoneSU24.mapper.ProductMapper;
import fpt.CapstoneSU24.model.ImageProduct;
import fpt.CapstoneSU24.model.Product;
import fpt.CapstoneSU24.model.User;
import fpt.CapstoneSU24.dto.payload.AddProductRequest;
import fpt.CapstoneSU24.dto.payload.EditProductRequest;
import fpt.CapstoneSU24.dto.payload.FilterSearchRequest;
import fpt.CapstoneSU24.dto.payload.IdRequest;
import fpt.CapstoneSU24.repository.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private ImageProductRepository imageProductRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ProductMapper productMapper;

    public ResponseEntity addProduct(AddProductRequest req) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        if (currentUser.getRole().getRoleId() == 2) {
            try {
                if (categoryRepository.findOneByCategoryId(req.getCategoryId()) == null)
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("can not find id category");

                Product product = new Product();
                product.setProductName(req.getProductName());
                product.setCategory(categoryRepository.findOneByCategoryId(req.getCategoryId()));
                product.setDimensions(req.getDimensions());
                product.setMaterial(req.getMaterial());
                product.setWeight(req.getWeight());
                product.setDescription(req.getDescription());
                product.setWarranty(req.getWarranty());
                product.setCreateAt(System.currentTimeMillis());
                product.setManufacturer(currentUser);
                productRepository.save(product);
                //save image
                for (String obj : req.getImages()) {
                    String filePath = cloudinaryService.uploadImageAndGetPublicId(cloudinaryService.convertBase64ToImgFile(obj), "");
                    imageProductRepository.save(new ImageProduct(0, filePath, product));
                }
                //save ava
                String filePathAvatar = cloudinaryService.uploadImageAndGetPublicId(cloudinaryService.convertBase64ToImgFile(req.getAvatar()), "avatar/" + product.getProductId());
                imageProductRepository.save(new ImageProduct(0, filePathAvatar, product));
                //            return ResponseEntity.status(200).body(new String(bytes, StandardCharsets.UTF_8));
                return ResponseEntity.status(HttpStatus.OK).body("add product successfully");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error add new product");
            }
            //add product
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("your account is not allowed for this action");
        }
    }

    public ResponseEntity editProduct(EditProductRequest req) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        if (currentUser.getRole().getRoleId() == 2) {
            try {
                Product product = productRepository.findOneByProductId(req.getProductId());
                if (product == null){
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("product id don't exists");
                }
                product.setProductName(req.getProductName());
                product.setCategory(categoryRepository.findOneByCategoryId(req.getCategoryId()));
//            product.setUnitPrice(req.getUnitPrice());
                product.setDimensions(req.getDimensions());
                product.setMaterial(req.getMaterial());
                product.setWeight(req.getWeight());
                product.setDescription(req.getDescription());
                product.setWarranty(req.getWarranty());
                product.setCreateAt(System.currentTimeMillis());
//                product.setManufacturer(currentUser);
//                product.setCertificate(certificateRepository.findOneByCertificateId(req.getCertificateId()));
                productRepository.save(product);
                //save image
                if (!req.getImages().isEmpty()) {
                    try {
                        imageProductRepository.deleteImageProductWithFilePathNotStartingWithAvatar(product.getProductId());
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                    for (String obj : req.getImages()) {
                        String filePath = cloudinaryService.uploadImageAndGetPublicId(cloudinaryService.convertBase64ToImgFile(obj), "");
                        imageProductRepository.save(new ImageProduct(0, filePath, product));
                    }
                }
                //save ava
                if (!req.getAvatar().isEmpty()) {
                    imageProductRepository.deleteImageProductWithFilePathStartingWithAvatar(product.getProductId());
                    String filePathAvatar = cloudinaryService.uploadImageAndGetPublicId(cloudinaryService.convertBase64ToImgFile(req.getAvatar()), "avatar/" + product.getProductId());
                    imageProductRepository.save(new ImageProduct(0, filePathAvatar, product));
                }

                //            return ResponseEntity.status(200).body(new String(bytes, StandardCharsets.UTF_8));
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("add product successfully");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error edit product");
            }
            //add product
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("your account is not allowed for this action");
        }
    }

    public ResponseEntity findAllProductByManufacturerId(FilterSearchRequest req) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        try {
            Page<Product> products = null;
            Pageable pageable = req.getType().equals("desc") ? PageRequest.of(req.getPageNumber(), req.getPageSize(), Sort.by(Sort.Direction.DESC, "createAt")) :
                    req.getType().equals("asc") ? PageRequest.of(req.getPageNumber(), req.getPageSize(), Sort.by(Sort.Direction.ASC, "createAt")) :
                            PageRequest.of(req.getPageNumber(), req.getPageSize());
            if (req.getStartDate() != 0 && req.getEndDate() != 0) {
                products = productRepository.findByManufacturerAndCreateAtBetween(currentUser, req.getStartDate(), req.getEndDate(), pageable);
            } else {
                products = productRepository.findByManufacturerAndProductNameContaining(currentUser, req.getName(), pageable);
            }
            return ResponseEntity.status(HttpStatus.OK).body(products.map(productMapper::productToProductDTOResponse));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error when fetching data");
        }
    }

    public ResponseEntity findImgByProductId(IdRequest req) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        if (productRepository.findOneByProductId(req.getId()).getManufacturer().getUserId() == currentUser.getUserId()) {
            List<ImageProduct> imageProductList = imageProductRepository.findAllByProductId(req.getId());
            List<String> listImg = new ArrayList<String>();
            for (ImageProduct i : imageProductList) {
                listImg.add(i.getFilePath());
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("data", listImg);
            return ResponseEntity.status(HttpStatus.OK).body(jsonObject.toString());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("your account is not allowed for this action");
        }
    }

    public ResponseEntity findProductById(IdRequest req) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        if (productRepository.findOneByProductId(req.getId()).getManufacturer().getUserId() == currentUser.getUserId()) {
            Product product = productRepository.findOneByProductId(req.getId());
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("your account is not allowed for this action");
        }
    }

    public ResponseEntity deleteProductById(IdRequest req) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        Product productDelete = productRepository.findOneByProductId(req.getId());
        if(productDelete != null){
            if (productDelete.getManufacturer().getUserId() == currentUser.getUserId()) {
                if (itemRepository.findAllByProductId(req.getId()).isEmpty()) {
                    imageProductRepository.deleteByProductId(req.getId());
                    productRepository.deleteOneByProductId(req.getId());
                    return ResponseEntity.status(HttpStatus.OK).body("delete product success");
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("product can't delete because product have instants");
                }

            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("your account is not allowed for this action");
            }
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("not exist product id="+ req.getId());
    }
}
