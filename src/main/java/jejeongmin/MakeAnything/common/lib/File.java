package jejeongmin.MakeAnything.common.lib;

import jejeongmin.MakeAnything.common.exception.MakeDirectoryException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Component
public class File {

    public void makeDirectoryIfNotExist(Path target) {
        if (!target.getParent().toFile().exists()) {
            if (target.getParent().toFile().mkdir()) {
                System.out.println("디렉토리가 생성됨");
            } else {
                throw new MakeDirectoryException();
            }
        }
    }

    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(UUID.randomUUID().toString() + "-" + file.getOriginalFilename());
        Path target = Paths.get("./src/main/resources/static").toAbsolutePath().normalize().resolve(fileName);
        makeDirectoryIfNotExist(target);
        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
        return fileName;
    }

}
