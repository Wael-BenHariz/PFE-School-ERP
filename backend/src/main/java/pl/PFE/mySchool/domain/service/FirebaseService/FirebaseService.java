package pl.PFE.mySchool.domain.service.FirebaseService;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.StorageOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
@Transactional
public class FirebaseService {
    public String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of("school-6ebdc.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        Credentials credentials = GoogleCredentials.fromStream(Files.newInputStream(Paths.get("school.json")));
        com.google.cloud.storage.Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format("https://firebasestorage.googleapis.com/v0/b/school-6ebdc.appspot.com/o/%s?alt=media", URLEncoder.encode(fileName, String.valueOf(StandardCharsets.UTF_8)));
    }


    public File convertToFile(MultipartFile multipartFile, String fileName) {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFile;    }


    public String AjouterImage2(MultipartFile multipartFile) {

        try {
            if (multipartFile != null) {
                System.out.println("1");
                if (multipartFile.getOriginalFilename() != null) {
                    System.out.println("2");
                    String fileName = multipartFile.getOriginalFilename();
                    File file = this.convertToFile(multipartFile, fileName);
                    String TEMP_URL = this.uploadFile(file, fileName);
                    file.delete();
                    return TEMP_URL;
                } else {
                    return "false";
                }
            } else {
                return "false1";

            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

        public String AjouterImage(MultipartFile multipartFile) {

            try {
                if (multipartFile !=null) {
                    if(multipartFile.getOriginalFilename()!=null) {
                        String fileName = multipartFile.getOriginalFilename();
                        File file = this.convertToFile(multipartFile, fileName);
                        String TEMP_URL = this.uploadFile(file, fileName);
                        file.delete();
                        return TEMP_URL;
                    }
                        else{
                            return "false";

                        }
                    }
                    else{
                        return "false";
                    }
            } catch (Exception e) {
                e.printStackTrace();
                return "false";
            }
    }




}
