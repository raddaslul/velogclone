package com.sparta.velogclone.service;

import com.sparta.velogclone.domain.ImageFile;
import com.sparta.velogclone.dto.requestdto.ImageFileRequestDto;
import com.sparta.velogclone.repository.ImageFileRepository;
import com.sparta.velogclone.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageFileService {

    private final ImageFileRepository imageFileRepository;

    private final S3Uploader s3Uploader;

    public ImageFile uploadFile(MultipartFile multipartFile) throws IOException {
        String originalFileName = multipartFile.getOriginalFilename();

//        int extensionIdx = multipartFile.getOriginalFilename().lastIndexOf(".");
//        String extension = originalFileName.substring(extensionIdx + 1);

//        UUID uuid = UUID.randomUUID(); // S3uploader에서 파일 이름 생성
//        String convertedFileName = uuid + "." + extension; // S3uploader에서 파일 이름 생성

        String convertedFileName = UUID.randomUUID() + originalFileName;
        String filePath = s3Uploader.upload(multipartFile, convertedFileName);


//        String savePath = System.getProperty("user.dir") + "\\files"; // 로컬에 저장할 때
//        String savePath = "files"; // ubuntu에 저장할 때
//        if (!new java.io.File(savePath).exists()) {
//            try {
//                new java.io.File(savePath).mkdir();
//            } catch (Exception e) {
//                e.getStackTrace();
//            }
//        }
//        String filePath = savePath + "\\" + convertedFileName; // 로컬에 저장할 때
//        Path filePath = Paths.get("/home/ubuntu/", savePath, "/" , convertedFileName); // ubuntu에 저장할 때
//        multipartFile.transferTo(new File(String.valueOf(filePath)));

        ImageFileRequestDto imageFileRequestDto = new ImageFileRequestDto();
        imageFileRequestDto.setOriginalFileName(originalFileName);
        imageFileRequestDto.setConvertedFileName(convertedFileName);
        imageFileRequestDto.setFilePath(String.valueOf(filePath));
        imageFileRequestDto.setFileSize(multipartFile.getSize());
        return imageFileRepository.save(imageFileRequestDto.toEntity());
    }


}
