package fpt.CapstoneSU24.controller;

import fpt.CapstoneSU24.dto.*;
import fpt.CapstoneSU24.model.*;
import fpt.CapstoneSU24.dto.payload.FilterByTimeStampRequest;
import fpt.CapstoneSU24.dto.payload.FilterSearchRequest;
import fpt.CapstoneSU24.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RequestMapping("/api/item")
@RestController
public class ItemController {

    //private static final Logger log = LoggerFactory.getLogger(ItemController.class);
    private final ItemService itemService;
    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /*
     * type is sort type: "desc" or "asc"
     * default data startDate and endDate equal 0 (need insert 2 data)
     * */
    @PostMapping("/search")
    public ResponseEntity<?> searchItem(@Valid @RequestBody FilterSearchRequest req) {
        return itemService.searchItem(req);
    }

    @PostMapping("/exportListItem")
    public ResponseEntity<?> exportListItem(@Valid @RequestBody FilterByTimeStampRequest req) throws IOException {
        return itemService.exportListItem(req);
    }

    @PostMapping("/addItem")
    public ResponseEntity<?> addItem(@Valid @RequestBody ItemLogDTO itemLogDTO) {
        return itemService.addItem(itemLogDTO);

    }



//    private double parseCoordinate(String coordinate) throws InvalidCoordinateException {
//        try {
//            return Double.parseDouble(coordinate);
//        } catch (NumberFormatException ex) {
//            throw new InvalidCoordinateException("Invalid coordinate: " + coordinate);
//        }
//    }


    public static class InvalidCoordinateException extends Exception {
        public InvalidCoordinateException(String message) {
            super(message);
        }
    }

    public static class CoordinateOutOfRangeException extends Exception {
        public CoordinateOutOfRangeException(String message) {
            super(message);
        }
    }


    @GetMapping("/findAllItemByProductId")
    public ResponseEntity<?> findAllItemByProductId(@RequestParam int ProductId) {
        return itemService.findByProductId(ProductId);
    }


    //list all item_log by product_recogine
    @GetMapping("/viewLineItem")
    public ResponseEntity<?> viewLineItem(@RequestParam String productRecognition) {
        return itemService.viewLineItem(productRecognition);

    }



    @GetMapping("/viewOrigin")
    public ResponseEntity<?> viewOrigin(@RequestParam int itemLogId) {
        return itemService.viewOrigin(itemLogId);
    }

    @GetMapping("/getCertificate")
    public ResponseEntity<?> getCertificate(@RequestBody String req, @RequestParam String productRecognition) {
        return itemService.getCertificate(req,productRecognition);
    }


//    @GetMapping("/getTESTCertificate")
//    public ResponseEntity<?> getTESTCertificate(@RequestParam String productRecognition) throws IOException {
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_PDF);
//        headers.setContentDispositionFormData("attachment", "certificate.pdf");
//        Item item = itemRepository.findByProductRecognition(productRecognition);
//
//        String pdfLink = item.getCertificateLink();
//        headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");
//
//        // Return the PDF link as a response entity
//        return ResponseEntity.ok().headers(headers).body(pdfLink);
//    }

//    @GetMapping("/convertHtmlToPdf")
//    public ResponseEntity<byte[]> convertHtmlToPdf() {
//        // HTML content to convert to PDF
//        String htmlContent = "<html><body><h1>Hello, World!</h1></body></html>";
//
//        // Prepare ByteArrayOutputStream to hold PDF bytes
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//
//        try {
//            // Create PdfWriter
//            PdfWriter writer = new PdfWriter(baos);
//            // Create PdfDocument
//            PdfDocument pdf = new PdfDocument(writer);
//            // Set landscape orientation
//            pdf.setDefaultPageSize(PageSize.A4.rotate());
//
//            // Convert HTML to PDF
//            ConverterProperties props = new ConverterProperties();
//            HtmlConverter.convertToPdf(htmlContent, pdf, props);
//
//            // Close PdfDocument
//            pdf.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // Prepare HttpHeaders
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_PDF);
//        headers.setContentDispositionFormData("attachment", "converted-document.pdf");
//
//        // Return PDF as ResponseEntity
//        return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
//    }

//    private boolean arePointsOnCurve(List<ItemLog> pointLogs) {
//        List<Point> points = new ArrayList<>();
//        for (int i = 1; i < pointLogs.size() - 1; i++) {
//            Point point = pointService.getPoint(pointLogs.get(i).getPoint());
//            points.add(point);
//        }
//
//        Point startPoint = pointService.getPoint(pointLogs.get(0).getPoint());
//        return pointService.isPointOnCurve(points, startPoint);
//    }

    @PostMapping(value = "/confirmCurrentOwner")
    public ResponseEntity<Boolean> confirmCurrentOwner(@RequestBody SendOTP otp, @RequestParam String productRecognition) {
        // B1: Người dùng nhập OTP confirm chính xác bằng cách check OTP trong DB và người dùng nhập
        // - Chính xác => Buoc tiep theo
        return itemService.confirmCurrentOwner(otp,productRecognition);

    }

    /**
     * API uy quyen checkCurrentOwner => authorized
     * Cai nay la uy quyen nguoi nhan
     * B1. Kiem tra xem san pham nay da duoc uy quyen chua bang cach check currentOwner voi status la 0
     * - Neu ma co currentOwner roi va co status la 1 thi co nghia la chua uy quyen
     * - Neu ma co currentOwner roi va co status la 0 thi co nghia la da uy quyen roi => khong cho uy quyen nua
     * B2. Neu chua uy quyen thi
     * - Update currentOwner voi email cua nguoi duoc uy quyen va status la 0
     * - Update bang itemLog voi id dai nhat voi authorized_id va bang authorized nhung thong tin cua nguoi duoc uy quyen
     * - Gui mail thong bao cho nguoi dung la bạn da duoc uy quyen
     */
    @PostMapping(value = "/authorized")
    public ResponseEntity<?> authorize(@RequestBody Authorized authorized, @RequestParam int itemLogId) {
        return itemService.authorize(authorized,itemLogId);
    }

    @PostMapping(value = "/checkAuthorized")
    public ResponseEntity<Boolean> checkAuthorized(@RequestParam String productRecognition)  {
        return itemService.checkAuthorized(productRecognition);
        // B1. Kiểm tra xem email này có phải currentOwner với status là 1 không
        // - Nếu mà không phải currentOwner => không cho ủy quyền người tiếp theo
    }

    //API check CurrentOwner
    @PostMapping(value = "/checkCurrentOwner")
    public ResponseEntity<Boolean> checkCurrentOwner(@RequestBody String email, @RequestParam String productRecognition) {
        // B1. Kiểm tra xem email này có phải currentOwner với status là 1 không
        // - Nếu mà không phải currentOwner => không cho ủy quyền người tiếp theo
        return itemService.checkCurrentOwner(email,productRecognition);
    }

    @PostMapping(value = "/sendCurrentOwnerOTP")
    public ResponseEntity<?> sendCurrentOwnerOTP(@RequestBody String req) {
        return itemService.sendCurrentOwnerOTP(req);
    }

    //API send OTP  nhap mail => sendOTP chua check
    @PostMapping(value = "/sendOTP")
    public ResponseEntity<?> sendOTP(@RequestBody String req, @RequestParam String productRecognition) {
        return itemService.sendOTP(req,productRecognition);

    }


    // API verify OTP
    @PostMapping(value = "/confirmOTP")
    public ResponseEntity<Boolean> confirmOTP(@RequestBody SendOTP otp, @RequestParam String productRecognition) {
        return itemService.confirmOTP(otp,productRecognition);

    }
}

