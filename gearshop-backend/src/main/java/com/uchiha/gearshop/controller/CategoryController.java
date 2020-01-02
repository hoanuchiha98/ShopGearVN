package com.uchiha.gearshop.controller;

import com.uchiha.gearshop.common.dto.model.CategoryDto;
import com.uchiha.gearshop.common.dto.response.Response;
import com.uchiha.gearshop.common.util.Constants;
import com.uchiha.gearshop.controller.request.CategoryRequest;
import com.uchiha.gearshop.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/category")
@Api(tags = {"categories"})
public class CategoryController {
    @Autowired
    CategoryService categoryService;

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

    @ApiOperation("Tìm danh mục theo id")
    @GetMapping(value = "/{categoryId}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Response showOne(@PathVariable("categoryId") int categoryId) {

        CategoryDto categoryDto = categoryService.findByCategoryType(categoryId);
        if (categoryDto == null) {
            return Response.badRequest().setErrors(Constants.ERR_MSG_NOT_FOUND);
        }
        return Response.ok().setPayload(categoryDto);
    }

    @ApiOperation("Thêm danh mục")
    @PostMapping(value = "/create", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Response create(@RequestBody CategoryRequest categoryRequest) {
        Response response = null;
        try {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(categoryRequest.getName())
                    .setDescription(categoryRequest.getDescription())
                    .setPhoto(categoryRequest.getPhoto());
            CategoryDto category = categoryService.create(categoryDto);
            response = Response.ok().setPayload(category);
        } catch (Exception e) {
            response = Response.badRequest().setErrors(Constants.ERR_CODE_BAD_REQUEST);
        }
        return response;
    }

    @ApiOperation("Cập nhật danh mục")
    @PutMapping(value = "/update/{categoryId}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Response create(@PathVariable("categoryId") Integer categoryId, @RequestBody CategoryRequest categoryRequest) {
        Response response = null;
        try {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(categoryRequest.getName())
                    .setDescription(categoryRequest.getDescription())
                    .setPhoto(categoryRequest.getPhoto())
                    .setId(categoryId)
            ;
            CategoryDto category = categoryService.update(categoryDto);
            response = Response.ok().setPayload(category);
        } catch (Exception e) {
            response = Response.badRequest().setErrors(Constants.ERR_CODE_BAD_REQUEST);
        }
        return response;
    }

    @ApiOperation(value = "Xoá danh mục")
    @DeleteMapping("/{categoryId}")
    public Response delete(@PathVariable("categoryId") Integer categoryId) {
        Response response = null;
        try {
            categoryService.delete(categoryId);
            response = Response.ok();
        } catch (Exception e) {
            System.out.println("Category::Error" + e.toString());
            response = Response.badRequest().setErrors(Constants.ERR_CODE_BAD_REQUEST);
        }
        return response;
    }
}
