package com.zw.learning.controller;

import com.zw.learning.entity.Image;
import com.zw.learning.service.ImageService;
import com.zw.learning.service.impl.ImageServiceImpl;
import com.zw.learning.utils.R;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletRequest;
import java.awt.print.Pageable;
import java.util.List;

@Controller
@RequestMapping(value = "/jsoup")
public class JsoupController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/images", method = RequestMethod.GET)
    @ResponseBody
    public void images(){
        try{
            Document document = Jsoup.connect("http://www.tooopen.com/view/1439719.html").get();
            Elements lists = document.select(".pic");

            for (int i=0;i<lists.size();i++) {
                Element list = lists.get(i);
                Element img =  list.select("img").get(0);
                String imgUrl = img.attr("src");
                Image image = new Image();
                image.setId(i);
                image.setUrl(imgUrl);
                imageService.saveImage(image);
                System.out.println("========================");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/imageList", method = RequestMethod.GET)
    @ResponseBody
    public R imageList(){
        return R.isOK().data(imageService.findAllImageList());
    }

    /***
     * 分页查询
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/imageListByPage", method = RequestMethod.GET)
    @ResponseBody
    public R imageListByPage(HttpServletRequest httpServletRequest){

        Integer page = Integer.valueOf(httpServletRequest.getParameter("page"));
        Integer size = Integer.valueOf(httpServletRequest.getParameter("size"));
        Page<Image> list = imageService.findAllImageListByPage(page, size);

        return R.isOK().data(list.getContent());
    }



    /***
     * 分页查询 web
     * @param
     * @return
     */
    @RequestMapping(value = "/imagePage", method = RequestMethod.GET)
    public String imagePage(ModelMap modelMap, @RequestParam(value = "page", defaultValue = "0") Integer page,
                            @RequestParam(value = "size", defaultValue = "5") Integer size){

        Page<Image> list = imageService.findAllImageListByPage(page+1, size);
        modelMap.addAttribute("datas", list);
        return "images";
    }


    public void test(){
        try{
            Document document = Jsoup.connect("http://news.qq.com/world_index.shtml").get();
            Elements lists = document.select(".linkto");
            for (int i=0;i<lists.size();i++) {
                Element list = lists.get(i);
                String title = list.text();
                System.out.println(title);
                System.out.println("========================");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
