package cn.william.rest.service;

import cn.william.pojo.TbContent;

import java.util.List;

public interface ContentService {

	List<TbContent> getContentList(long contentCid);
}
