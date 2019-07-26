package com.iviui.carlife.modules.people.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: ChengPan
 * @date: 2019/7/23
 * @description: Excel下载
 */
public interface PeopleService {
    void peopleDownLoad(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
