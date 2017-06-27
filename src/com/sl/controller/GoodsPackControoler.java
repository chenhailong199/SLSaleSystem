package com.sl.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.sl.service.goodspack.GoodsPackService;

@Controller
public class GoodsPackControoler extends BaseController {
	@Resource
	private GoodsPackService goodsPackService;
	
	
}
