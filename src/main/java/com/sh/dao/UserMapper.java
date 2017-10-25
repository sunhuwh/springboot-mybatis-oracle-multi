package com.sh.dao;

import java.util.List;

import com.sh.model.User;

public interface UserMapper {

    User selectByPrimaryKey(String id);
}