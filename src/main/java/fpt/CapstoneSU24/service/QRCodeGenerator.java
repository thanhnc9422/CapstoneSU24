package fpt.CapstoneSU24.service;

import fpt.CapstoneSU24.model.Product;
import fpt.CapstoneSU24.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

@Service

public class QRCodeGenerator {
    @Autowired
public ProductRepository productRepository;
    private static final Random random = new Random();
    public String generateProductCode(int productId, int quantity) throws NoSuchAlgorithmException {
        // Assuming productRepository is properly initialized
        System.out.println("var" + productId );

        Product product = productRepository.findOneByProductId(productId);
        if (product == null) {
            throw new IllegalArgumentException("Product not found with ID: " + productId);
        }

        // Generate a random number between 0 and quantity - 1
        int randomNumber = random.nextInt(quantity);

        // Get current time in milliseconds
        long currentTimeMillis = System.currentTimeMillis();

        // Create raw code string
        String rawCode = product.getProductName() + "-" + product.getCategory().getName() + "-" + randomNumber + "-" + currentTimeMillis;

        // Hash the raw code using SHA-256
        String hashedCode = hashString(rawCode);

        return hashedCode;
    }

    private String hashString(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(input.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
//    public String generateProductCode(int productId, int quantity) {
//        Product product = productRepository.findOneByProductId(productId);
//        // Lấy UUID ngẫu nhiên
//        UUID uuid = UUID.randomUUID();
//
//        // Lấy thời gian hiện tại dưới dạng milliseconds
//        long currentTimeMillis = System.currentTimeMillis();
//
//        // Tạo chuỗi mã sản phẩm dựa trên các thông tin
//        String rawCode = product.getProductName() + "-" + product.getCategory().getName() + "-" + uuid.toString() + "-" + currentTimeMillis;
//
//        // Mã hóa chuỗi thành Base64 để dễ đọc hơn
//        String encodedCode = Base64.getUrlEncoder().encodeToString(rawCode.getBytes(StandardCharsets.UTF_8));
//
//        return encodedCode;
//    }

}