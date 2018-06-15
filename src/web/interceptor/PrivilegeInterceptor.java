package web.interceptor;

import java.util.Map;

import org.aopalliance.intercept.Invocation;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import domain.User;

public class PrivilegeInterceptor extends MethodFilterInterceptor {

	@Override
	//不校验登录方法和注册方法
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//获得session
		Map<String, Object> session = ActionContext.getContext().getSession();
		//获得user对象
		User user = (User) session.get("user");
		//判断标识是否存在
		if(user!=null){
			//存在放行
			return invocation.invoke();
		} else{
			return "toLogin";
		}
	}
}
