package com.iviui.carlife.modules.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeUtility;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * @author: ChengPan
 * @date: 2019/5/24
 * @description: Excel导出工具类
 */
public class ExcelUtil{

	private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);
			
	
	public static Workbook createWorkBook(List<Map<String, Object>> list,String[] keys,String columnNames[]) {
		Map<String,Object> formatekey = new HashMap<String,Object>();
		return createWorkBook(list, keys, columnNames, formatekey);
	}
	
	public static Workbook createWorkBook(List<Map<String, Object>> list,String[] keys,String columnNames[],Map formatekey) {
        // 创建excel工作簿
		int sheetSize=50000;
		Workbook wb = new SXSSFWorkbook(500);
        // 创建第一个sheet（页），并命名
        Sheet sheet=null;
        sheet = wb.createSheet("sheet1");
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for(int i=0;i<keys.length;i++){
            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
        }

        // 创建第一行
        Row row = sheet.createRow((int) 0);

        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();

        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());

        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_CENTER);

        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        cs2.setAlignment(CellStyle.ALIGN_CENTER);
        //设置列名
        for(int i=0;i<columnNames.length;i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(cs);
        }
        //设置每行每列的值
        for (int i = 0; i < list.size(); i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
        	Row row1 =null;
            // 创建一行，在页sheet上
        	//分sheet页算法
        	int c = i%sheetSize==0?i/sheetSize:i/sheetSize+1;
        	if(i>sheetSize && i<=(c-1)*sheetSize+sheetSize){
        		if(i==sheetSize*(c-1)+1){
        			sheet = wb.createSheet("sheet"+c);
        		}
        		row1 = sheet.createRow((int) (i-sheetSize*(c-1)-1));
        	}else{
        		row1 = sheet.createRow((int) (i+1));
        	}
             
            // 在row行上创建一个方格
            for(int j=0;j<keys.length;j++){
                Cell cell = row1.createCell(j);
                if(formatekey.containsKey(keys[j])){
                	 cell.setCellValue(list.get(i).get(keys[j]) == null?" ": timeFormate(list.get(i).get(keys[j])));
                }else{
                	 cell.setCellValue(list.get(i).get(keys[j]) == null?" ": list.get(i).get(keys[j]).toString());
                }
                cell.setCellStyle(cs2);
            }
            
        }
        return wb;
    }

	
	public static String timeFormate(Object obj){
		SimpleDateFormat f2 = new SimpleDateFormat("yyyy-MM-dd");
        //格式化日期转换为字符串
		String strDate = f2.format(obj);
		return strDate;
	}

    /**
     * 设置下载文件中文件的名称
     * @param filename
     * @param request
     * @return
     */
    public static String encodeFilename(String filename, HttpServletRequest request) {    
      /**  
       * 获取客户端浏览器和操作系统信息  
       * 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)  
       * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6  
       */    
      String agent = request.getHeader("USER-AGENT");    
      try {    
        if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {    
          String newFileName = URLEncoder.encode(filename, "UTF-8");    
          newFileName = StringUtils.replace(newFileName, "+", "%20");    
          if (newFileName.length() > 150) {    
            newFileName = new String(filename.getBytes("GB2312"), "ISO8859-1");    
            newFileName = StringUtils.replace(newFileName, " ", "%20");    
          }    
          return newFileName;    
        }    
        if ((agent != null) && (-1 != agent.indexOf("Mozilla"))) {
            return MimeUtility.encodeText(filename, "UTF-8", "B");
        }
        return filename;    
      } catch (Exception ex) {    
        return filename;    
      }    
    }  

	/**
	 * 设置response参数，可以打开下载页面，
	 * @param response 数据
	 * @param os
	 * @param fileNm excel的文件名
	 * */
	public static void getResponsePage(HttpServletRequest request,HttpServletResponse response,ByteArrayOutputStream os,String fileNm){
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		// 设置response参数，可以打开下载页面
		try {
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename="+ encodeFilename(fileNm + ".xlsx",request));
			ServletOutputStream out = response.getOutputStream();
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				logger.error("--------------打开下载页面出现问题----------------");
				e.printStackTrace();
			}
		}
	}
}
