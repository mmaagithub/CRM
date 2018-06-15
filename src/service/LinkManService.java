package service;

import org.hibernate.criterion.DetachedCriteria;

import utils.PageBean;
import domain.LinkMan;

public interface LinkManService {

	void save(LinkMan linkMan);

	PageBean getPageBean(DetachedCriteria dc, Integer currentPage,
			Integer pageSize);
	//根据id获得linkman对象
	LinkMan getById(Long lkm_id);

	void delete(LinkMan linkMan);
}
