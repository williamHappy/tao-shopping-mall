package cn.william.order.service;

import cn.william.pojo.TaotaoResult;
import cn.william.pojo.TbOrder;
import cn.william.pojo.TbOrderItem;
import cn.william.pojo.TbOrderShipping;

import java.util.List;

public interface OrderService {

	 TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping);
}
