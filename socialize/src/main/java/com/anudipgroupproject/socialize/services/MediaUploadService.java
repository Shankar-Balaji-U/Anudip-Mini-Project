package com.anudipgroupproject.socialize.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static com.anudipgroupproject.socialize.constants.Configurations.MEDIA_DIR;

@Service
public class MediaUploadService {
	public String subdir = "";
	private final Path root = Paths.get(MEDIA_DIR + subdir);
	
	MediaUploadService() {
		try {
			Files.createDirectories(root);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize the media directory!");
		}
	}
	
	public Path save(MultipartFile file, String subdir) throws IOException {
		Path targetPath = root.resolve(subdir + "/" + file.getOriginalFilename());
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
}























//
//@Service
//public class FilesStorageServiceImpl implements FilesStorageService {
//
//  private final Path root = Paths.get("uploads");
//
//  @Override
//  public void init() {
//    try {
//      Files.createDirectories(root);
//    } catch (IOException e) {
//      throw new RuntimeException("Could not initialize folder for upload!");
//    }
//  }
//
//  @Override
//  public String save(MultipartFile file) {
//    try {
//      Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
//    } catch (Exception e) {
////      if (e instanceof FileAlreadyExistsException) {
////        throw new RuntimeException("A file of that name already exists.");
////      }
//    }
//  }
//
//  @Override
//  public Resource load(String filename) {
//    try {
//      Path file = root.resolve(filename);
//      Resource resource = new UrlResource(file.toUri());
//
//      if (resource.exists() || resource.isReadable()) {
//        return resource;
//      } else {
//        throw new RuntimeException("Could not read the file!");
//      }
//    } catch (MalformedURLException e) {
//      throw new RuntimeException("Error: " + e.getMessage());
//    }
//  }
//
//  @Override
//  public boolean delete(String filename) {
//    try {
//      Path file = root.resolve(filename);
//      return Files.deleteIfExists(file);
//    } catch (IOException e) {
//      throw new RuntimeException("Error: " + e.getMessage());
//    }
//  }
//
//  @Override
//  public void deleteAll() {
//    FileSystemUtils.deleteRecursively(root.toFile());
//  }
//
//  @Override
//  public Stream<Path> loadAll() {
//    try {
//      return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
//    } catch (IOException e) {
//      throw new RuntimeException("Could not load the files!");
//    }
//  }
//
//}