package fpt.CapstoneSU24.service;

import fpt.CapstoneSU24.model.Category;
import fpt.CapstoneSU24.model.User;
import fpt.CapstoneSU24.dto.payload.CreateCategoryRequest;
import fpt.CapstoneSU24.dto.payload.EditCategoryRequest;
import fpt.CapstoneSU24.dto.payload.IdRequest;
import fpt.CapstoneSU24.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public ResponseEntity findAll() {
        List<Category> categoryList = categoryRepository.findAll();
        return ResponseEntity.ok(categoryList);
    }
    public ResponseEntity addCategory(CreateCategoryRequest req) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        if(currentUser.getRole().getRoleId() == 1){
            Category category = new Category(0,req.getName(), req.getDescription());
            categoryRepository.save(category);
            return ResponseEntity.status(HttpStatus.OK).body("successfully");
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Your account not permitted to handle this action");
        }
    }
    public ResponseEntity editCategory(EditCategoryRequest req) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        if(currentUser.getRole().getRoleId() == 1){
            Category category = categoryRepository.findOneByCategoryId(req.getCategoryId());
            if(category == null) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Can't find category by id");
            category.setName(req.getName());
            category.setDescription(req.getDescription());
            category.setName(req.getName());
            categoryRepository.save(category);
            return ResponseEntity.status(HttpStatus.OK).body("successfully");
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Your account not permitted to handle this action");
        }
    }
    public ResponseEntity deleteById(IdRequest req) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        if (currentUser.getRole().getRoleId() == 1) {
            try {
                Category category = categoryRepository.findOneByCategoryId(req.getId());
                if(category == null) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Can't find category by id");
                categoryRepository.deleteById(req.getId());
                return ResponseEntity.status(HttpStatus.OK).body("Successfully");
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error delete category");
            }
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Your account not permitted to handle this action");
        }
    }
}