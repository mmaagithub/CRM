package dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import domain.Customer;

public interface CustomerDao extends BaseDao<Customer>{

List<Object[]> getIndustryCount();

List<Object[]> getSourceCount();
}
