package net.dev1m.shopgame.service;

import net.dev1m.shopgame.service.imp.FileServiceImp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService implements FileServiceImp {
    @Value("${fileUpLoad.root-path}")
    private  String rootPath;
    private Path root;

   public void init(){
       try{
           root = Paths.get(rootPath);
           if(Files.notExists(root)){
               Files.createDirectories(root);
           }
       }catch(Exception e){
           System.out.println("Error Create Folder Root: " +e.getMessage());
       }
   }

    @Override
    public boolean savefile(MultipartFile multipartFile) {
        try {
            init();
            try (InputStream inputStream = multipartFile.getInputStream()) {
                Files.copy(inputStream, root.resolve(multipartFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            }catch (Exception e){
                System.out.println("Error inputStream " +e.getMessage());
            }
            return true;
        }catch (Exception e){
            System.out.println("Error Save File: " +e.getMessage());
            return  false;
        }
    }

    @Override
    public Resource loadFile(String fileName) {
       try{
           init();
           Path file = root.resolve(fileName);
           Resource resource = new UrlResource(file.toUri());
           if(resource.exists() || resource.isReadable()){
               return resource;
           }
       }catch (Exception e){
           System.out.println("Error Loadfile: " +e.getMessage());
       }
        return null;
    }
}

