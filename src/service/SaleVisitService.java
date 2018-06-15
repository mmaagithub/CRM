package service;

import org.hibernate.criterion.DetachedCriteria;

import utils.PageBean;
import domain.SaleVisit;

public interface SaleVisitService {

	void save(SaleVisit saleVisit);

	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);

	SaleVisit getById(String visit_id);

	void update(SaleVisit saleVisit);

	void delete(SaleVisit saleVisit);
}
