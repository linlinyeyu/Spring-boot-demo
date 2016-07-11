package com.edi.domain;

import java.io.Serializable;
import javax.persistence.*;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author jjweng@ibenben.com
 *
 */
@Entity
@Table(name="temp_joke")
public class TempJoke implements Serializable {
	private static final long serialVersionUID = 1015208101809320960L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column  (name = "JokeId")
	private Integer JokeId;
	/**
	 * ID
	 */
	@Column  (name = "id")
	private String id;
	/**
	 * 标题
	 */
	@Column  (name = "title")
	private String title;
	/**
	 * 内容
	 */
	@Column  (name = "text")
	private String text;
	/**
	 * 类型
	 */
	@Column  (name = "type")
	private Integer type;
	/**
	 * 时间
	 */
	@Column  (name = "ct")
	private String ct;

	/**
	 * 
	 */
	public void setJokeId(Integer JokeId){
		this.JokeId=JokeId;
	}
	/**
	 * 
	 */
	public Integer getJokeId(){
		return JokeId;
	}
	/**
	 * ID
	 */
	public void setId(String id){
		this.id=id;
	}
	/**
	 * ID
	 */
	public String getId(){
		return id;
	}
	/**
	 * 标题
	 */
	public void setTitle(String title){
		this.title=title;
	}
	/**
	 * 标题
	 */
	public String getTitle(){
		return title;
	}
	/**
	 * 内容
	 */
	public void setText(String text){
		this.text=text;
	}
	/**
	 * 内容
	 */
	public String getText(){
		return text;
	}
	/**
	 * 类型
	 */
	public void setType(Integer type){
		this.type=type;
	}
	/**
	 * 类型
	 */
	public Integer getType(){
		return type;
	}
	/**
	 * 时间
	 */
	public void setCt(String ct){
		this.ct=ct;
	}
	/**
	 * 时间
	 */
	public String getCt(){
		return ct;
	}
}

