package cn.william.service;

import cn.william.pojo.EUDataGridResult;
import cn.william.pojo.TaotaoResult;
import cn.william.pojo.TbItem;

/**
 * Created by william on 2017/1/10.
 */
public interface ItemService {

    TbItem getItemById(long itemId);
    EUDataGridResult getItemList(int page,int rows);
    TaotaoResult createItem(TbItem item, String desc, String itemParam) throws Exception;

}
