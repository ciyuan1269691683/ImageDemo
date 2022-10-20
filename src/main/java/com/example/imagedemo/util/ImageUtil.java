package com.example.imagedemo.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;

/**
 * @author xwj
 * description:
 */
public class ImageUtil {

    /**
     * 遍历文件夹文件
     *
     * @param srcPath    原图路径
     * @param destPath   新图路径
     * @param formatName 图片格式，支持bmp|gif|jpg|jpeg|png
     * @return
     */
    public static void traverseFile(String srcPath, String destPath, String formatToChange, String formatName) {
        boolean flag;
        File file = new File(srcPath);
        if (file.isFile()) {
            modifyImageFormat(srcPath, destPath + "." + formatName, formatName);
            System.out.println("转换单张图片,格式为" + formatName);
        } else {
            File[] files = file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    if (pathname.getAbsolutePath().endsWith(formatToChange)) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });

            if (null != files && files.length > 0) {
                for (File file1 : files) {
                    String name = file1.toString().substring(file1.toString().lastIndexOf("\\")
                            , file1.toString().lastIndexOf("."));
                    flag = modifyImageFormat(file1.toString(), destPath + name + "." + formatName, formatName);
                    if (flag) {
                        System.out.println(file1.toString() + "转换成功!");
                    } else {
                        System.out.println(file1.toString() + "转换失败");
                    }
                }
            }
        }
    }

    /**
     * 修改原图的文件格式
     * @param srcPath 原图路径
     * @param destPath 新图路径
     * @param formatName 图片格式，支持bmp|gif|jpg|jpeg|png
     * @return true/false
     */
    public static boolean modifyImageFormat(String srcPath, String destPath, String formatName) {
        boolean flag = false;
        try {
            BufferedImage bufferedImg = ImageIO.read(new File(srcPath));
            flag = ImageIO.write(bufferedImg, formatName, new File(destPath));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return flag;
    }

    public static void main(String[] args) {
//        traverseFile("D:\\图片","D:\\123\\","jpg","png");//转换某一文件夹的图片
        traverseFile("D:\\工作资料\\MercadoLibre\\1666156765947.webp",
                "D:\\新图片", "webp", "png");//转换单张图片
    }



}
