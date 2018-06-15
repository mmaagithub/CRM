package service;

import java.util.List;

import domain.BaseDict;

public interface BaseDictService {
	//查找数据字典对象
	List<BaseDict> getListByTypeCode(String dict_type_code);

}
