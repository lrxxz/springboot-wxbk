package org.lrx.service.serviceImpl;

import org.lrx.dao.MatterMapper;
import org.lrx.dao.UniversityMapper;
import org.lrx.entity.UniversityPicture;
import org.lrx.entity.UniversityPicture2;
import org.lrx.service.FileService;
import org.lrx.until.FileUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileUntil fileUntil;

    @Autowired
    MatterMapper matterMapper;

    @Autowired
    UniversityMapper universityMapper;

    /**
     * 文件上传
     * @param img
     * @return
     */
    @Override
    public String uploadImg(MultipartFile img) {
        //获取当前日期
        Date now = new Date();
        // 设定日期格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //定义上传图片的文件命名空间
        String fileSpace = "/wxbk";
        // 保存到数据库的相对路径
        String uploadPathDB = "/" + dateFormat.format(now) + "/Imgs";
        // 上传图片
        return fileUntil.uploadResources(img, fileSpace, uploadPathDB);
    }

    @Override
    public int insertFile(List<UniversityPicture> universityPictureList) {
        return matterMapper.insertFile(universityPictureList);
    }

    @Override
    public int insertFile2(List<UniversityPicture2> universityPictureList2) {
        return universityMapper.insertUniversityFile(universityPictureList2);
    }
}
