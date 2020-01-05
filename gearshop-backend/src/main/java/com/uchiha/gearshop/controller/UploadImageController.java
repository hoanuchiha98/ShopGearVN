package com.uchiha.gearshop.controller;

import com.uchiha.gearshop.common.dto.response.Response;
import com.uchiha.gearshop.common.enums.ImageType;
import com.uchiha.gearshop.common.util.Constants;
import com.uchiha.gearshop.service.UploadImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@Api(tags = {"upload"})
@RequestMapping(value = "/api")
public class UploadImageController {
    @Autowired
    UploadImageService uploadImageService;

    @ApiOperation(value = "Upload ảnh")
    @PostMapping(value = "/upload-image", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public @ResponseBody
    Response<?> uploadImage(@RequestParam("type") ImageType type, @RequestParam("file") MultipartFile file) {
        Response<?> response = new Response<>();
        String str_type = type.toString();
        try {
            String image = uploadImageService.uploadImage(str_type, file);
            response = Response.ok().setPayload(image);
        } catch (Exception e) {
            response = Response.badRequest().setErrors(Constants.MSG_TEMP);
        }
        return response;
    }
}
