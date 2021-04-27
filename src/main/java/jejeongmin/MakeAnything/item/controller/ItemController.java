package jejeongmin.MakeAnything.item.controller;

import jejeongmin.MakeAnything.common.annotation.AutoLogging;
import jejeongmin.MakeAnything.common.vo.http.Response;
import jejeongmin.MakeAnything.common.vo.http.ResponseData;
import jejeongmin.MakeAnything.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @AutoLogging
    @PostMapping("/uploadImage")
    public Response uploadImage(@RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        String fileName = itemService.uploadImage(file);
        return new ResponseData<String>(HttpStatus.OK, "파일 업로드 성공", fileName);
    }

}
