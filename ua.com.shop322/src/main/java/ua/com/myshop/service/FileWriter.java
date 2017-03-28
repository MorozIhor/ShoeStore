package ua.com.myshop.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileWriter {

	enum Folder{
		BRAND, COMMODITIES
	}
	
	boolean write(Folder folder, MultipartFile file, Integer id);
}
