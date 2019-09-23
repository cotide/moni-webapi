package com.gold.moni.webapi.controller.v1;

import com.gold.moni.webapi.helper.common.qr.QRCodeUtil;
import com.gold.moni.webapi.config.attr.Download;
import com.gold.moni.webapi.controller.base.BaseApiController;
import com.google.zxing.WriterException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static javax.management.timer.Timer.ONE_DAY;


/**
 * @author cotide
 */
@RestController
@RequestMapping("/api/v1.0/qr")
@Api(description = "二维码模块")
public class QrController  extends BaseApiController {


    @Download
    @Cacheable(value = "qr_getGithubQR",key = "#userName")
    @Scheduled(fixedRate = ONE_DAY )
    @ApiOperation(value = "获取Github二维码")
    @RequestMapping(
            value = "getGithubQR",
            method = RequestMethod.GET,
            produces = MediaType.IMAGE_PNG_VALUE
    )
    public byte[] getGithubQR(String userName)
            throws IOException, WriterException {

        BufferedImage in = ImageIO.read(getClass().getResourceAsStream("/static/img/rq_bg.png"));
        if(in==null)
        {
            throw new NullPointerException("未找到背景图");
        }
        BufferedImage qrImg = QRCodeUtil.toBufferedImage("https://github.com/"+userName,240,240);
        BufferedImage outPut = QRCodeUtil.mergeImage(in,qrImg,200,170);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            ImageIO.write(outPut, "png", output);
            output.flush();
            return output.toByteArray();
        }finally {
            output.close();
        }
    }

}
