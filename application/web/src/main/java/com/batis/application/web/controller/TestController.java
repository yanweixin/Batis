package com.batis.application.web.controller;

import com.batis.application.database.entity.audit.ImportLog;
import com.batis.application.database.entity.bussiness.vulnerability.Cnnvd;
import com.batis.application.database.entity.bussiness.vulnerability.Vulnerability;
import com.batis.application.service.system.StorageService;
import com.batis.application.service.audit.ImportLogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("test")
public class TestController {
    private ImportLog importLog;

    @Autowired
    StorageService storageService;
    @Autowired
    ImportLogService importLogService;

    @PostMapping("/upload/vulnerability")
    public String uploadVulnerability(@RequestParam MultipartFile[] files) {
        Arrays.stream(files).forEach(it -> {
            String filename = StringUtils.cleanPath(it.getOriginalFilename());
            try {
                System.out.println(DigestUtils.md5Hex(it.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            importLog = new ImportLog();
            importLog.setFileName(filename);
            importLog.setSource("Web");
            importLog.setTarget("vulnerability");

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(() -> {
                try {
                    storageService.store(it);
                    Path path = storageService.load(filename);
                    importLog.setProcessAt(LocalDateTime.now());
                    if (filename.endsWith("json")) {
                        importJson(Files.readString(path));
                    } else if (filename.endsWith("xml")) {
                        importXml(Files.readString(path));
                    } else {
                        System.out.println("File extension error!");
                    }
                } catch (Exception e) {
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
        importLog.setMessage("Finished importing json successfully!");
        importLog.setFinishedAt(LocalDateTime.now());
        importLogService.save(importLog);
    }

    private void importXml(String xml) throws JAXBException, JsonProcessingException {
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
//        DocumentBuilder builder = factory.newDocumentBuilder();
//        Document document = builder.parse(new InputSource(new StringReader(xml)));
//        NodeList nodeList = document.getElementsByTagName("entry");
//        for (int i =0;i<nodeList.getLength();i++){
//            Node node = nodeList.item(i);
//            System.out.println(node.getNodeValue());
//        }

        JAXBContext jaxbContext = JAXBContext.newInstance(Cnnvd.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Cnnvd cnnvd = (Cnnvd) unmarshaller.unmarshal(new StringReader(xml));
//        Cnnvd cnnvd = JAXB.unmarshal(new StringReader(xml),Cnnvd.class);

        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.cnnvd.org.cn/vuln/1.0 http://www.cnnvd.org.cn/schema/vulnerability.xsd");
        marshaller.marshal(cnnvd, System.out);
        importLog.setMessage("Finished importing xml successfully!");
        importLog.setFinishedAt(LocalDateTime.now());
        importLogService.save(importLog);
    }

}
