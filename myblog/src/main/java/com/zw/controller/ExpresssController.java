package com.zw.controller;

import com.zw.constant.LogisticsCompany;
import com.zw.response.ResultBody;
import com.zw.utils.HttpUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExpresssController {

    /**
     * @Description 根据快递code和订单号查询快递信息
     * @param: info
     *          logisticsCode  快递类型
     *          logisticsNo    订单编号 12位
     * @return string
     */
    @ApiOperation(value="分页获得该归档日期下的文章信息", notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "logisticsCode", value = "快递类型缩写", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "logisticsNo", value = "订单编号12位",required = true, dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/getExpressInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResultBody getExpressInfo(@RequestParam String logisticsCode, @RequestParam String logisticsNo){

        String type="";
        switch (logisticsCode){
            case "SF":
                type = LogisticsCompany.SF.getType();
                break;
            case "ZT":
                type = LogisticsCompany.ZT.getType();
                break;
            case "ST":
                type = LogisticsCompany.ST.getType();
                break;
            case "YT":
                type = LogisticsCompany.YT.getType();
                break;
            case "HT":
                type = LogisticsCompany.HT.getType();
                break;
            case "YD":
                type = LogisticsCompany.YD.getType();
                break;
            case "YZ":
                type = LogisticsCompany.YZ.getType();
                break;
            case "EMS":
                type = LogisticsCompany.EMS.getType();
                break;
            case "TT":
                type = LogisticsCompany.TT.getType();
                break;
            case "DB":
                type = LogisticsCompany.DB.getType();
                break;
            default:
                break;
        }
        String temp = String.valueOf(Math.random());
        String url = "http://www.kuaidi100.com/query?type=" + type + "&postid=" + logisticsNo + "&temp=" + temp + "";
        JSONObject ojb = HttpUtil.httpGet(url);
        ResultBody resultBody= new ResultBody();
        resultBody.setCode(String.valueOf(ojb.get("status")));
        resultBody.setMessage(String.valueOf(ojb.get("message")));
        resultBody.setResult(ojb.get("data"));
        return resultBody;
    }
}
