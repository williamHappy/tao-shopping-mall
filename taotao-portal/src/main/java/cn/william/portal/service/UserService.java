package cn.william.portal.service;


import cn.william.pojo.TbUser;

public interface UserService {

	TbUser getUserByToken(String token);
}
