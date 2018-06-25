package me.BrilZliaN.TinkoffTestTask.db;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.BrilZliaN.TinkoffTestTask.Application;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	private final ApplicationRepository applicationRepository;

	@Autowired
	public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
		this.applicationRepository = applicationRepository;
	}

	@Override
	public Optional<Application> getApplicationById(Integer applicationId) {
		return Optional.ofNullable(applicationRepository.selectApplicationById(applicationId));
	}

	@Override
	public Optional<Application> getApplicationByContactId(Integer contactId) {
		return Optional.ofNullable(applicationRepository.selectApplicationByContactId(contactId));
	}

	@Override
	@Transactional
	public boolean addApplication(Application application) {
		return applicationRepository.insertApplication(application) > 0;
	}

	@Override
	@Transactional
	public boolean updateApplicationById(Application application) {
		return applicationRepository.updateApplicationById(application) > 0;
	}

	@Override
	@Transactional
	public boolean deleteApplicationById(Application application) {
		return applicationRepository.deleteApplicationById(application) > 0;
	}

}
