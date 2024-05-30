package fpt.CapstoneSU24.model;

import jakarta.persistence.*;

@Entity
@Table(name = "item") // Đặt tên bảng trong cơ sở dữ liệu
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private int itemId;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "currentOwner")
    private String currentOwner;
    @ManyToOne
    @JoinColumn(name = "origin_id")
    private Origin origin;
    @Column(name = "created_at")
    private long createdAt;
    @Column(name = "product_recognition", columnDefinition = "nvarchar(255)")
    private String productRecognition;
    @Column(name = "status")
    private int status;

    public Item(int itemId, Product product, String currentOwner, Origin origin, long createdAt, String productRecognition, int status) {
        this.itemId = itemId;
        this.product = product;
        this.currentOwner = currentOwner;
        this.origin = origin;
        this.createdAt = createdAt;
        this.productRecognition = productRecognition;
        this.status = status;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getCurrentOwner() {
        return currentOwner;
    }

    public void setCurrentOwner(String currentOwner) {
        this.currentOwner = currentOwner;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public String getProductRecognition() {
        return productRecognition;
    }

    public void setProductRecognition(String productRecognition) {
        this.productRecognition = productRecognition;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
