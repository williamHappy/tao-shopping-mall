package cn.william.service;


import cn.william.pojo.TaotaoResult;
import cn.william.pojo.TbItemParam;

public interface ItemParamService {

	TaotaoResult getItemParamByCid(long cid);
	TaotaoResult insertItemParam(TbItemParam itemParam);
}
