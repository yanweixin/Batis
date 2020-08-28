package com.batis.application.web.controller;

import com.batis.application.database.entity.audit.ImportLog;
import com.batis.application.database.entity.bussiness.Vulnerability;
import com.batis.application.service.StorageService;
import com.batis.application.service.audit.ImportLogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.synth.SynthTabbedPaneUI;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("test")
public class TestController {
    private final ImportLog importLog = new ImportLog();

    @Autowired
    StorageService storageService;
    @Autowired
    ImportLogService importLogService;

    @PostMapping("/upload/vulnerability")
    public String uploadVulnerability(@RequestParam MultipartFile[] files) {
        Arrays.stream(files).forEach(it -> {
            String filename = StringUtils.cleanPath(it.getOriginalFilename());
            try {
                MessageDigest md5 = MessageDigest.getInstance("SHA-256");
                byte[] digest = md5.digest(it.getBytes());
                System.out.println(Arrays.toString(digest));
            } catch (NoSuchAlgorithmException | IOException e) {
                e.printStackTrace();
            }

            importLog.setFileName(filename);
            importLog.setSource("Manual");
            importLog.setTarget("vulnerability");

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(() -> {
                try {
                    storageService.store(it);
                    Path path = storageService.load(filename);
                    if (filename.endsWith("json")) {
                        importJson(Files.readString(path));
                    }else {
                        System.out.println("File extension error!");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
        System.out.println("Upload successfully");
        return "Upload successfully";
    }

    private void importJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode jsonNode = objectMapper.readTree(json).get("results");
        List<Vulnerability> vulnerabilities = objectMapper.convertValue(jsonNode, new TypeReference<>() {
        });
        vulnerabilities.forEach(vulnerability -> System.out.println(vulnerability.getVulndbId()));

        importLog.setMessage("Finished successfully!");
        importLog.setFinishedAt(LocalDateTime.now());
        importLogService.save(importLog);
    }

}
