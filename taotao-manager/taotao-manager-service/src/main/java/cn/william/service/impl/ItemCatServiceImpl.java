package cn.william.service.impl;

import cn.william.mapper.TbItemCatMapper;
import cn.william.pojo.EUTreeNode;
import cn.william.pojo.TbItemCat;
import cn.william.pojo.TbItemCatExample;
import cn.william.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by william on 2017/1/12.
 * 商品分类管理
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<EUTreeNode> getCatList(long parentId) {

        //创建查询条件
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //根据条件查询
        List<TbItemCat> itemCatList = itemCatMapper.selectByExample(example);
        List<EUTreeNode> euTreeNodeList = new ArrayList<>();
        //把列表转换成treeNodeList
        for (TbItemCat tbItemCat:itemCatList) {
            EUTreeNode node = new EUTreeNode();
            node.setId(tbItemCat.getId());
            node.setText(tbItemCat.getName());
            node.setState(tbItemCat.getIsParent()?"closed":"open");
            euTreeNodeList.add(node);
        }

        //返回结果
        return euTreeNodeList;
    }
}
