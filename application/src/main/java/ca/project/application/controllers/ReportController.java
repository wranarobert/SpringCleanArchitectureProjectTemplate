package ca.project.application.controllers;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.project.domain.dto.request.LoginRequest;
import ca.project.infrastructure.pdfgenerator.ThymeleafPdfGenerator;

@RestController
@RequestMapping("/api/pdf")
public class ReportController {

	@Autowired
	ThymeleafPdfGenerator pdfGenerator;
	
	@PostMapping(produces = "application/pdf")
	public byte[] testPdf() {
		return pdfGenerator.generatePdf("testreport", Map.of(
		    "name", "Robi ca",
		    "users", Collections.singleton(new LoginRequest("robi", "lozinka"))
		));
	}
	
	@PostMapping(value="export", produces = "application/pdf")
	public byte[] export(@RequestBody String html) {
		return pdfGenerator.generatePdfFromHtml(html);
	}
	
	@PostMapping("test")
	public String testProccessedHtml() {
		return pdfGenerator.test("testreport", Map.of(
		    "name", "Robi ca",
		    "users", Collections.singleton(new LoginRequest("robi", "lozinka"))
		));
	}
}
