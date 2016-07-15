package com.elixir.common.until;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;


/**
* @ClassName: FileUtil
* @Description:
* @author Jingyan
* @date 2016年3月21日 下午5:49:15
*/ 
public class FileUtil {

    private final static int BUFF_SIZE = 4096;
    public static final String FILETYPE_PDF = "pdf";
    public static final String FILETYPE_ZIP = "zip";

	public static void changeFile(String fileName, String fileType, String fromPath, String toPath){
		
		change(fileName, fromPath, toPath);
		
		/*String fileName100 = fileName.substring(fileName.lastIndexOf("."))+"_100_100."+fileType;
		change(fileName100, fromPath, toPath);
		
		String fileName300 = fileName.substring(fileName.lastIndexOf("."))+"_300_300."+fileType;
		change(fileName300, fromPath, toPath);*/
		
	}
	
	/**
	 * 文件拷贝
     * @param fileName 文件名
     * @param fromPath 原路径
     * @param toPath 目标路径
	 */
	public static void change(String fileName, String fromPath, String toPath){
        File file =new File(toPath);
        //如果文件夹不存在则创建
        if  (!file .exists() && !file .isDirectory()) {
            file.mkdirs();
        }
        File tmpFile = new File(fromPath + fileName);
		File formalFile = new File(toPath + fileName);
		if(tmpFile.exists()){
//			tmpFile.renameTo(formalFile);
			try {
				FileUtils.copyFile(tmpFile, formalFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }

    public static void copy(File fromFile, File toFile) throws IOException {
        FileUtils.copyFile(fromFile, toFile);
    }

    public static void copy(List<String> files, String toFolder) throws IOException {
        for (String file : files) {
            FileUtils.copyFileToDirectory(new File(file), new File(toFolder));
        }
    }

    public static void delete(File file) throws Exception {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File theFile : files) {
                    delete(theFile);
                }

            }
            file.delete();

		}
	}

    public static void output(File file,String fileType,String fileName,HttpServletRequest request,HttpServletResponse response) throws Exception {
        // 下载文件
        FileInputStream inputStream = null;
        ServletOutputStream outputStream = null;
        try {
            response.reset();
            //设置响应类型	PDF文件为"application/pdf"，WORD文件为："application/msword"， EXCEL文件为："application/vnd.ms-excel"。
            if("jpg".equalsIgnoreCase(fileType) || "jpeg".equalsIgnoreCase(fileType)){
                response.setContentType("image/jpeg;charset=utf-8");
            }else if("png".equalsIgnoreCase(fileType)){
                response.setContentType("image/png;charset=utf-8");
            }else if("pdf".equalsIgnoreCase(fileType)){
                response.setContentType("application/pdf;charset=utf-8");
            }else if("doc".equalsIgnoreCase(fileType) ||  "docx".equalsIgnoreCase(fileType)){
                response.setContentType("application/msword;charset=utf-8");
            }else if("xls".equalsIgnoreCase(fileType) ||  "xlsx".equalsIgnoreCase(fileType)){
                response.setContentType("application/vnd.ms-excel");
            }else if("zip".equalsIgnoreCase(fileType)){
                response.setContentType("application/zip;charset=utf-8");
            }else {
                response.setContentType("application/octet-stream;charset=utf-8");
            }


            //设置响应的文件名称,并转换成中文编码
            //returnName = URLEncoder.encode(returnName,"UTF-8");
            String returnName = response.encodeURL(new String(fileName.getBytes(),"iso8859-1"));	//保存的文件名,必须和页面编码一致,否则乱码

            //attachment作为附件下载；inline客户端机器有安装匹配程序，则直接打开；注意改变配置，清除缓存，否则可能不能看到效果
            response.addHeader("Content-Disposition",   "attachment;filename="+returnName);

            //将文件读入响应流
            inputStream = new FileInputStream(file);
            outputStream = response.getOutputStream();
            int readLength = 0;
            byte buf[] = new byte[BUFF_SIZE];
            readLength = inputStream.read(buf, 0, BUFF_SIZE);
            while (readLength != -1) {
                outputStream.write(buf, 0, readLength);
                readLength = inputStream.read(buf, 0, BUFF_SIZE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("输出文件失败.");
        } finally {
            try {
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String readFile(String path){
        File file = new File(path);
        BufferedReader reader = null;
        String laststr = "";
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return laststr;
    }

	/**
	 * @param byteArrayOutputStream 将文件内容写入ByteArrayOutputStream
	 * @param response HttpServletResponse	写入response
	 * @param returnName 返回的文件名
	 */
	public void download(ByteArrayOutputStream byteArrayOutputStream, HttpServletResponse response, String returnName) throws IOException{
		response.setContentType("application/octet-stream;charset=utf-8");
		returnName = response.encodeURL(new String(returnName.getBytes(),"iso8859-1"));			//保存的文件名,必须和页面编码一致,否则乱码
		response.setHeader("Content-Disposition", "attachment;filename=" + returnName);
		response.setContentLength(byteArrayOutputStream.size());
		ServletOutputStream outputstream = response.getOutputStream();	//取得输出流
		byteArrayOutputStream.writeTo(outputstream);					//写到输出流
		outputstream.flush();											//刷数据
		//outputstream.close(); ?
		byteArrayOutputStream.close();									//关闭
	}
}
