package com.project.my.util.S3;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AwsS3Service {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    public String uploadFile(List<MultipartFile> multipartFiles){
        List<String> fileNameList = new ArrayList<>();

        // forEach 구문을 통해 multipartFiles 리스트로 넘어온 파일들을 순차적으로 fileNameList 에 추가
        multipartFiles.forEach(file -> {
          // 파일명 난수화
          String fileName = createFileName(file.getOriginalFilename());
          //String fileName = file.getOriginalFilename();
          ObjectMetadata objectMetadata = new ObjectMetadata();
          objectMetadata.setContentLength(file.getSize());
          objectMetadata.setContentType(file.getContentType());

          try(InputStream inputStream = file.getInputStream()){
              amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                      .withCannedAcl(CannedAccessControlList.PublicRead));
          } catch (IOException e){
              System.out.println("파일 업로드 실패");
          }
          fileNameList.add(fileName);

        });
        return bucket;
    }

    // 먼저 파일 업로드시, 파일명을 난수화하기 위해 UUID 를 활용하여 난수를 돌린다.
    public String createFileName(String fileName){
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    // file 형식이 잘못된 경우를 확인하기 위해 만들어진 로직이며, 파일 타입과 상관없이 업로드할 수 있게 하기위해, "."의 존재 유무만 판단하였습니다.
    private String getFileExtension(String fileName){
      try{
          return fileName.substring(fileName.lastIndexOf("."));
      } catch (StringIndexOutOfBoundsException e){
         
      }
      return fileName;
    }

    public void deleteFile(String fileName){
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
        System.out.println(bucket);
    }
}