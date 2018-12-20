package com.baizhi.ysk.controller;

import com.baizhi.ysk.conf.CreateValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private CreateValidateCode createValidateCode;

    @RequestMapping("/getImage")
    public void getImage(HttpSession session, HttpServletResponse response) throws IOException {
        String code = createValidateCode.getCode();
        session.setAttribute("code", code);
        createValidateCode.write(response.getOutputStream());
    }
}
