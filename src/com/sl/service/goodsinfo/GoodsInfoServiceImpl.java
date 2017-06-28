package com.sl.service.goodsinfo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sl.dao.goodsinfo.GoodsInfoMapper;
import com.sl.pojo.GoodsInfo;
@Service
public class GoodsInfoServiceImpl implements GoodsInfoService{
    @Resource
	private GoodsInfoMapper goodsInfoMapper;
	@Override
	public List<GoodsInfo> listGoodsInfo(GoodsInfo goodsInfo) throws Exception {
		// TODO Auto-generated method stub
		return goodsInfoMapper.listGoodsInfo(goodsInfo);
	}

	@Override
	public List<GoodsInfo> listGoodsInfoByPage(GoodsInfo goodsInfo) throws Exception {
		// TODO Auto-generated method stub
		return goodsInfoMapper.listGoodsInfoByPage(goodsInfo);
	}

	@Override
	public int goodsSNIsExit(GoodsInfo goodsInfo) throws Exception {
		// TODO Auto-generated method stub
		return goodsInfoMapper.goodsSNIsExit(goodsInfo);
	}

	@Override
	public int totalCount(GoodsInfo goodsInfo) throws Exception {
		// TODO Auto-generated method stub
		return goodsInfoMapper.totalCount(goodsInfo);
	}

	@Override
	public int isExitInPack(GoodsInfo goodsInfo) throws Exception {
		// TODO Auto-generated method stub
		return goodsInfoMapper.isExitInPack(goodsInfo);
	}

	@Override
	public int saveGoodsInfo(GoodsInfo goodsInfo) throws Exception {
		// TODO Auto-generated method stub
		return goodsInfoMapper.saveGoodsInfo(goodsInfo);
	}

	@Override
	public GoodsInfo getGoodsInfoById(GoodsInfo goodsInfo) throws Exception {
		// TODO Auto-generated method stub
		return goodsInfoMapper.getGoodsInfoById(goodsInfo);
	}

	@Override
	public int updateGoodsInfo(GoodsInfo goodsInfo) throws Exception {
		// TODO Auto-generated method stub
		return goodsInfoMapper.updateGoodsInfo(goodsInfo);
	}

	@Override
	public int removeGoodsInfo(GoodsInfo goodsInfo) throws Exception {
		// TODO Auto-generated method stub
		return goodsInfoMapper.removeGoodsInfo(goodsInfo);
	}

	@Override
	public List<GoodsInfo> listGoodsInfoByName(GoodsInfo goodsInfo) throws Exception {
		// TODO Auto-generated method stub
		return goodsInfoMapper.listGoodsInfoByName(goodsInfo);
	}
	
}
