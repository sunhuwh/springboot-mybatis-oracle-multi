package com.sh.service;

import com.sh.model.DmRyJbxx;
import com.sh.model.User;

public interface UserService {

	User selectByPrimaryKey(String id);

	DmRyJbxx selectBySfzh(String sfzh);

}
