package com.iviui.carlife.modules.util;

import java.io.Serializable;
import java.util.List;

/**
 * @author: ChengPan
 * @date: 2019/5/14
 * @description: layUI分页工具类
 */
public class PageUtil implements Serializable {

	private static final long serialVersionUID = 620421858510718076L;

	private Integer code=0;
	private Long count;
	private String msg;
	private List data;
	private Integer page;
	private Integer size;

	public PageUtil(Integer page,Integer limit) {
		this.page=page;
		this.size =limit;
	}

	public PageUtil(Page page, Long recordsTotal, List data) {
		super();
		this.page = page.getPagenum();
		this.size = page.getPagesize();
		this.count = recordsTotal;
		this.data = data;
	}

	 

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}



	public Integer getPage() {
		return page;
	}



	public void setPage(Integer page) {
		this.page = page;
	}



	public Integer getSize() {
		return size;
	}



	public void setSize(Integer size) {
		this.size = size;
	}

}