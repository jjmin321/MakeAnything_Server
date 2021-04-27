package jejeongmin.MakeAnything.item.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ItemService {

    String uploadImage(MultipartFile file) throws IOException;

}
