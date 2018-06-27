package com.threebrother.qofood.controller.mng;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.threebrother.qofood.common.Constant;
import com.threebrother.qofood.common.RequestConstant;
import com.threebrother.qofood.common.exception.BusinessException;
import com.threebrother.qofood.entity.Goods;
import com.threebrother.qofood.model.Result;
import com.threebrother.qofood.service.GoodsService;
import com.threebrother.qofood.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/mng/goods")
public class GoodsMngController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsMngController.class);

    @Autowired
    GoodsService goodsService;

    @GetMapping("")
    public String index(HttpServletRequest request){

        return "mng/goods_list";
    }

    @RequestMapping("/pageList")
    @ResponseBody
    public PageInfo<Goods> selectGoodsList(HttpServletRequest request) {

        String pageNumber = request.getParameter("pageNumber");
        String pageSize = request.getParameter("pageSize");
        String sort = request.getParameter("sort");
        String order = request.getParameter("order");
        String goodsName = request.getParameter("goodsName");

        PageInfo<Goods> goodsPageInfo = goodsService.selectGoodsList(Integer.valueOf(pageNumber), Integer.valueOf(pageSize),
                goodsName, sort, order);

        return goodsPageInfo;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result deleteGoods(HttpServletRequest request){

        String goodsId = request.getParameter("goodsId");

        // 参数校验 如果为空 或者 不是数字 抛出异常
        if(Strings.isNullOrEmpty(goodsId) || !StringUtils.isNumeric(goodsId)){
            throw new BusinessException(RequestConstant.DELETE_GOODS_FAILE_CODE,
                    RequestConstant.DELETE_GOODS_FAILE_MSG);
        }

        goodsService.deleteGoodsByGoodsId(Integer.valueOf(goodsId));

        return ResultUtil.success();
    }


    @RequestMapping("/save")
    @ResponseBody
    public Result saveGoods(HttpServletRequest request){

        // 获取参数
        String goodsName = request.getParameter("goodsName");
        String goodsDesc = request.getParameter("goodsDesc");
        String goodsPrice = request.getParameter("goodsPrice");
        String goodsSmallImageUrl = request.getParameter("goodsSmallImageUrl");
        String goodsBigImageUrl = request.getParameter("goodsBigImageUrl");

        Goods goods = new Goods();
        goods.setGoodsName(goodsName);
        goods.setGoodsDesc(goodsDesc);
        goods.setGoodsPrice(new BigDecimal(goodsPrice));
        goods.setGoodsSmallImageUrl(goodsSmallImageUrl);
        goodsBigImageUrlHander(goods, goodsBigImageUrl);

        goodsService.saveGoods(goods);

        return ResultUtil.success();
    }

    private void goodsBigImageUrlHander(Goods goods, String urlArrayStr){
        String[] goodsBigImageUrlArray = urlArrayStr.split(Constant.IMAGES_SPLIT_FLAG);
        LOGGER.info("新增商品列表图片" + goodsBigImageUrlArray.length + "张");
        if(goodsBigImageUrlArray.length == Constant.INT_ONE){
            goods.setGoodsBig1ImageUrl(goodsBigImageUrlArray[Constant.INT_ZERO]);
            goods.setGoodsBig2ImageUrl(goodsBigImageUrlArray[Constant.INT_ZERO]);
            goods.setGoodsBig3ImageUrl(goodsBigImageUrlArray[Constant.INT_ZERO]);
        }

        if(goodsBigImageUrlArray.length == Constant.INT_TWO){
            goods.setGoodsBig1ImageUrl(goodsBigImageUrlArray[Constant.INT_ZERO]);
            goods.setGoodsBig2ImageUrl(goodsBigImageUrlArray[Constant.INT_ZERO]);
            goods.setGoodsBig3ImageUrl(goodsBigImageUrlArray[Constant.INT_ONE]);
        }

        if(goodsBigImageUrlArray.length > Constant.INT_TWO){
            goods.setGoodsBig1ImageUrl(goodsBigImageUrlArray[goodsBigImageUrlArray.length - Constant.INT_ONE]);
            goods.setGoodsBig2ImageUrl(goodsBigImageUrlArray[goodsBigImageUrlArray.length - Constant.INT_TWO]);
            goods.setGoodsBig3ImageUrl(goodsBigImageUrlArray[goodsBigImageUrlArray.length - Constant.INT_THREE]);
        }
    }


    @RequestMapping(value="/fileupload")
    @ResponseBody
    public String fileUpload(HttpServletRequest request, HttpServletResponse response) {

        // ResourceBundle bundle = PropertyResourceBundle.getBundle("application");
        // String rootPath = bundle.getString();

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        Map<String,MultipartFile> fileMap = multipartRequest.getFileMap();

        //TODO 服务器路径/home/static/upload
        // String filePath = "F:\\TEST";
        String filePath = "/home/static/upload/img";

        Map<String, Object> map = new HashMap<>();
        map = uploadFiles(filePath, fileMap);

        return (String) map.get("filePath");
    }

    public Map<String, Object> uploadFiles(String savePath, Map<String,MultipartFile> fiLeMap){
        Map<String, Object> map = new HashMap<>();
        try {
            String fileName = "";
            if(fiLeMap != null){
                for(Map.Entry<String, MultipartFile> entity : fiLeMap.entrySet()){
                    MultipartFile f = entity.getValue();
                    if(f != null && !f.isEmpty()){
                        String nowTime = String.valueOf(System.currentTimeMillis());
                        fileName = nowTime + "-" + (UUID.randomUUID().toString());
                        String originalFilename = f.getOriginalFilename();
                        String suffix = originalFilename.substring(originalFilename.indexOf("."), originalFilename.length());
                        fileName = fileName + suffix;
                        File newFile = new File(savePath + "/" + fileName);
                        f.transferTo(newFile);
                    }
                }
            }
            map.put("success", true);
            map.put("filePath", "https://www.kar98.com.cn/static/img/" + fileName);
            return map;
        }catch (Exception e) {
            map.put("success", false);
            return map;
        }
    }

}
