package com.sl.service.goodspack;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sl.dao.goodspack.GoodsPackMapper;
import com.sl.dao.goodspackaffiliated.GoodsPackAffiliatedMapper;
import com.sl.pojo.GoodsPack;
import com.sl.pojo.GoodsPackAffiliated;
@Service
public class GoodsPackServiceImpl implements GoodsPackService{
	@Resource
	private GoodsPackMapper goodsPackMapper;
	@Resource
	private GoodsPackAffiliatedMapper goodsPackAffiliatedMapper;
	 
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
		return goodsPackMapper.updateGoodsPack(goodsPack);
	}

	@Override
	public int removeGoodsPack(GoodsPack goodsPack) throws Exception {
		// TODO Auto-generated method stub
		return goodsPackMapper.removeGoodsPack(goodsPack);
	}

	@Override
	public int getAddGoodsPackId() throws Exception {
		// TODO Auto-generated method stub
		return goodsPackMapper.getAddGoodsPackId();
	}

	@Override
	public boolean tm_addGoodsPack(GoodsPack goodsPack, List<GoodsPackAffiliated> apaList) throws Exception {
		int addGoodsPackId = 0;
		goodsPackMapper.saveGoodsPack(goodsPack);
		addGoodsPackId = goodsPackMapper.getAddGoodsPackId();
		if(null != apaList && apaList.size() > 0 && addGoodsPackId != 0){
			for(int i = 0; i <  apaList.size(); i++){
				if(null != apaList.get(i)){
					apaList.get(i).setGoodsPackId(addGoodsPackId);
					goodsPackAffiliatedMapper.saveGoodsPackAffiliated(apaList.get(i));
				}
			}
		}
		return true;
	}

	@Override
	public boolean tm_modifyGoodsPack(GoodsPack goodsPack, List<GoodsPackAffiliated> apaList) throws Exception {
		goodsPackMapper.updateGoodsPack(goodsPack);
		int goodsPackId = goodsPack.getId();
		GoodsPackAffiliated goodsPackAffiliated = new GoodsPackAffiliated();
		goodsPackAffiliated.setGoodsPackId(goodsPackId);
		goodsPackAffiliatedMapper.removeGoodsPackAffiliated(goodsPackAffiliated);
		if(null != apaList){
			for(int i = 0; i < apaList.size(); i++){
				if(null != apaList.get(i)){
					apaList.get(i).setGoodsPackId(goodsPackId);
					goodsPackAffiliatedMapper.saveGoodsPackAffiliated(apaList.get(i));
				}
			}
		}
		return true;
	}
	
}
