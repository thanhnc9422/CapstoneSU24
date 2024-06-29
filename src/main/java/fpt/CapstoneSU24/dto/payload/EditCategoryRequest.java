package fpt.CapstoneSU24.dto.payload;

import jakarta.validation.constraints.NotBlank;

public class EditCategoryRequest {
    @NotBlank(message = "The categoryId is required")
    private int categoryId;
    @NotBlank(message = "The name is required")
    private String name;
    @NotBlank(message = "The description is required")
    private String description;

    public int getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
