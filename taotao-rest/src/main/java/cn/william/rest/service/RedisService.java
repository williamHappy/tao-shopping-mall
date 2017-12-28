package cn.william.rest.service;


import cn.william.pojo.TaotaoResult;

public interface RedisService {

    TaotaoResult syncContent(long contentCid);
}
