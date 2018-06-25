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
	public ResponseEntity<?> getApplicationJSON(@RequestParam(required = false) String contactId) {
		return this.getApplication(contactId, ResponseType.JSON);

	}

	@GetMapping("/xml/getApplication")
	public ResponseEntity<?> getApplicationXML(@RequestParam(required = false) String contactId) {
		return this.getApplication(contactId, ResponseType.XML);
	}

	private ResponseEntity<?> getApplication(String contact, ResponseType type) {
		try {
			if (contact == null) { // check if contactId is provided
				return generateAnswer(type.processObject(new Error("Parameter 'contactId' should be provided")));
			}

			int contactId = -1;
			try { // check if it is an integer and parse it manually (spring can do that though)
				contactId = Integer.parseInt(contact);
			} catch (NumberFormatException e) {
				return generateAnswer(type.processObject(new Error("Parameter 'contactId' should be an integer")));
			}

			Optional<Application> object = applicationService.getApplicationByContactId(contactId);
			String response = null;
			if (object.isPresent()) {
				response = type.processObject(object.get());
			} else {
				response = type.processObject(new Error("No applications were found for this contact id"));
			}

			return generateAnswer(response);
		} catch (Exception e) {
			e.printStackTrace();
			// in case of any error
			return ResponseEntity.badRequest().body(new Error("Internal server error"));
		}
	}

	private ResponseEntity<?> generateAnswer(String response) {
		if (response != null) {
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.badRequest().body(new Error("Internal server error"));
		}
	}

}
