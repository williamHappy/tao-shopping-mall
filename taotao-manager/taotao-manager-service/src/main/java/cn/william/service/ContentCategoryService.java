package cn.william.service;


import cn.william.pojo.EUTreeNode;
import cn.william.pojo.TaotaoResult;

import java.util.List;

public interface ContentCategoryService {

    List<EUTreeNode> getCategoryList(long parentId);

    TaotaoResult insertContentCategory(long parentId, String name);
}
