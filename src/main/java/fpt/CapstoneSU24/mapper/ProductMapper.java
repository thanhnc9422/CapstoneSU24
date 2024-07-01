package fpt.CapstoneSU24.mapper;

import fpt.CapstoneSU24.dto.ProductDTOResponse;
import fpt.CapstoneSU24.model.ImageProduct;
import fpt.CapstoneSU24.model.Product;
import fpt.CapstoneSU24.repository.ImageProductRepository;
import fpt.CapstoneSU24.service.CloudinaryService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class ProductMapper {

    @Autowired
    private ImageProductRepository imageProductRepository;
    @Autowired
    private CloudinaryService cloudinaryService;

    @Mapping(source = "productName", target = "productName")
    @Mapping(source = "description", target = "description")
    @Mapping(target = "avatar", ignore = true) // Ignore avatar for now, we'll set it manually
    public abstract ProductDTOResponse productToProductDTOResponse(Product product);

    @AfterMapping
    protected void setAvatar(Product product, @MappingTarget ProductDTOResponse productDTO) {
        ImageProduct imageProduct = imageProductRepository.findAllByFilePath("avatar/"+product.getProductId());
        if (imageProduct != null) {
            productDTO.setAvatar(cloudinaryService.getImageUrl(imageProduct.getFilePath()));
        }
    }
}