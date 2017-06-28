package com.sl.service.goodspack;

import java.util.List;


import com.sl.pojo.GoodsPack;
import com.sl.pojo.GoodsPackAffiliated;

public interface GoodsPackService {

	/**
	 * listGoodsPack
	 * @param goodsPack
	 * @return
	 * @throws Exception
	 */
	List<GoodsPack> listGoodsPack(GoodsPack goodsPack) throws Exception;
	
	
	/**
	 * 分页查询
	 * @param goodsPack
	 * @return
	 * @throws Exception
	 */
	List<GoodsPack> listGoodsPackByPage(GoodsPack goodsPack) throws Exception;
	
	/**
	 * 总记录数
	 * @param goodsPack
	 * @return
	 * @throws Exception
	 */
	int totalCount(GoodsPack goodsPack) throws Exception;
	
	/**
	 * 新增商品套餐
	 * @param goodsPack
	 * @return
	 * @throws Exception
	 */
	int saveGoodsPack(GoodsPack goodsPack) throws Exception;
	
	/**
	 * 商品套餐编号是否存在
	 * @param goodsPack
	 * @return
	 * @throws Exception
	 */
	int goodsPackCodeIsExit(GoodsPack goodsPack) throws Exception;
	
	/**
	 * 根据id获取商品套餐
	 * @param goodsPack
	 * @return
	 * @throws Exception
	 */
	GoodsPack getGoodsPackById(GoodsPack goodsPack) throws Exception;
	
	/**
	 * 修改商品套餐
	 * @param goodsPack
	 * @return
	 * @throws Exception
	 */
	int updateGoodsPack(GoodsPack goodsPack) throws Exception;
	
	/**
	 * 删除商品套餐
	 * @param goodsPack
	 * @return
	 * @throws Exception
	 */
	int removeGoodsPack(GoodsPack goodsPack) throws Exception;
	
	/**
	 * @return
	 * @throws Exception
	 */
	int getAddGoodsPackId() throws Exception;
	
	/**
	 * 保存新增的商品套餐
	 * @param goodsPack
	 * @param apaList
	 * @return
	 * @throws Exception
	 */
	boolean tm_addGoodsPack(GoodsPack goodsPack,List<GoodsPackAffiliated> apaList) throws Exception;

	/**
	 * 保存修改的商品套餐
	 * @param goodsPack
	 * @param apaList
	 * @return
	 * @throws Exception
	 */
	boolean tm_modifyGoodsPack(GoodsPack goodsPack,List<GoodsPackAffiliated> apaList) throws Exception;
}
