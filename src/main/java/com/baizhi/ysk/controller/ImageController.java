package com.baizhi.ysk.controller;

import com.baizhi.ysk.conf.ValidateImageCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("/image")
public class ImageController {
    @RequestMapping("/getImage")
    public void getImage(HttpSession session, HttpServletResponse response) {
        String code = ValidateImageCodeUtils.getSecurityCode();
        session.setAttribute("code", code);
        BufferedImage image = ValidateImageCodeUtils.createImage(code);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            ImageIO.write(image, "png", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
