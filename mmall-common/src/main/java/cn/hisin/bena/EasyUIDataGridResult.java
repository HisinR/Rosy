package cn.hisin.bena;

import java.io.Serializable;
import java.util.List;

/*
 * 分页信息
 * 
 */
public class EasyUIDataGridResult implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 48569L;
	private Long total;//总记录数
	private List rows;//一页信息
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	@Override
	public String toString() {
		return "EasyUIDataGridResult [total=" + total + ", rows=" + rows + "]";
	}
	
	
}
