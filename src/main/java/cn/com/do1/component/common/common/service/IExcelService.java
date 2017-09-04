package cn.com.do1.component.common.common.service;

public interface IExcelService {
	/**
	 * 对导入文件对应的属性进行校验
	 * @param obj 校验的对象
	 * @return 校验是否通过
	 */
	public boolean valid(Object obj);
}
