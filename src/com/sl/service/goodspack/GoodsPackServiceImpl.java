package com.sl.service.goodspack;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sl.dao.goodspack.GoodsPackMapper;
import com.sl.pojo.GoodsPack;
@Service
public class GoodsPackServiceImpl implements GoodsPackService{
	@Resource
	private GoodsPackMapper goodsPackMapper;
	
	@Override
	public List<GoodsPack> listGoodsPack(GoodsPack goodsPack) throws Exception {
		// TODO Auto-generated method stub
		return goodsPackMapper.listGoodsPack(goodsPack);
	}

	@Override
	public List<GoodsPack> listGoodsPackByPage(GoodsPack goodsPack) throws Exception {
		// TODO Auto-generated method stub
		return goodsPackMapper.listGoodsPackByPage(goodsPack);
	}

	@Override
	public int totalCount(GoodsPack goodsPack) throws Exception {
		// TODO Auto-generated method stub
		return goodsPackMapper.totalCount(goodsPack);
	}

	@Override
	public int saveGoodsPack(GoodsPack goodsPack) throws Exception {
		// TODO Auto-generated method stub
		return goodsPackMapper.saveGoodsPack(goodsPack);
	}

	@Override
	public int goodsPackCodeIsExit(GoodsPack goodsPack) throws Exception {
		// TODO Auto-generated method stub
		return goodsPackMapper.goodsPackCodeIsExit(goodsPack);
	}

	@Override
	public GoodsPack getGoodsPackById(GoodsPack goodsPack) throws Exception {
		// TODO Auto-generated method stub
		return goodsPackMapper.getGoodsPackById(goodsPack);
	}

	@Override
	public int updateGoodsPack(GoodsPack goodsPack) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeGoodsPack(GoodsPack goodsPack) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAddGoodsPackId() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
