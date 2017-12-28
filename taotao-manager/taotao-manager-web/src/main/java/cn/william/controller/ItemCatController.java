package cn.william.controller;

import cn.william.pojo.EUTreeNode;
import cn.william.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by william on 2017/1/12.
 * 商品分类管理Controller
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EUTreeNode> getCatList(@RequestParam(value = "id",defaultValue = "0")Long parentId){
        List<EUTreeNode> euTreeNodeList = itemCatService.getCatList(parentId);
        return euTreeNodeList;
    }

}
