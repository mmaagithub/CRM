package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import dao.CustomerDao;
import domain.Customer;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

	@SuppressWarnings("all")
	@Override
	public List<Object[]> getIndustryCount() {
		
		List<Object[]> list = getHibernateTemplate().execute(new HibernateCallback<List>() {
			//sql查询到2列，每一行的数据都是一个数组。
			String sql="select bd.dict_item_name,COUNT(*) total from cst_customer c,base_dict bd where c.cust_industry=bd.dict_id GROUP BY c.cust_industry;";
			@Override
			public List doInHibernate(Session session)
					throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql);
				return query.list();
			}
		});
		return list;
	}

	@SuppressWarnings("all")
	@Override
	public List<Object[]> getSourceCount() {
		List<Object[]> list = getHibernateTemplate().execute(new HibernateCallback<List>() {
			//sql查询到2列，每一行的数据都是一个数组。
			String sql="select bd.dict_item_name,COUNT(*) source from cst_customer c,base_dict bd where c.cust_source=bd.dict_id GROUP BY c.cust_source";
			@Override
			public List doInHibernate(Session session)
					throws HibernateException {
				SQLQuery query = session.createSQLQuery(sql);
				return query.list();
			}
		});
		return list;
	}

}
