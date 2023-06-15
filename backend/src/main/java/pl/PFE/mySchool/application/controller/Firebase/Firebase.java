package pl.PFE.mySchool.application.controller.Firebase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.PFE.mySchool.domain.service.FirebaseService.FirebaseService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/firebase")
@RequiredArgsConstructor
public class Firebase {
    private final FirebaseService firebaseService;

    @PostMapping("/url")
    @Secured({"TEACHER", "OFFICE", "DIRECTOR", "ADMIN"})
    public ResponseEntity<Map<String, String>> getFileUrl(@RequestBody MultipartFile file) {
        if (!file.isEmpty()) {
            String url = firebaseService.AjouterImage(file);
            Map<String, String> response = new HashMap<>();
            response.put("url", url);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "File is empty"));
        }
    }
}
