package me.BrilZliaN.TinkoffTestTask.db;

import me.BrilZliaN.TinkoffTestTask.Application;

public interface ApplicationRepository {

	public Integer insertApplication(Application application);

	public Integer updateApplicationById(Application application);

	public Integer deleteApplicationById(Application application);

	public Application selectApplicationById(Integer id);
	
	public Application selectApplicationByContactId(Integer contactId);

}
