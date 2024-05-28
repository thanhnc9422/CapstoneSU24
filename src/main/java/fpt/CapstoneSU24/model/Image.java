package fpt.CapstoneSU24.model;

import jakarta.persistence.*;

@Entity
@Table(name = "image") // Đặt tên bảng trong cơ sở dữ liệu
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private int imageId;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "image", columnDefinition = "varbinary(MAX)")
    private byte[] image;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "item_log_id")
    private ItemLog itemLog;

    public Image(int imageId, String imageName, byte[] image, Product product, ItemLog itemLog) {
        this.imageId = imageId;
        this.imageName = imageName;
        this.image = image;
        this.product = product;
        this.itemLog = itemLog;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ItemLog getItemLog() {
        return itemLog;
    }

    public void setItemLog(ItemLog itemLog) {
        this.itemLog = itemLog;
    }
}

