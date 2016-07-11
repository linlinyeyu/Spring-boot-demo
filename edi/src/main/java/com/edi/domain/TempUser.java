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
@Table(name="temp_user")
public class TempUser implements Serializable {
	private static final long serialVersionUID = 9223372036854775807L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column  (name = "id")
	private Integer id;
	/**
	 * 姓名
	 */
	@Column  (name = "user")
	private String user;
	/**
	 * 年龄
	 */
	@Column  (name = "age")
	private Integer age;

	/**
	 * 
	 */
	public void setId(Integer id){
		this.id=id;
	}
	/**
	 * 
	 */
	public Integer getId(){
		return id;
	}
	/**
	 * 姓名
	 */
	public void setUser(String user){
		this.user=user;
	}
	/**
	 * 姓名
	 */
	public String getUser(){
		return user;
	}
	/**
	 * 年龄
	 */
	public void setAge(Integer age){
		this.age=age;
	}
	/**
	 * 年龄
	 */
	public Integer getAge(){
		return age;
	}
}

