package org.lrx.service;

import org.lrx.entity.UniversityPicture;
import org.lrx.entity.UniversityPicture2;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    public String uploadImg(MultipartFile img);
    int insertFile(List<UniversityPicture> universityPictureList);
    int insertFile2(List<UniversityPicture2> universityPictureList2);
}
