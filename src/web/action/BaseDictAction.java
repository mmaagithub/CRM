package web.action;


import com.opensymphony.xwork2.ActionSupport;
import domain.BaseDict;
import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;
import service.BaseDictService;

import java.util.List;

public class BaseDictAction extends ActionSupport {

	private String dict_type_code;
	private BaseDictService baseDictService;
	
	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}

	public String getDict_type_code() {
		return dict_type_code;
	}

	public void setDict_type_code(String dict_type_code) {
		this.dict_type_code = dict_type_code;
	}

	@Override
	public String execute() throws Exception {
			List<BaseDict> list = baseDictService.getListByTypeCode(dict_type_code);
			String json = JSONArray.fromObject(list).toString();
			ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
			//json发送到页面
			ServletActionContext.getResponse().getWriter().write(json);
		return null;//不需要Struts2处理结果集
	}

	
}
