package com.uchiha.gearshop.controller;

import com.uchiha.gearshop.common.dto.model.ProductDto;
import com.uchiha.gearshop.common.dto.response.Response;
import com.uchiha.gearshop.common.util.Constants;
import com.uchiha.gearshop.controller.request.ProductRequest;
import com.uchiha.gearshop.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/products")
@Api(tags = {"products"})
public class ProductController {
    @Autowired
    ProductService productService;

    @ApiOperation("Lấy danh sách sản phẩm")
    @GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Response findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                            @RequestParam(value = "size", defaultValue = "3") Integer size) {
        PageRequest request = PageRequest.of(page - 1, size);
        Page<ProductDto> productDtoPage = productService.findAll(request);
        if (productDtoPage.isEmpty()) {
            return Response.badRequest().setErrors(Constants.ERR_MSG_NOT_FOUND);
        }
        return Response.ok().setPayload(productDtoPage);
    }

    @ApiOperation("Tìm sản phẩm id")
    @GetMapping(value = "/{productId}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Response showOne(@PathVariable("productId") int productId) {

        ProductDto productInfo = productService.findOne(productId);
        if (productInfo == null) {
            return Response.badRequest().setErrors(Constants.ERR_MSG_NOT_FOUND);
        }
        return Response.ok().setPayload(productInfo);
    }

    @ApiOperation("Tìm sản phẩm theo danh mục và hãng")
    @GetMapping(value = "/all/category-manufacturer/", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Response findAllByCategoryIdAndManufacturerId(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                         @RequestParam(value = "size", defaultValue = "3") Integer size,
                                                         @RequestParam("categoryId") Integer categoryId, @RequestParam("manufacturerId") Integer manufacturerId) {
        PageRequest request = PageRequest.of(page - 1, size);
        Page<ProductDto> productDtoPage = productService.findAllByCategoryIdAndManufacturerId(categoryId, manufacturerId, request);
        if (productDtoPage.isEmpty()) {
            return Response.badRequest().setErrors(Constants.ERR_MSG_NOT_FOUND);
        }
        return Response.ok().setPayload(productDtoPage);
    }

    @ApiOperation("Thêm sản phẩm")
    @PostMapping(value = "/create", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Response create(@RequestBody ProductRequest productRequest) {
        Response response = null;
        try {
            ProductDto productDto = new ProductDto();
            productDto.setName(productRequest.getName())
                    .setDescription(productRequest.getDescription())
                    // mậc định để là kích hoạt
                    .setAvailable(1)
                    .setCategory_id(productRequest.getCategory_id())
                    .setManufacturer_id(productRequest.getManufacturer_id())
                    .setDescription(productRequest.getDescription())
                    .setHot(productRequest.getHot())
                    .setIs_new(productRequest.getIs_new())
                    .setSpecial(productRequest.getSpecial())
                    .setName(productRequest.getName())
                    .setPrice(productRequest.getPrice())
                    .setPrice_old(productRequest.getPrice_old())
                    .setStock(productRequest.getStock())
                    .setPhoto(productRequest.getPhoto());
            System.out.println("Product::Dto" + productDto);
            ProductDto product = productService.create(productDto);
            response = Response.ok().setPayload(product);
        } catch (Exception e) {
            System.out.println("Product::Error" + e.toString());
            response = Response.badRequest().setErrors(Constants.ERR_CODE_BAD_REQUEST);
        }
        return response;
    }

    @ApiOperation("Cập nhật sản phẩm")
    @PutMapping(value = "/update/{productId}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Response create(@PathVariable("productId") Integer productId, @RequestBody ProductRequest productRequest) {
        Response response = null;
        try {
            ProductDto productDto = new ProductDto();
            productDto.setName(productRequest.getName())
                    .setDescription(productRequest.getDescription())
                    // mậc định để là kích hoạt
                    .setAvailable(1)
                    .setCategory_id(productRequest.getCategory_id())
                    .setManufacturer_id(productRequest.getManufacturer_id())
                    .setDescription(productRequest.getDescription())
                    .setHot(productRequest.getHot())
                    .setIs_new(productRequest.getIs_new())
                    .setSpecial(productRequest.getSpecial())
                    .setName(productRequest.getName())
                    .setPrice(productRequest.getPrice())
                    .setPrice_old(productRequest.getPrice_old())
                    .setStock(productRequest.getStock())
                    .setPhoto(productRequest.getPhoto())
                    .setId(productId)
            ;
            ProductDto product = productService.update(productDto);
            response = Response.ok().setPayload(product);
        } catch (Exception e) {
            response = Response.badRequest().setErrors(Constants.ERR_CODE_BAD_REQUEST);
        }
        return response;
    }

    @ApiOperation(value = "Xoá sản phẩm")
    @DeleteMapping("/{productId}")
    public Response delete(@PathVariable("productId") Integer productId) {
        Response response = null;
        try {
            productService.delete(productId);
            response = Response.ok();
        } catch (Exception e) {
            System.out.println("Product::Error" + e.toString());
            response = Response.badRequest().setErrors(Constants.ERR_CODE_BAD_REQUEST);
        }
        return response;
    }
}
