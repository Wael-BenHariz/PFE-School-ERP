package pl.PFE.mySchool.domain.shared;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    String saveImage(MultipartFile image) throws IOException;
}
