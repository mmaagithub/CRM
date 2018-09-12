package web.action;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import service.CustomerService;
import utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import domain.Customer;

public class CustomerAction extends ActionSupport implements
		ModelDriven<Customer> {
	// 上传图片
	// private File photo;
	// 文件名自动封装
	// private String photoFileName;

	// public String getPhotoFileName() {
	// return photoFileName;
	// }
	//
	// public void setPhotoFileName(String photoFileName) {
	// this.photoFileName = photoFileName;
	// }
	//
	// public File getPhoto() {
	// return photo;
	// }
	//
	// public void setPhoto(File photo) {
	// this.photo = photo;
	// }

	private Customer customer = new Customer();

	public Customer getCustomer() {
		return customer;
	}

	public CustomerService getCs() {
		return cs;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	private CustomerService cs;

	private Integer currentPage;
	private Integer pageSize;

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setCs(CustomerService cs) {
		this.cs = cs;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String list() throws Exception {
		// 封装离线查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(Customer.class);
		// 判断并封装参数
		if (StringUtils.isNotBlank(customer.getCust_name())) {
			dc.add(Restrictions.like("cust_name", "%" + customer.getCust_name()
					+ "%"));
		}
		// 1 调用Service查询分页数据(PageBean)
		PageBean pb = cs.getPageBean(dc, currentPage, pageSize);
		// 2 将PageBean放入request域,转发到列表页面显示
		ActionContext.getContext().put("pageBean", pb);
		return "list";
	}

	// add.jsp页面提交后进行保存添加
	public String add() throws Exception {
		// if(photo!=null){
		// System.out.println("文件的名字："+photoFileName);
		// photo.renameTo(new
		// File("C:/Users/Mr.MA/Desktop/"+photoFileName+".jpg"));
		// }
		// 1.调用service，保存customer对象
		cs.save(customer);
		// 2.重定向到客户列表界面
		return "toList";
	}

	// 在修改时拿到对象在add.jsp页面回显
	public String toEdit() throws Exception {

		// 1.调用service，根据id获得customer对象
		Customer c = cs.getById(customer.getCust_id());
		// 2.customer封装到request域
		ActionContext.getContext().put("customer", c);
		return "edit";
	}

	public String delete() throws Exception {
		Customer c = cs.getById(customer.getCust_id());
		cs.delete(c);
		return "toList";
	}

	public String industryCount() throws Exception {
		List<Object[]> list = cs.getIndustryCount();
		// 2.customer封装到request域
		ActionContext.getContext().put("list", list);
		return "industryCount";
	}

	public String sourceCount() throws Exception {
		List<Object[]> list = cs.getSourceCount();
		// 2.customer封装到request域
		ActionContext.getContext().put("list", list);
		return "sourceCount";
	}

	@Override
	public Customer getModel() {
		return customer;
	}

}
