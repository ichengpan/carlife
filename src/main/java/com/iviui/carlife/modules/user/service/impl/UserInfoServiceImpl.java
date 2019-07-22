package com.iviui.carlife.modules.user.service.impl;

import com.iviui.carlife.modules.user.mapper.UserInfoMapper;
import com.iviui.carlife.modules.user.service.UserInfoService;
import com.iviui.carlife.modules.user.vo.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.iviui.carlife.modules.util.ExcelUtil.encodeFilename;

/**
 * @author: ChengPan
 * @date: 2019/5/13
 * @description: 菜单信息
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    //事务管理
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Override
    public Integer countUserInfo(User userInfo) {
        return userInfoMapper.countUserInfo(userInfo);
    }

    @Override
    public List<Map<String, Object>> listUserInfo(User userInfo) {

        return userInfoMapper.listUserInfo(userInfo);
    }

    @Override
    public List<Map<String, Object>> userInfoDownLoad(User userInfo) {
        return userInfoMapper.userInfoDownLoad(userInfo);
    }

    @Override
    public void insertBatchesUserInfo() {
        List<Map<String,Object>> dataList = new ArrayList<>();
        for (int i = 1; i< 3000001;i++){
            Map<String, Object> data = new HashMap<>();
            data.put("id",i);
            data.put("name",i);
            data.put("sex",i%2);
            dataList.add(data);
            if (dataList.size()%20000==0) {
                userInfoMapper.insertUserInfo(dataList);
                dataList.clear();
            }
        }

    }

    @Override
    public void downLoadBatchesUserInfo(HttpServletRequest request, HttpServletResponse response) {
        String fileNm = "批量下载";
        Map<String,Object> paramMap = new HashMap<>();
        List<Map<String,Object>> resultData = userInfoMapper.queryPeopleData(paramMap);
        String[] keys ={"id","name","sex","createTime"};
        String[] columnNames={"编号","名称","性别","创建时间"};
        Workbook wb = createWorkBook(resultData, keys, columnNames);
        try {
            OutputStream output=response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition", "attachment;filename="+ encodeFilename(fileNm + ".xlsx",request));
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            wb.write(output);
            output.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

    private Workbook createWorkBook(List<Map<String, Object>> list, String[] keys, String columnNames[]) {
        Workbook wb = new XSSFWorkbook();
        // Create sheet
        Sheet sheet = wb.createSheet("测试批量下载");
        Row titleRow = sheet.createRow(0);
        CellStyle cellTitleStyle = wb.createCellStyle();
        cellTitleStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        cellTitleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellTitleStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellTitleStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellTitleStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellTitleStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellTitleStyle.setAlignment(CellStyle.ALIGN_CENTER);
        for(int i = 0; i<columnNames.length;i++){
            Cell titleRowCell = titleRow.createCell(i);
            titleRowCell.setCellValue(columnNames[i]);
            titleRowCell.setCellStyle(cellTitleStyle);
        }
        if(CollectionUtils.isNotEmpty(list)){
            CellStyle dataCellStyle = wb.createCellStyle();
            dataCellStyle.setFillForegroundColor(IndexedColors.ROSE.getIndex());
            dataCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
            dataCellStyle.setBorderLeft(CellStyle.BORDER_THIN);
            dataCellStyle.setBorderRight(CellStyle.BORDER_THIN);
            dataCellStyle.setBorderTop(CellStyle.BORDER_THIN);
            dataCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
            dataCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
            for(int i = 0;i<list.size();i++){
                Row dataRow = sheet.createRow(i + 1);
                for(int cell = 0; cell<keys.length;cell++){
                    Cell dataRowCell = dataRow.createCell(cell);
                    dataRowCell.setCellValue(list.get(i).get(keys[cell])+"");
                    dataRowCell.setCellStyle(dataCellStyle);
                }
                //sheet.autoSizeColumn(i);
            }
        }

        return wb;
    }

    @Override
    public String insertPeople() {
        List<Map<String,Object>> dataList = new ArrayList<>();
        Map<String, Object> data = new HashMap<>();
        data.put("id",1);
        data.put("name","cheng");
        data.put("sex",1);
        dataList.add(data);

        new TransactionTemplate(transactionManager).execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                try {
                    int i = userInfoMapper.insertUserInfo(dataList);
                    return "新增成功";
                }catch (Exception e){
                    status.setRollbackOnly();
                    return e.getMessage();
                }
            }
        });
        return null;
    }
}