package fpt.CapstoneSU24.controller;

import fpt.CapstoneSU24.dto.AbortDTO;
import fpt.CapstoneSU24.dto.EditItemLogDTO;
import fpt.CapstoneSU24.dto.EventItemLogDTO;
import fpt.CapstoneSU24.service.ItemLogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/itemlog")
@RestController
public class ItemLogController {
    private final ItemLogService itemLogService;

    @Autowired
    public ItemLogController(ItemLogService itemLogService) {
        this.itemLogService = itemLogService;
    }

    @PostMapping(value = "/additemlogTransport")
    public ResponseEntity<?> addItemLog(@Valid @RequestBody EventItemLogDTO itemLogDTO) {
        return itemLogService.addItemLog(itemLogDTO);
    }


    @GetMapping(value = "/getItemLogDetail")
    public ResponseEntity<?> getItemLogDetail(@RequestParam int itemLogId) {
        if(itemLogId < 0) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ItemLogId is not null");
        return itemLogService.getItemLogDetail(itemLogId);
    }
    @PostMapping(value = "/editItemLog")
    public ResponseEntity<?> editItemLog(@Valid @RequestBody EditItemLogDTO dataEditDTO){
        return itemLogService.editItemLog(dataEditDTO);
    }
}
