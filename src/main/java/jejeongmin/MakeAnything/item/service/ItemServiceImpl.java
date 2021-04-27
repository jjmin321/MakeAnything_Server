package jejeongmin.MakeAnything.item.service;

import jejeongmin.MakeAnything.common.exception.FileIsEmptyException;
import jejeongmin.MakeAnything.common.lib.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private File fileLib;

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new FileIsEmptyException();
        }
        return fileLib.uploadFile(file);
    }

}
