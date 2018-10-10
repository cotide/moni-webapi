package com.gold.moni.webapi.controller.v1;


import com.gold.moni.helper.common.qr.QRCodeUtil;
import com.gold.moni.helper.excel.ExcelsUtils;
import com.gold.moni.helper.exception.BusinessException;
import com.gold.moni.webapi.config.attr.Download;
import com.gold.moni.webapi.config.attr.IgnoreRequestFilter;
import com.gold.moni.webapi.controller.base.AuthApiController;
import com.gold.moni.webapi.controller.base.BaseApiController;
import com.gold.moni.webapi.filter.jwt.attr.ActionPowerFilter;
import com.gold.moni.webapi.filter.jwt.attr.PowerFilter;
import com.google.zxing.WriterException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static javax.management.timer.Timer.ONE_DAY;

@RestController
@RequestMapping("/api/v1.0/data")
@Api(description = "公用模块")
public class CommonController extends BaseApiController {


    @ApiOperation(value = "没有任何返回")
    @RequestMapping(value = "getNotReturn",method = RequestMethod.GET)
    public void  getNotReturn(){

    }


    @ApiOperation(value = "抛出异常")
    @RequestMapping(value = "getException",method = RequestMethod.GET)
    public void getException(){
        throw  new BusinessException("业务异常了... >_<");
    }


    @ApiOperation(value = "获取自定义格式")
    @IgnoreRequestFilter
    @RequestMapping(value = "getMyResult",method = RequestMethod.GET)
    public String getMyResult(){
        return "Hello World";
    }


    @ApiOperation(value = "Excel下载")
    @Download
    @RequestMapping(value = "downloadExcel",method = RequestMethod.GET)
    public void downloadExcel(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException,
            IllegalAccessException,
            InstantiationException,
            ClassNotFoundException {


        List<String> list = new ArrayList<>();
        list.add("Tommy");
        list.add("Ada");
        list.add("Ben");
        list.add("Toby");
        ExcelsUtils.createTable(
                list,
                String.class,
                "姓名",
                new String[]{"姓名"},
                response);
    }




}
