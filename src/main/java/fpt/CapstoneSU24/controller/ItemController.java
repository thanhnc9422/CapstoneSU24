package fpt.CapstoneSU24.controller;

import fpt.CapstoneSU24.dto.*;
import fpt.CapstoneSU24.dto.payload.FilterByTimeStampRequest;
import fpt.CapstoneSU24.dto.payload.FilterSearchItemRequest;
import fpt.CapstoneSU24.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RequestMapping("/api/item")
@RestController
@Validated
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
    public ResponseEntity<?> searchItem(@Valid @RequestBody FilterSearchItemRequest req) {
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

    //list all item_log by product_recogine
    @GetMapping("/viewLineItem")
    public ResponseEntity<?> viewLineItem(@RequestParam String productRecognition) {
        return itemService.viewLineItem(productRecognition);

    }

    @GetMapping("/viewOrigin")
    public ResponseEntity<?> viewOrigin(@RequestParam int itemLogId) {
        return itemService.viewOrigin(itemLogId);
    }

    //Da sua chua test cho su kien getCertificate
    @PostMapping("/getCertificate")
    public ResponseEntity<?> getCertificate(@Valid @RequestBody CurrentOwnerCheckDTO req) {
         return itemService.getCertificate(req);
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
    //Da sua chua test cho su kien uy quyen
    @PostMapping(value = "/authorized")
    public ResponseEntity<?> authorize(@Valid @RequestBody AuthorizedDTO authorized) {
        return itemService.authorize(authorized);
    }
//    @PostMapping(value = "/editAuthorized")
//    public ResponseEntity<?> editAuthorized(@Valid @RequestBody AuthorizedDTO authorized){
//        return itemService.editAuthorized(authorized);
//    }
    //Da sua chua test cho su kien uy quyen
    @PostMapping(value = "/checkEventAuthorized")
    public ResponseEntity<Integer> checkEventAuthorized(@RequestParam String productRecognition) {
        return itemService.checkEventAuthorized(productRecognition);
        // B1. Kiểm tra xem email này có phải currentOwner với status là 1 không
        // - Nếu mà không phải currentOwner => không cho ủy quyền người tiếp theo
    }
    @PostMapping(value = "/checkPartyFirst")
    @Validated
    public ResponseEntity<Integer> checkPartyFirst(@Valid @RequestBody CurrentOwnerCheck req)  {
        return itemService.checkPartyFirst(req);
    }

    @PostMapping(value = "/check")
    public ResponseEntity<Integer> check(@Valid @RequestBody CurrentOwnerCheck req) throws URISyntaxException, IOException, InterruptedException {
        return itemService.check(req);
    }


    @PostMapping(value = "/sendOTP")
    public ResponseEntity<?> sendOTP(@Valid @RequestParam String email) {
        return itemService.sendOTP(email);
    }

    // API verify OTP
    @PostMapping(value = "/confirmOTP")
    public ResponseEntity<?> confirmOTP(@Valid @RequestBody SendOTP otp, @RequestParam String productRecognition) {
        return itemService.confirmOTP(otp,productRecognition);
    }
    //Da sua chua test
    @PostMapping(value = "/abortItem")
    public ResponseEntity<?> abortItem(@RequestBody AbortDTO abortDTO ){
        return itemService.abortItem(abortDTO);
    }

    @GetMapping(value = "/getItemByEventType")
    public ResponseEntity<?> getItemByEventType(int eventType){
        return itemService.getItemByEventType(eventType);
    }

    @GetMapping(value="getInforItemByProductRecognition")
    public  ResponseEntity<?> getInfoItemByItemId(String productRecognition){
        return itemService.getInforItemByItemId(productRecognition);
    }
    @PostMapping(value = "/listPartyJoin")
    public ResponseEntity<?>  listPartyJoin(@RequestBody CurrentOwnerCheck req){
        return itemService.listPartyJoin(req);
    }

}

