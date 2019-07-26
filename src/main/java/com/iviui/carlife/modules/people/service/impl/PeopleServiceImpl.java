package com.iviui.carlife.modules.people.service.impl;

import com.iviui.carlife.modules.people.mapper.PeopleMapper;
import com.iviui.carlife.modules.people.service.PeopleService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.iviui.carlife.modules.util.ExcelUtil.encodeFilename;

/**
 * @author: ChengPan
 * @date: 2019/7/23
 * @description: Excel下载
 */
@Service
public class PeopleServiceImpl implements PeopleService {

    private static final Logger logger = LoggerFactory.getLogger(PeopleServiceImpl.class);

    @Autowired
    private PeopleMapper peopleMapper;

    @Override
    public void peopleDownLoad(HttpServletRequest request, HttpServletResponse response) throws IOException  {
        StopWatch sw = new StopWatch();
        sw.start("人员批量下载");
        String fileNm = "人员批量下载"+ UUID.randomUUID();
        List<Map<String,Object>> resultList = peopleMapper.listPeopleInfo(request);

        // 创建excel工作簿
        Workbook wb = new SXSSFWorkbook(50);
        //标题样式
        CellStyle titleStyle = wb.createCellStyle();
        //标题字体
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short) 10);
        titleFont.setColor(IndexedColors.BLACK.getIndex());
        titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);

        titleStyle.setFont(titleFont);
        titleStyle.setBorderLeft(CellStyle.BORDER_THIN);
        titleStyle.setBorderRight(CellStyle.BORDER_THIN);
        titleStyle.setBorderTop(CellStyle.BORDER_THIN);
        titleStyle.setBorderBottom(CellStyle.BORDER_THIN);
        titleStyle.setAlignment(CellStyle.ALIGN_CENTER);

        Sheet sheet = wb.createSheet("普通下载");
        Row titleRow = sheet.createRow(0);
        Cell id = titleRow.createCell(0);
        Cell name = titleRow.createCell(1);
        Cell sex = titleRow.createCell(2);
        Cell createTime = titleRow.createCell(3);
        id.setCellValue("编号");
        name.setCellValue("姓名");
        sex.setCellValue("性别");
        createTime.setCellValue("创建时间");
        id.setCellStyle(titleStyle);
        name.setCellStyle(titleStyle);
        sex.setCellStyle(titleStyle);
        createTime.setCellStyle(titleStyle);

        for (int i = 0;i<resultList.size();i++){
            sheet.setColumnWidth((short) i+1, (short) (35.7 * 150));
            createRowData(sheet.createRow(i+1),titleStyle,resultList.get(i));
        }

        OutputStream output = null;
        try {
            output = response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition", "attachment;filename="+ encodeFilename(fileNm + ".xlsx",request));
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            wb.write(output);
            output.close();
            sw.stop();
            System.out.println(sw.prettyPrint());
            System.out.println(sw.getTotalTimeMillis());
        } catch (IOException e) {
            logger.error("Excel下载失败{}",e.getMessage());
        } finally {
            if (output != null) {
                output.close();
            }
        }
    }

    private void createRowData(Row dataRow, CellStyle titleStyle, Map<String, Object> dataMap){
        Cell id = dataRow.createCell(0);
        Cell name = dataRow.createCell(1);
        Cell sex = dataRow.createCell(2);
        Cell createTime = dataRow.createCell(3);
        id.setCellValue(dataMap.get("id")==null?"":dataMap.get("id").toString());
        name.setCellValue(dataMap.get("name")==null?"":dataMap.get("name").toString());
        sex.setCellValue(dataMap.get("sex")==null?"":dataMap.get("sex").toString());
        createTime.setCellValue(dataMap.get("createTime")==null?"":dataMap.get("createTime").toString());
        id.setCellStyle(titleStyle);
        name.setCellStyle(titleStyle);
        sex.setCellStyle(titleStyle);
        createTime.setCellStyle(titleStyle);
    }
}