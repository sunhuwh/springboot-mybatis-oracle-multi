package com.sh.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.sh.model.DmRyJbxx;
import com.sh.model.User;
import com.sh.service.UserService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;

@Controller
public class IndexController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UserService userService;


	@GetMapping("/user/{id}")
	@ResponseBody
	public User user(@PathVariable String id){
		return userService.selectByPrimaryKey(id);
	}

	@GetMapping("/user/sfzh/{sfzh}")
	@ResponseBody
	public DmRyJbxx dmRyJbxx(@PathVariable String sfzh){
		return userService.selectBySfzh(sfzh);
	}

}
