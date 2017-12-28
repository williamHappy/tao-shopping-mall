package cn.william.service.impl;

import cn.william.mapper.TbItemDescMapper;
import cn.william.mapper.TbItemMapper;
import cn.william.mapper.TbItemParamItemMapper;
import cn.william.pojo.*;
import cn.william.pojo.TbItemExample.Criteria;
import cn.william.service.ItemService;
import cn.william.utils.IDUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by william on 2017/1/10.
 * 商品管理service
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Override
    public TbItem getItemById(long itemId) {

        //TbItem item = itemMapper.selectByPrimaryKey(itemId);
        TbItemExample example = new TbItemExample();
        //添加查询条件
        Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> itemList = itemMapper.selectByExample(example);
        if (itemList != null && itemList.size() > 0) {
            TbItem item = itemList.get(0);
            return item;
        }

        return null;
    }

    /**
     * 商品里列表查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public EUDataGridResult getItemList(int pageNum, int pageSize) {
        //查询商品列表
        TbItemExample example = new TbItemExample();
        //分页处理
        PageHelper.startPage(pageNum, pageSize);
        List<TbItem> list = itemMapper.selectByExample(example);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;

    }

    @Override
    public TaotaoResult createItem(TbItem item, String desc, String itemParam) throws Exception {
        //item补全
        //生成商品id
        Long itemId = IDUtils.genItemId();
        item.setId(itemId);
        //商品状态：1 正常 2 下架  3 删除
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        itemMapper.insert(item);

        //添加商品描述信息
        TaotaoResult result = this.insertItemDesc(itemId, desc);
        if (result.getStatus() != 200) {
            throw new Exception();
        }
        //添加规格参数
        result = insertItemParamItem(itemId, itemParam);
        if (result.getStatus() != 200) {
            throw new Exception();
        }

        return TaotaoResult.ok();
    }

    /**
     * 添加商品详情
     *
     * @param itemId
     * @param desc
     * @return
     */
    private TaotaoResult insertItemDesc(long itemId, String desc) {
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        itemDescMapper.insert(itemDesc);
        return TaotaoResult.ok();
    }

    /**
     * 添加规格参数
     *
     * @param itemId
     * @param itemParam
     * @return
     */
    private TaotaoResult insertItemParamItem(Long itemId, String itemParam) {
        //创建一个pojo
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(itemParam);
        itemParamItem.setCreated(new Date());
        itemParamItem.setUpdated(new Date());
        //向表中插入数据
        itemParamItemMapper.insert(itemParamItem);

        return TaotaoResult.ok();

    }
}
