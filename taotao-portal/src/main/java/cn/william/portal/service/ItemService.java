package cn.william.portal.service;


import cn.william.portal.pojo.ItemInfo;

public interface ItemService {

	ItemInfo getItemById(Long itemId);
	String getItemDescById(Long itemId);
	String getItemParam(Long itemId);
	
}
