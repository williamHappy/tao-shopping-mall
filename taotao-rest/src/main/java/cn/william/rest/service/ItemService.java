package cn.william.rest.service;


import cn.william.pojo.TaotaoResult;

public interface ItemService {

    TaotaoResult getItemBaseInfo(long itemId);

    TaotaoResult getItemDesc(long itemId);

    TaotaoResult getItemParam(long itemId);
}
