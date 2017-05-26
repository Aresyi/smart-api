package com.tranb.ocr.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pagination<T> implements Serializable {

	private static final long serialVersionUID = 1825516096983144624L;

	private int page = 1;

	private int totalRecords;

	private int pageSize = 20;

	private int numbersPerBlock = 10;

	private List<T> records = new ArrayList<T>();

	// Ajax显示分页列表时的容器ID
	private String containerId;

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		// page为-1时为最后一页
		if (page < 1 && page != -1)
			page = 1;
		this.page = page;
	}

	public int getPageNumber() {
		int pageNumber = 0;
		if (totalRecords % pageSize == 0)
			pageNumber = totalRecords / pageSize;
		else
			pageNumber = 1 + totalRecords / pageSize;

		return pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	/**
	 * first row count of current page, start from 1
	 * 
	 * @return
	 */
	public int getFirstRow() {
		int pageNumber = getPageNumber();
		if (pageNumber > 0) {
			page = page > pageNumber || page == -1 ? pageNumber : page;
		}
		return (page - 1) * pageSize + 1;
	}

	/**
	 * last row count of current page
	 * 
	 * @return
	 */
	public int getLastRow() {
		return page == getPageNumber() ? getTotalRecords() : page * pageSize;
	}

	public int getPreviousPage() {
		return page > 1 ? page - 1 : page;
	}

	public int getNextPage() {
		return page < getPageNumber() ? page + 1 : page;
	}

	public int getBlocks() {
		if (this.getPageNumber() % this.numbersPerBlock == 0) {
			return this.getPageNumber() / this.numbersPerBlock;
		} else {
			return 1 + this.getPageNumber() / this.numbersPerBlock;
		}
	}

	public int getBlock() {
		if (this.getPage() % this.numbersPerBlock == 0) {
			return this.getPage() / this.numbersPerBlock;
		} else {
			return 1 + this.getPage() / this.numbersPerBlock;
		}
	}

	public int getNumbersPerBlock() {
		return numbersPerBlock;
	}

	public void setNumbersPerBlock(int numberPerBlock) {
		this.numbersPerBlock = numberPerBlock;
	}

	public int getPageOfPrevBlock() {
		if (this.getBlock() > 1) {
			return (this.getBlock() - 1) * this.getNumbersPerBlock();
		} else {
			return 1;
		}
	}

	public int getPageOfNextBlock() {
		if (this.getBlock() < this.getBlocks()) {
			return this.getBlock() * this.getNumbersPerBlock() + 1;
		} else {
			return this.getTotalRecords();
		}
	}

	public String getContainerId() {
		return containerId;
	}

	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}
}
