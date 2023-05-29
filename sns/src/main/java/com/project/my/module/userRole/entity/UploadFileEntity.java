package com.project.my.module.userRole.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UploadFileEntity {
    private Long id;
    private String uploadFileName;
    private String storeFileUrl;
    
    public UploadFileEntity(String uploadFileName, String storeFileUrl) {
        this.uploadFileName = uploadFileName;
        this.storeFileUrl = storeFileUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public void setStoreFileUrl(String storeFileUrl) {
        this.storeFileUrl = storeFileUrl;
    }   
 
}
