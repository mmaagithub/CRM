package service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import utils.PageBean;
import domain.Customer;

public interface CustomerService {
	// 分页业务方法
	PageBean getPageBean(DetachedCriteria dc, Integer currentPage,
			Integer pageSize);

	// 添加保存客户
	void save(Customer customer);

	Customer getById(Long cust_id);

	List<Object[]> getIndustryCount();

	List<Object[]> getSourceCount();

	void delete(Customer customer);

}
