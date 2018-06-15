package service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import dao.SaleVisitDao;
import domain.LinkMan;
import domain.SaleVisit;
import service.SaleVisitService;
import utils.PageBean;

public class SaleVisitServiceImpl implements SaleVisitService {

	private SaleVisitDao svd;
	@Override
	public void save(SaleVisit saleVisit) {
		svd.save(saleVisit);
	}
	public void setSvd(SaleVisitDao svd) {
		this.svd = svd;
	}

	@Override
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
		//1 调用Dao查询总记录数
		Integer totalCount = svd.getTotalCount(dc);
		//2 创建PageBean对象
		PageBean pb = new PageBean(currentPage, totalCount, pageSize);
		//3 调用Dao查询分页列表数据
		List<SaleVisit> list = svd.getPageList(dc,pb.getStart(),pb.getPageSize());
		//4 列表数据放入pageBean中.并返回
		pb.setList(list);
		return pb;
	}
	@Override
	public SaleVisit getById(String visit_id) {
		return svd.getById(visit_id);
	}
	@Override
	public void update(SaleVisit saleVisit) {
		svd.update(saleVisit);
	}
	@Override
	public void delete(SaleVisit saleVisit) {
		svd.delete(saleVisit);
	}

}
