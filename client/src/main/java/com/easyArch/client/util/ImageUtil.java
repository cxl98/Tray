package com.easyArch.client.util;

import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import org.apache.commons.lang.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtil {
    private static final String PATH="1111.png";
    public static File image(File file){
        try {
           return image(file,PATH,400,400);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static File image(File imageFile, String newPath, int width, int height) throws IOException {
        if (imageFile != null && !imageFile.canRead())
            return null;
        BufferedImage bufferedImage = ImageIO.read(imageFile);
        if (null == bufferedImage)
            return null;

        return zoomImageUtils(imageFile, newPath, bufferedImage, width, height);
    }

    private static File zoomImageUtils(File imageFile, String newPath, BufferedImage bufferedImage, int width, int height)
            throws IOException {

        String suffix = StringUtils.substringAfterLast(imageFile.getName(), ".");
        File file = new File(newPath);
        // 处理 png 背景变黑的问题
        if (suffix != null && (suffix.trim().toLowerCase().endsWith("png") || suffix.trim().toLowerCase().endsWith("gif"))) {
            BufferedImage to = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = to.createGraphics();
            to = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
            g2d.dispose();

            g2d = to.createGraphics();
            Image from = bufferedImage.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
            g2d.drawImage(from, 0, 0, null);
            g2d.dispose();
            ImageIO.write(to, suffix,file);
        } else {
            BufferedImage newImage = new BufferedImage(width, height, bufferedImage.getType());
            Graphics g = newImage.getGraphics();
            g.drawImage(bufferedImage, 0, 0, width, height, null);
            g.dispose();
            ImageIO.write(newImage, suffix, file);
        }
        return file;
    }
    /**
     * 转化为灰度图
     * @throws IOException
     */
    public static javafx.scene.image.Image convertToGray(javafx.scene.image.Image image) {
        PixelReader pixelReader = image.getPixelReader();
        WritableImage grayImage = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelWriter pixelWriter = grayImage.getPixelWriter();

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = pixelReader.getColor(x, y);
                color = color.grayscale();
                pixelWriter.setColor(x, y, color);
            }
        }
        return grayImage;
    }

    public static void main(String[] args) {
        ImageUtil imageUtil = new ImageUtil();
        File file = new File("/home/cxl/cxl/test-demo/test/src/main/resources/331077379.png");
        try {
            imageUtil.image(file,"1111.png",400,400);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
