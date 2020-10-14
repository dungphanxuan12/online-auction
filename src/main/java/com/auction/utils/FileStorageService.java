package com.auction.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.auction.config.FileStorageConfigure;
import com.auction.exception.FileStorageException;

@Component
public class FileStorageService {

	private final Path storageLocattion;

	@Autowired
	public FileStorageService(FileStorageConfigure storageConfigure) {
		this.storageLocattion = Paths.get(storageConfigure.getUploadDir()).toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.storageLocattion);
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	// function to store the file
	public String storageFile(MultipartFile file) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			Path targetLocation = this.storageLocattion.resolve(filename);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			return filename;
		} catch (Exception e) {
			throw new FileStorageException("Could not create the directory to upload");
		}
	}

	// function to load the file
	public Resource loadFileAsResource(String fileName) throws FileNotFoundException {
		try {
			Path filePath = this.storageLocattion.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new FileNotFoundException("File not found " + fileName);
			}
		} catch (MalformedURLException | FileNotFoundException ex) {
			throw new FileNotFoundException("File not found " + fileName);
		}
	}

}
