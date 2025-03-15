package common.bean;

import java.util.HashMap;
import java.util.List;
/**
 * 封装信息查询页面上相关的分页信息
 * 1、视图层、控制层、模型层之间传递分页信息；
 * @author dell
 *
 */
public class PageInforBean {
	/**
	 * 当前页显示记录数,默认为每页显示10条
	 */
	private int showCount = 2; 

	/**
	 * 当前页开始记录数
	 */
	private int fromCount = 0;
	/**
	 * 记录总条数
	 */
	private int totalNumber = 0;
	/**
	 * 当前页数据的列表
	 */
	private List list = null;
	/**
	 * 总页数
	 */
	private int totalPage = 0;
	/**
	 * 当前页页码
	 */
	private int currentPage = 1;

	/**
	 * 存储检索条件
	 */
	private HashMap<String,Object> hm;

	public int getShowCount() {
		return showCount;
	}

	public void setShowCount(int showCount) {
		this.showCount = showCount;
	}

	public int getFromCount() {
		return fromCount;
	}

	public void setFromCount(int fromCount) {
		this.fromCount = fromCount;
	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public HashMap<String, Object> getHm() {
		return hm;
	}

	public void setHm(HashMap<String, Object> hm) {
		this.hm = hm;
	}

	
	
}
