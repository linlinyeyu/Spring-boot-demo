package com.edi.api;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edi.dto.EdiResponse;
import com.edi.service.SystemService;

/**
 * 系统模块：初始化配置
 * @author jjweng@ibenben.com
 *
 */
@Controller
@RequestMapping("/system")
public class SystemController {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private SystemService systemService;
	/**
	 * 重置服务端的配置
	 * @return
	 */
	@RequestMapping(value ="/initDirt")
	@ResponseBody
	public EdiResponse<String> initDirt(){
		systemService.initDirt();
		return null;
	}
}
