package com.iviui.carlife.modules.people.controller;

import com.iviui.carlife.modules.people.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: ChengPan
 * @date: 2019/7/23
 * @description: Excel下载
 */
@RequestMapping("/people/*")
@Controller
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @RequestMapping(value = "peopleDownLoad",method = RequestMethod.GET)
    public void peopleDownLoad(HttpServletRequest request, HttpServletResponse response){
        try {
            peopleService.peopleDownLoad(request,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}