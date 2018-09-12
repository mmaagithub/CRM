package service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import service.CustomerService;
import utils.PageBean;
import dao.CustomerDao;
import domain.Customer;

public class CustomerServiceImpl implements CustomerService{

	private CustomerDao cd;

	public void setCd(CustomerDao cd) {
		this.cd = cd;
	}

	@Override
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage,
			Integer pageSize) {
		//1 调用Dao查询总记录数
		Integer totalCount = cd.getTotalCount(dc);
		//2 创建PageBean对象
		PageBean pb = new PageBean(currentPage,totalCount,pageSize);
		//3 调用Dao查询分页列表数据
		List<Customer> list = cd.getPageList(dc,pb.getStart(),pb.getPageSize());
		//4 列表数据放入pageBean中.并返回
		pb.setList(list);
		return pb;
	}

	@Override
	public void save(Customer customer) {
		//维护customer与数据字典之间的关系
		//调用Dao保存客户
		cd.saveOrUpdate(customer);
	}

	@Override
	public Customer getById(Long cust_id) {
		return cd.getById(cust_id);
	}

	@Override
	public List<Object[]> getIndustryCount() {
		return cd.getIndustryCount();
	}

	@Override
	public List<Object[]> getSourceCount() {
		return cd.getSourceCount();
	}

	@Override
	public void delete(Customer customer) {
		cd.delete(customer);
	}

}
