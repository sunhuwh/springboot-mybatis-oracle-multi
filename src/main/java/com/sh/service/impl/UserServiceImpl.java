package com.sh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.sh.dao.UserMapper;
import com.sh.dmdao.DmRyJbxxMapper;
import com.sh.model.DmRyJbxx;
import com.sh.model.User;
import com.sh.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private DmRyJbxxMapper dmRyJbxxMapper;

	@Override
	public User selectByPrimaryKey(String id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public DmRyJbxx selectBySfzh(String sfzh) {
		List<DmRyJbxx> dmRyJbxxs = dmRyJbxxMapper.selectBySfzh(sfzh);
		return CollectionUtils.isEmpty(dmRyJbxxs) ? null : dmRyJbxxs.get(0);
	}

}
