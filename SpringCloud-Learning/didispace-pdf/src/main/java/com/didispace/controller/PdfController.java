package com.didispace.controller;

import com.didispace.pdf.PdfUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pdf")
public class PdfController {

    @GetMapping(value = "/getPdf")
    public String getPdf(){
        String  path = PdfUtils.turnToPdf("pdf");
        return path;
    }
}
