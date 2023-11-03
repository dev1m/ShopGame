package net.dev1m.shopgame.service.imp;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileServiceImp {
    boolean savefile(MultipartFile multipartFile);
    Resource loadFile(String fileName);
}
