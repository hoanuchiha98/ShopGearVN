package com.uchiha.gearshop.controller;

import com.uchiha.gearshop.common.dto.model.ManufacturerDto;
import com.uchiha.gearshop.common.dto.response.Response;
import com.uchiha.gearshop.common.util.Constants;
import com.uchiha.gearshop.controller.request.ManufacturerRequest;
import com.uchiha.gearshop.service.ManufacturerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/manufacturer")
@Api(tags = {"manufacturers"})
public class ManufacturerController {
    @Autowired
    ManufacturerService manufacturerService;

//    @ApiOperation("Lấy danh sách danh muc")
//    @GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//    public Response findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
//                            @RequestParam(value = "size", defaultValue = "3") Integer size) {
//        PageRequest request = PageRequest.of(page - 1, size);
//        Page<CategoryDto> categoryDtoPage = categoryService.findAll(request);
//        if (categoryDtoPage.isEmpty()){
//            return Response.badRequest().setErrors(Constants.ERR_MSG_NOT_FOUND);
//        }
//        return Response.ok().setPayload(categoryDtoPage);
//    }

    @ApiOperation("Tìm hãng theo id")
    @GetMapping(value = "/{manufacturerId}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Response showOne(@PathVariable("manufacturerId") int manufacturerId) {

        ManufacturerDto manufacturerDto = manufacturerService.findByManufacturerId(manufacturerId);
        if (manufacturerDto == null) {
            return Response.badRequest().setErrors(Constants.ERR_MSG_NOT_FOUND);
        }
        return Response.ok().setPayload(manufacturerDto);
    }

    @ApiOperation("Thêm mới hãng")
    @PostMapping(value = "/create", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Response create(@RequestBody ManufacturerRequest manufacturerRequest) {
        Response response = null;
        try {
            ManufacturerDto manufacturerDto = new ManufacturerDto();
            manufacturerDto.setName(manufacturerRequest.getName())
                    .setDescription(manufacturerRequest.getDescription())
                    .setPhoto(manufacturerRequest.getPhoto());
            ManufacturerDto manufacturer = manufacturerService.create(manufacturerDto);
            response = Response.ok().setPayload(manufacturer);
        } catch (Exception e) {
            response = Response.badRequest().setErrors(Constants.ERR_CODE_BAD_REQUEST);
        }
        return response;
    }

    @ApiOperation("Cập nhật hãng")
    @PutMapping(value = "/update/{manufacturerId}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Response create(@PathVariable("manufacturerId") Integer manufacturerId, @RequestBody ManufacturerRequest manufacturerRequest) {
        Response response = null;
        try {
            ManufacturerDto manufacturerDto = new ManufacturerDto();
            manufacturerDto.setName(manufacturerRequest.getName())
                    .setDescription(manufacturerRequest.getDescription())
                    .setPhoto(manufacturerRequest.getPhoto())
                    .setId(manufacturerId)
            ;
            ManufacturerDto category = manufacturerService.update(manufacturerDto);
            response = Response.ok().setPayload(category);
        } catch (Exception e) {
            response = Response.badRequest().setErrors(Constants.ERR_CODE_BAD_REQUEST);
        }
        return response;
    }

    @ApiOperation(value = "Xoá hãng")
    @DeleteMapping("/{categoryId}")
    public Response delete(@PathVariable("manufacturerId") Integer manufacturerId) {
        Response response = null;
        try {
            manufacturerService.delete(manufacturerId);
            response = Response.ok();
        } catch (Exception e) {
            System.out.println("Manufacturer::Error" + e.toString());
            response = Response.badRequest().setErrors(Constants.ERR_CODE_BAD_REQUEST);
        }
        return response;
    }
}
