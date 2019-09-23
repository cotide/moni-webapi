package com.gold.moni.webapi.helper.common.qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class QRCodeUtil {

    /**
     * 二维码参数
     */
    private static final Map<EncodeHintType, Object> hints = new HashMap();

    static {
        // 字符编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        // 容错等级 L、M、Q、H 其中 L 为最低, H 为最高
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 二维码与图片边距
        hints.put(EncodeHintType.MARGIN, 1);
    }

    /**
     * 返回一个 BufferedImage 对象
     * @param content 二维码内容
     * @param width   宽
     * @param height  高
     */
    public static BufferedImage toBufferedImage(String content, int width, int height) throws WriterException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }


    public static BufferedImage mergeImage(
            BufferedImage img1,
            BufferedImage img2,
            int startX,
            int startY) {
        int w1 = img1.getWidth();
        int h1 = img1.getHeight();
        int w2 = img2.getWidth();
        int h2 = img2.getHeight();

        // 从图片中读取RGB
        int[] imageArrayOne = new int[w1 * h1];
        imageArrayOne = img1.getRGB(0, 0, w1, h1, imageArrayOne, 0, w1);
        int[] imageArrayTwo = new int[w2 * h2];
        imageArrayTwo = img2.getRGB(0, 0, w2, h2, imageArrayTwo, 0, w2);

        // 生成新图片
        BufferedImage destImage = new BufferedImage(w1, h1, BufferedImage.TYPE_INT_RGB);
        destImage.setRGB(0, 0, w1, h1, imageArrayOne, 0, w1);
        destImage.setRGB(startX, startY, w2, h2, imageArrayTwo, 0, w2);
        return destImage;
    }

}
