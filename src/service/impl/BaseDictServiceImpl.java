package service.impl;

import java.util.List;

import dao.BaseDictDao;
import domain.BaseDict;
import service.BaseDictService;

public class BaseDictServiceImpl implements BaseDictService {

	private BaseDictDao bdd;
	
	public void setBdd(BaseDictDao bdd) {
		this.bdd = bdd;
	}

	@Override
	public List<BaseDict> getListByTypeCode(String dict_type_code) {
		return bdd.getListByTypeCode(dict_type_code);
	}

}
