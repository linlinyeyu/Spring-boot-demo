package com.edi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.edi.domain.WsMerchant;
import com.edi.util.MyMapper;

/**
 * 商家模块
 * @author jjweng@ibenben.com
 *
 */
public interface WsMerchantMapper extends MyMapper<WsMerchant>{
	/**
	 * 查询商家的名称+对应的秘钥
	 * @return
	 */
	@Select("select merchant_name merchantName,app_key appKey,secret,session_key sessionKey "
			+ "from ws_merchant where app_key is not null")
	List<WsMerchant> selectForNameKey();

}
