package me.BrilZliaN.TinkoffTestTask.db;

import java.util.Optional;

import me.BrilZliaN.TinkoffTestTask.Application;

public interface ApplicationService {

	public boolean addApplication(Application application);

	public boolean updateApplicationById(Application application);

	public boolean deleteApplicationById(Application application);

	public Optional<Application> getApplicationById(Integer applicationId);

	public Optional<Application> getApplicationByContactId(Integer contactId);

}
