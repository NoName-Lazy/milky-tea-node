package com.example.milky_tea_node.utils;

import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtils {
    public static File compressImage(File originalFile, int maxWidthOrHeight) throws IOException {
        // 读取原始图片
        BufferedImage originalImage = ImageIO.read(originalFile);

        // 计算压缩比例
        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();
        double scale = Math.min((double) maxWidthOrHeight / originalWidth,
                (double) maxWidthOrHeight / originalHeight);

        // 计算新尺寸
        int newWidth = (int) (originalWidth * scale);
        int newHeight = (int) (originalHeight * scale);

        // 创建新的图片对象
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g2d.dispose();

        // 创建临时文件存储压缩后的图片
        File compressedFile = File.createTempFile("compressed-", ".jpg");
        ImageIO.write(resizedImage, "jpg", compressedFile);

        return compressedFile;
    }
}