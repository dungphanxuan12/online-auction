package com.auction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.auction.converter.ProductConverter;
import com.auction.dto.ProductDTO;
import com.auction.service.IProductService;
import com.auction.utils.FileStorageService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private IProductService productService;

	@Autowired
	private FileStorageService fileStorageService;

	/**
	 * 
	 * @param product
	 * @return
	 */
	@PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, 
								produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addProduct(@RequestBody ProductDTO product) {
		try {
			productService.save(new ProductConverter().convertToEntity(product));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
								  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
		try {
			String fileName = fileStorageService.storageFile(file);
			System.out.println(fileName);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/uploads/")
					.path(fileName)
					.toUriString();
			System.out.println("File" + fileDownloadUri);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
