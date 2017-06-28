package com.sl.service.goodspackaffiliated;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sl.dao.goodspackaffiliated.GoodsPackAffiliatedMapper;
import com.sl.pojo.GoodsPackAffiliated;
@Service
public class GoodsPackAffiliatedServiceImpl implements GoodsPackAffiliatedService {
    @Resource
	private GoodsPackAffiliatedMapper goodsPackAffiliatedMapper;
	
    @Override
	public List<GoodsPackAffiliated> listgoodsPackAffiliated(GoodsPackAffiliated goodsPackAffiliated) throws Exception {
		// TODO Auto-generated method stub
		return goodsPackAffiliatedMapper.listgoodsPackAffiliated(goodsPackAffiliated);
	}

	@Override
	public int saveGoodsPackAffiliated(GoodsPackAffiliated goodsPackAffiliated) throws Exception {
		// TODO Auto-generated method stub
		return goodsPackAffiliatedMapper.saveGoodsPackAffiliated(goodsPackAffiliated);
	}

	@Override
	public int removeGoodsPackAffiliated(GoodsPackAffiliated goodsPackAffiliated) throws Exception {
		// TODO Auto-generated method stub
		return goodsPackAffiliatedMapper.removeGoodsPackAffiliated(goodsPackAffiliated);
	}

}
