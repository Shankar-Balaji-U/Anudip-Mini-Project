package com.anudipgroupproject.socialize.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

import static com.anudipgroupproject.socialize.constants.Configurations.MEDIA_DIR;


public class MediaManager {
	private final Path root;
	
	public MediaManager() {
		try {
			this.root = Paths.get(MEDIA_DIR);
			Files.createDirectories(this.root);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize the media directory!");
		}
	}
	
	public Path save(MultipartFile file, String subdir) throws IOException {
		Path subFolder = root.resolve(subdir);
		Files.createDirectories(subFolder);
		
		Path targetPath = subFolder.resolve(file.getOriginalFilename());
		
		System.out.println(targetPath);
		Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
		return targetPath;
	}
	
	public File load(String path) throws IOException {
        Path filePath = Paths.get(path);
        
        if (Files.exists(filePath)) {
            // Create a new File object from the loaded file
        	File fileObject = filePath.toFile();
            return fileObject;
        } else {
            throw new IOException("File does not exist: " + filePath);
        }
    }
	
	public static File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
	    File file = new File(multipartFile.getOriginalFilename());
	    
	    try (InputStream inputStream = multipartFile.getInputStream();
	         OutputStream outputStream = new FileOutputStream(file)) {
	        int bytesRead;
	        byte[] buffer = new byte[4096];
	        
	        while ((bytesRead = inputStream.read(buffer)) != -1) {
	            outputStream.write(buffer, 0, bytesRead);
	        }
	    } catch (IOException e) {
	    	
	    }
	    
	    return file;
	}
} 