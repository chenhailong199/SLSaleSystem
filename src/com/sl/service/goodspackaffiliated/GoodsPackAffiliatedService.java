package com.sl.service.goodspackaffiliated;

import java.util.List;

import com.sl.pojo.GoodsPackAffiliated;

public interface GoodsPackAffiliatedService {
	/**
	 * listgoodsPackAffiliated
	 * 若id 不为空,根据id查询
	 * @param goodsPackAffiliated
	 * @return
	 * @throws Exception
	 */
	List<GoodsPackAffiliated> listgoodsPackAffiliated(GoodsPackAffiliated goodsPackAffiliated) throws Exception;

    /**
     * 增加
     * @param goodsPackAffiliated
     * @return
     * @throws Exception
     */
    int saveGoodsPackAffiliated(GoodsPackAffiliated goodsPackAffiliated) throws Exception;

    /**
     * 删除
     * @param goodsPackAffiliated
     * @return
     * @throws Exception
     */
    int removeGoodsPackAffiliated(GoodsPackAffiliated goodsPackAffiliated) throws Exception;

}
