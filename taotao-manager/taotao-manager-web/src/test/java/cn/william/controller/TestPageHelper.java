package cn.william.controller;

import cn.william.mapper.TbItemMapper;
import cn.william.pojo.TbItem;
import cn.william.pojo.TbItemExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by william on 2017/1/10.
 */
public class TestPageHelper {

    @Test
    public void testPageHelper() {

        //创建一个spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        //从spring容器中获得Mapper的代理对象
        TbItemMapper mapper = applicationContext.getBean(TbItemMapper.class);
        //执行查询，并分页
        TbItemExample example = new TbItemExample();
        //分页处理
        PageHelper.startPage(1, 10);
        List<TbItem> itemList = mapper.selectByExample(example);
        //取商品列表
        for (TbItem tbItem : itemList) {
            System.out.println(tbItem.getTitle());
        }
        //取分页信息
        PageInfo<TbItem> pageInfo = new PageInfo<>(itemList);
        long total = pageInfo.getTotal();
        System.out.println("共有商品：" + total);

    }

}
