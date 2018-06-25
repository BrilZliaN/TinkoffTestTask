package me.BrilZliaN.TinkoffTestTask;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.BrilZliaN.TinkoffTestTask.db.ApplicationService;

@RestController
public class ApiController {
	
	private final ApplicationService applicationService;

	@Autowired
	public ApiController(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	@GetMapping("/json/getApplication")
	public ResponseEntity<?> getApplicationJSON(@RequestParam Integer contactId) {
		return this.getApplication(contactId, ResponseType.JSON);

	}
	
	@GetMapping("/xml/getApplication")
	public ResponseEntity<?> getApplicationXML(@RequestParam Integer contactId) {
		return this.getApplication(contactId, ResponseType.XML);
	}
	
	private ResponseEntity<?> getApplication(Integer contactId, ResponseType type) {
		Optional<Application> o = applicationService.getApplicationByContactId(contactId); //TODO
		String response = null;
		if (o.isPresent()) {
			response = type.processObject(o.get());
		} else {
			response = type.processObject(new Error("No applications were found for this contact id"));
		}
		return generateAnswer(response);
	}
	
	private ResponseEntity<?> generateAnswer(String response) {
		if (response != null) {
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.badRequest().body(new Error("Internal server error"));
		}
	}

}
