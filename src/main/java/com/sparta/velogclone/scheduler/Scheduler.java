package com.sparta.velogclone.scheduler;

import com.sparta.velogclone.domain.ImageFile;
import com.sparta.velogclone.repository.ImageFileRepository;
import com.sparta.velogclone.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Scheduler {
    
    public final ImageFileRepository imageFileRepository;
    public final S3Uploader s3Uploader;
    
    @Scheduled(cron = "0 0 0 0/1 * *")
    public void deleteImage() {
        List<ImageFile> imageFileList = imageFileRepository.findAll();
        for (ImageFile imageFile : imageFileList) {
            if(imageFile.getPost() == null) {
                String deleteFileURL = imageFile.getFilePath();
                s3Uploader.deleteFile(deleteFileURL);
                imageFileRepository.delete(imageFile);
            }
        }
    }
}
