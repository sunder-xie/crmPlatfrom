package com.kintiger.platform.framework.util;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.kintiger.platform.framework.annotations.PermissionSearch;

/**
 * File 工具类
 * 
 * @author
 * 
 */
public class FileUtil {

	private static final Logger logger = Logger.getLogger(FileUtil.class);

	private static final int BUFFER_SIZE = 16 * 1024;

	public static boolean saveFile(String path, String content, boolean append) {
		boolean flag = false;
		OutputStream out = null;
		try {
			out = new BufferedOutputStream(new FileOutputStream(path, append),
					BUFFER_SIZE);
			out.write(content.getBytes("GBK"));
			flag = true;
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}

		return flag;
	}

	/**
	 * 文件另存为
	 * 
	 * @param source
	 *            源文件
	 * @param target
	 *            目标文件
	 * @return 保存是否成功
	 */
	public static boolean saveAsFile(File source, File target) {
		boolean flag = false;
		InputStream in = null;
		OutputStream out = null;
		//判断文件类型，如果是图片文件，则压缩大小
				String path=target.getAbsolutePath();
				String fileType=StringUtils.substring(path,StringUtils.lastIndexOf(path, '.')).toUpperCase();
				String fileTypeSoure=StringUtils.substring(path,StringUtils.lastIndexOf(path, '.'));
				String[] typeStr=fileTypeSoure.split("[.]");
				if(".PNG".equals(fileType)||".JPG".equals(fileType)||".JPEG".equals(fileType)
						||".GIF".equals(fileType)||".BMP".equals(fileType)){
					//处理图片
					BufferedImage input;
					BufferedImage image = null;
					try {
						input = ImageIO.read(source);
						if(input.getHeight()>500||input.getWidth()>800){
						    image= compressImage(input);
						}else{
							image=input;
						}
						ImageIO.write(image, typeStr[1], target);  
						flag = true;
						System.out.println("----图片处理成功----");
					} catch (IOException e) {
						logger.error("图片处理有误!",e);
					}
					
				}else{
		try {
			in = new BufferedInputStream(new FileInputStream(source),
					BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(target),
					BUFFER_SIZE);

			byte[] buffer = new byte[BUFFER_SIZE];
			int len;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}

			flag = true;
		} catch (Exception e) {
			logger.error("文件另存为失败！", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}
				}
		return flag;
	}

	public static String readFile(String filePath) {
		StringBuffer fileContent = new StringBuffer();
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			isr = new InputStreamReader(new FileInputStream(filePath), "GBK");
			br = new BufferedReader(isr);

			String tempStr = br.readLine();
			while (tempStr != null) {
				fileContent.append(tempStr);
				tempStr = br.readLine();
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if (isr != null) {
				try {
					isr.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					logger.error(e);
				}
			}
		}

		return fileContent.toString();
	}

	/**
	 * 获取文件类型名 (.txt)
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileExtention(String fileName) {
		if (StringUtils.isEmpty(fileName)) {
			return null;
		}

		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 获取文件名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileName(String fileName) {
		if (StringUtils.isEmpty(fileName)) {
			return null;
		}

		if (fileName.lastIndexOf(".") == -1) {
			return fileName;
		} else {
			return fileName.substring(0, fileName.lastIndexOf("."));
		}
	}

	/**
	 * 获取文件类型名 (txt)
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileSuffix(String fileName) {
		if (StringUtils.isEmpty(fileName)) {
			return null;
		}

		if (fileName.lastIndexOf(".") == -1) {
			return "";
		} else {
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		}
	}

	/**
	 * 修改文件名
	 * 
	 * @param source
	 * @param targetName
	 * @return
	 */
	public static boolean modifyFileNameTo(File source, String targetName) {
		boolean flag = false;
		try {
			source.renameTo(new File(targetName));
			flag = true;
		} catch (Exception e) {
			logger.error(e);
		}
		return flag;
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean exists(String fileName) {
		File source = new File(fileName);
		return source.exists();
	}

	/**
	 * 文件下载
	 * 
	 * @param file
	 * @param fileName
	 * @param response
	 * @return6
	 */
	@SuppressWarnings("finally")
	public static boolean downFile(File file, String fileName,
			HttpServletResponse response) {

		FileInputStream in = null;
		OutputStream out = null;
		try {
			fileName = new String(fileName.getBytes("GBK"), "iso-8859-1");
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment;filename=\""
					+ fileName);
			in = new FileInputStream(file);
			out = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			response.flushBuffer();
		} catch (Exception ex) {
			return false;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (final Exception e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (final Exception e) {
				}
			}
			return true;
		}
	}
	 /**
     * 图片压缩
     * @param sourceImage 要压缩的图片
     * @return 压缩后的图片
     * @throws IOException 图片读写异常
     */
    public static BufferedImage compressImage(BufferedImage sourceImage) throws IOException{
        if(sourceImage==null) throw new NullPointerException("空图片");
        BufferedImage cutedImage=null;
        //图片自动裁剪
        cutedImage=cutImageAuto(sourceImage);
        return cutedImage;
    }
    /**
     * 图片自动裁剪
     * @param image 要裁剪的图片
     * @return 裁剪后的图片
     */
    public static BufferedImage cutImageAuto(BufferedImage image){
        Rectangle area=getCutAreaAuto(image);
        return image.getSubimage(area.x, area.y,area.width, area.height);
    }

    /**
     * 获得裁剪图片保留区域
     * @param image 要裁剪的图片
     * @return 保留区域
     */
    private static Rectangle getCutAreaAuto(BufferedImage image){
        if(image==null) throw new NullPointerException("图片为空");
        int width=image.getWidth();
        int height=image.getHeight();
        int startX=width;
        int startY=height;
        int endX=0;
        int endY=0;
        int []pixel=new int[width*height];

        pixel=image.getRGB(0, 0, width, height, pixel, 0, width);
        for(int i=0;i<pixel.length;i++){
                int w=i%width;
                int h=i/width;
                startX=(w<startX)?w:startX;
                startY=(h<startY)?h:startY;
                endX=(w>endX)?w:endX;
                endY=(h>endY)?h:endY;
        }
        if(startX>endX || startY>endY){
            startX=startY=0;
            endX=width;
            endY=height;
        }
        return new Rectangle(startX, startY, endX-startX, endY-startY);
    }
}
