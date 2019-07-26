package com.iviui.carlife.modules.people.mapper;

import org.apache.ibatis.annotations.Mapper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author: ChengPan
 * @date: 2019/7/23
 * @description: Excel下载
 */
@Mapper
public interface PeopleMapper {
    /**
     * 查询所有人员信息
     * @param request
     * @return
     */
    List<Map<String, Object>> listPeopleInfo(HttpServletRequest request);
}
