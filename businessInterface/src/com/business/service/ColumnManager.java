package com.business.service;

import java.util.List;

import com.business.entity.Company;

public interface ColumnManager {

	List<Company> loadColumnList(int rows, int page, Integer isCheck, Integer columnAuthority);

}
