package jejeongmin.MakeAnything.item.controller;

import jejeongmin.MakeAnything.common.annotation.AuthorizationCheck;
import jejeongmin.MakeAnything.common.annotation.AutoLogging;
import jejeongmin.MakeAnything.common.annotation.AutoLoggingWithUser;
import jejeongmin.MakeAnything.common.vo.http.Response;
import jejeongmin.MakeAnything.common.vo.http.ResponseData;
import jejeongmin.MakeAnything.item.domain.dto.ItemDto;
import jejeongmin.MakeAnything.item.domain.entity.Item;
import jejeongmin.MakeAnything.item.service.ItemService;
import jejeongmin.MakeAnything.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    @AutoLogging
    @GetMapping("/getItem")
    public Response getItem(@RequestParam String name) {
        Item item = itemService.getItem(name);
        return new ResponseData<>(HttpStatus.OK, "물품 정보 반환 성공", item);
    }

    @AutoLogging
    @GetMapping("/getAllItems")
    public Response getAllItems() {
        List<Item> items = itemService.getAllItems();
        return new ResponseData<>(HttpStatus.OK, "전체 물품 기존 등록 순으로 정보 반환 성공", items);
    }

    @AutoLogging
    @GetMapping("/getAllItemsDesc")
    public Response getAllItemsDesc() {
        List<Item> items = itemService.getAllItemsDesc();
        return new ResponseData<>(HttpStatus.OK, "전체 물품 최신 등록 순으로 반환 성공", items);
    }

    @AutoLogging
    @GetMapping("/getAllItemsByPrice")
    public Response getAllItemsByPrice() {
        List<Item> items = itemService.getAllItemsByPrice();
        return new ResponseData<>(HttpStatus.OK, "전체 물품 가격 낮은 순으로 반환 성공", items);
   }

   @AutoLogging
   @GetMapping("/getAllItemsByPriceDesc")
   public Response getAllItemsByPriceDesc() {
        List<Item> items = itemService.getAllItemsByPriceDesc();
        return new ResponseData<>(HttpStatus.OK, "전체 물품 가격 높은 순으로 반환 성공", items);
   }

   @AutoLogging
   @GetMapping("/getTalentItems")
   public Response getTalentItems() {
        List<Item> items = itemService.getTalentItems();
        return new ResponseData<>(HttpStatus.OK, "재능 물품 최신 등록 순으로 반환 성공", items);
   }

   @AutoLogging
   @GetMapping("/getUsedItems")
   public Response getUsedItems() {
        List<Item> items = itemService.getUsedItems();
        return new ResponseData<>(HttpStatus.OK, "중고 물품 최신 등록 순으로 반환 성공", items);
   }

   @AutoLogging
   @GetMapping("/getCustomItems")
   public Response getCustomItems() {
        List<Item> items = itemService.getCustomItems();
        return new ResponseData<>(HttpStatus.OK, "판매 요청 물품 최신 등록순으로 반환 성공", items);
   }


    @AutoLogging
    @GetMapping("/searchItems")
    public Response searchItems(@RequestParam String name) {
        List<Item> items = itemService.searchItems(name);
        return new ResponseData<>(HttpStatus.OK, "물품 정보 검색 성공", items);
    }

    @AutoLoggingWithUser
    @AuthorizationCheck
    @PostMapping("/createItem")
    public Response createItem(HttpServletRequest request, @Valid @RequestBody ItemDto itemDto) {
        User user = (User) request.getAttribute("user");
        Item createdItem = itemService.createItem(user, itemDto);
        return new ResponseData<>(HttpStatus.OK, "물품 등록 성공", createdItem);
    }

    @AutoLogging
    @PostMapping("/uploadImage")
    public Response uploadImage(@RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        String fileName = itemService.uploadImage(file);
        return new ResponseData<>(HttpStatus.OK, "파일 업로드 성공", fileName);
    }

}
