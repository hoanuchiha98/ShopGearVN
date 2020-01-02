package com.uchiha.gearshop.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class FileRequestDTO {
    private MultipartFile image;
}
