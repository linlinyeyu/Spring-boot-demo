package com.edi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edi.domain.TempJoke;
import com.edi.mapper.JokeMapper;

/**
 * 笑话接口业务逻辑层	
 */
@Service
public class JokeService {
	@Autowired
	private JokeMapper jokeMapper;
	
	/**
	 * 插入记录
	 */
	public void addJoke(TempJoke joke)
	{
		jokeMapper.insert(joke);
	}

}
