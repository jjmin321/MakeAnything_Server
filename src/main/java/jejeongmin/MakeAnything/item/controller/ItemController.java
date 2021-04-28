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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @AutoLogging
    @GetMapping("/getItem")
    public Response getItem(@RequestParam String name) {
        Item item = itemService.getItem(name);
        return new ResponseData<Item>(HttpStatus.OK, "물품 정보 반환 성공", item);
    }

    @AutoLogging
    @GetMapping("/getRecentItems")
    public Response getRecentItems() {
        List<Item> items = itemService.getRecentItems();
        return new ResponseData<List<Item>>(HttpStatus.OK, "각 마켓 별 5가지 물품 정보 반환 성공", items);
    }

    @AutoLogging
    @GetMapping("/searchItems")
    public Response searchItems(@RequestParam String name) {
        List<Item> items = itemService.searchItems(name);
        return new ResponseData<List<Item>>(HttpStatus.OK, "물품 정보 검색 성공", items);
    }

    @AutoLoggingWithUser
    @AuthorizationCheck
    @PostMapping("/createItem")
    public Response createItem(HttpServletRequest request, @Valid @RequestBody ItemDto itemDto) {
        User user = (User) request.getAttribute("user");
        Item createdItem = itemService.createItem(user, itemDto);
        return new ResponseData<Item>(HttpStatus.OK, "물품 등록 성공", createdItem);
    }

    @AutoLogging
    @PostMapping("/uploadImage")
    public Response uploadImage(@RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        String fileName = itemService.uploadImage(file);
        return new ResponseData<String>(HttpStatus.OK, "파일 업로드 성공", fileName);
    }

}
