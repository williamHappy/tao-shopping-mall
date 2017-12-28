package cn.william.search.dao;

import cn.william.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;



public interface SearchDao {

	SearchResult search(SolrQuery query) throws Exception;
}
