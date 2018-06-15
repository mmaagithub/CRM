package test;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import service.UserService;
import dao.UserDao;
import domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")

public class HibernateTest {
	@Resource(name="sessionFactory")
	private SessionFactory sf;
	
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Test
	public void fun1(){
		Configuration conf = new Configuration().configure();
		
		SessionFactory sf = conf.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		
		User u = new User();
		u.setUser_code("tom");
		u.setUser_name("tangmu");
		u.setUser_password("1234");
		u.setUser_state('1');
		session.save(u);
		
		tx.commit();
		session.close();
		sf.close();
		
	}
	
	@Test
	public void fun2(){
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		
		User u = new User();
		u.setUser_code("jerry3");
		u.setUser_name("jerry3");
		u.setUser_password("1122");
		u.setUser_state('1');
		session.save(u);
		
		tx.commit();
		session.close();
	}
	public void setUd(UserDao ud) {
		this.ud = ud;
	}
	@Resource(name="userDao")
	private UserDao ud;
	@Test
	public void fun3(){
		ud.getByUserCode("tom");
		System.out.println(ud);
	}
	@Resource(name="userService")
	private UserService us;
	public void setUs(UserService us) {
		this.us = us;
	}

	@Test
	public void fun4(){
		User u = new User();
		u.setUser_code("hqy2");
		u.setUser_name("2");
		u.setUser_password("12342");
		u.setUser_state('1');
		us.saveUser(u);
	}
}
