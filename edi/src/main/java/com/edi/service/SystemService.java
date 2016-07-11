package com.edi.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edi.domain.WsMerchant;
import com.edi.mapper.WsMerchantMapper;

/**
 * 系统模块：初始化配置
 * @author jjweng@ibenben.com
 *
 */
@Service
public class SystemService {
	@Autowired
    private WsMerchantMapper wsMerchantMapper;
	/**
	 * 启动程序，系统数据初始化，放入系统变量。减少查询次数
	 */
	@PostConstruct
	public void initDirt(){
		List<WsMerchant> wsMerchantList=wsMerchantMapper.selectForNameKey();
		for (WsMerchant wsMerchant : wsMerchantList) {
			String key="WsMerchant."+wsMerchant.getMerchantName();
			String value=wsMerchant.getAppKey()+";"+wsMerchant.getSecret()+";"+wsMerchant.getSessionKey();
			System.setProperty(key, value);
		}
	}
}
