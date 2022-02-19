package com.sparta.velogclone.service;

import com.sparta.velogclone.domain.ImageFile;
import com.sparta.velogclone.dto.requestdto.ImageFileRequestDto;
import com.sparta.velogclone.repository.ImageFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageFileService {

    private final ImageFileRepository imageFileRepository;

    public ImageFile saveFile(MultipartFile multipartFile) throws IOException {
        String originalFileName = multipartFile.getOriginalFilename();
        int extensionIdx = multipartFile.getOriginalFilename().lastIndexOf(".");
        String extension = originalFileName.substring(extensionIdx + 1);

        UUID uuid = UUID.randomUUID();
        String convertedFileName = uuid + "." + extension;
        String savePath = System.getProperty("user.dir") + "\\files";
        if (!new java.io.File(savePath).exists()) {
            try {
                new java.io.File(savePath).mkdir();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        String filePath = savePath + "\\" + convertedFileName;
        multipartFile.transferTo(new File(filePath));

        ImageFileRequestDto imageFileRequestDto = new ImageFileRequestDto();
        imageFileRequestDto.setOriginalFileName(originalFileName);
        imageFileRequestDto.setConvertedFileName(convertedFileName);
        imageFileRequestDto.setFilePath(filePath);
        imageFileRequestDto.setFileSize(multipartFile.getSize());
        return imageFileRepository.save(imageFileRequestDto.toEntity());
    }
}
