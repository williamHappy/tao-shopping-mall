package cn.william.portal.service;


import cn.william.portal.pojo.SearchResult;

public interface SearchService {

	SearchResult search(String queryString, int page);
}
