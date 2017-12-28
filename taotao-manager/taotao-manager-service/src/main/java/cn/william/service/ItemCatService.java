package cn.william.service;

import cn.william.pojo.EUTreeNode;

import java.util.List;

/**
 * Created by william on 2017/1/12.
 */
public interface ItemCatService {

    List<EUTreeNode> getCatList(long parentId);

}
