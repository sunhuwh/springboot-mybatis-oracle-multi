package com.sh.dmdao;

import java.util.List;

import com.sh.model.DmRyJbxx;


public interface DmRyJbxxMapper {

	List<DmRyJbxx> selectBySfzh(String sfzh);
}