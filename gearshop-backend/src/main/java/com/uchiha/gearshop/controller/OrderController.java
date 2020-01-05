package com.uchiha.gearshop.controller;

import com.uchiha.gearshop.common.dto.model.CategoryDto;
import com.uchiha.gearshop.common.dto.response.Response;
import com.uchiha.gearshop.common.util.Constants;
import com.uchiha.gearshop.controller.request.OrderRequest;
import com.uchiha.gearshop.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
@Api(tags = {"order"})
public class OrderController {
    @Autowired
    OrderService orderService;

    @ApiOperation("Đặt hàng")
    @PostMapping(value = "/order", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Response order(@RequestBody OrderRequest orderRequest) {
        try {
            boolean result = orderService.Order(orderRequest);
            System.out.println("Result===============" + result);
            return (result)?Response.ok():Response.badRequest().setErrors(Constants.MSG_TEMP);
        }
        catch (Exception e){
            return Response.badRequest().setErrors(Constants.ERR_MSG_BAD_REQUEST);
        }
    }
}
