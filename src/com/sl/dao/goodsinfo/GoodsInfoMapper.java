package com.sl.dao.goodsinfo;

import java.util.List;

import com.sl.pojo.GoodsInfo;

public interface GoodsInfoMapper {
	
	/**
	 * listGoodeInfo
	 * @return
	 */
	List<GoodsInfo> listGoodsInfo(GoodsInfo goodsInfo) throws Exception;
	
	/**
	 * listGoodsInfoByPage
	 * @param goodsInfo
	 * @return
	 * @throws Exception
	 */
	List<GoodsInfo> listGoodsInfoByPage(GoodsInfo goodsInfo) throws Exception;
	
	
	/**
	 * 商品名称模糊查询
	 * @param goodsInfo
	 * @return
	 * @throws Exception
	 */
	List<GoodsInfo> listGoodsInfoByName(GoodsInfo goodsInfo) throws Exception;
	
	/**
	 * 商品编码是否存在
	 * @param goodsInfo
	 * @return
	 * @throws Exception
	 */
	int goodsSNIsExit(GoodsInfo goodsInfo) throws Exception;
	
	/**
	 * totalCount
	 * @param goodsInfo
	 * @return
	 * @throws Exception
	 */
	int totalCount(GoodsInfo goodsInfo) throws Exception;
	
	/**
	 * isExitInPack
	 * @param goodsInfo
	 * @return
	 * @throws Exception
	 */
	int isExitInPack(GoodsInfo goodsInfo) throws Exception;
	
	/**
	 * saveGoodsInfo
	 * @param goodsInfo
	 * @return
	 * @throws Exception
	 */
	int saveGoodsInfo(GoodsInfo goodsInfo) throws Exception;
	
	/**
	 * getGoodsInfoById
	 * @param goodsInfo
	 * @return
	 * @throws Exception
	 */
	GoodsInfo getGoodsInfoById(GoodsInfo goodsInfo) throws Exception;
	
	/**
	 * updateGoodsInfo
	 * @param goodsInfo
	 * @return
	 * @throws Exception
	 */
	int updateGoodsInfo(GoodsInfo goodsInfo) throws Exception;
	
	/**
	 * removeGoodsInfo
	 * @param goodsInfo
	 * @return
	 * @throws Exception
	 */
	int removeGoodsInfo(GoodsInfo goodsInfo) throws Exception;
	
}
