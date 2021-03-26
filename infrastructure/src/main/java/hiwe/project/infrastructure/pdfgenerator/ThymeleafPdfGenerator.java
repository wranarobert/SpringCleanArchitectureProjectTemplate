package ca.project.infrastructure.pdfgenerator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.DocumentException;

@Component
public class ThymeleafPdfGenerator {
	@Autowired
	ReportProperties reportProperties;
	
	@Autowired
	TemplateEngine templateEngine;
	
	public String test(String templateName, Map<String, Object> variables) {

	    var htmlTemplate = processThymeleafTemplate(templateName, variables);
	 
		return htmlTemplate;
	}
	
	public byte[] generatePdf(String templateName, Map<String, Object> variables) {

	    var htmlTemplate = processThymeleafTemplate(templateName, variables);
	    
	    var pdfByteArray = generatePdfFromHtml(htmlTemplate);
	 
		return pdfByteArray;
	}
	
	private String processThymeleafTemplate(String templateName, Map<String, Object> variables) {
	    Context context = new Context();
	    
	    variables.forEach((name, value) -> context.setVariable(name, value));
	 
	    return templateEngine.process(templateName, context);
	}
	
	public byte[] generatePdfFromHtml(String rawHtml) {
		try {
			String html = prettyPrintHTML(rawHtml);
			System.out.println(html);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); 
		 
		    ITextRenderer renderer = new ITextRenderer();
		    renderer.setDocumentFromString(html, new ClassPathResource("/").getURL().toExternalForm());
		    renderer.layout();
		    
			renderer.createPDF(outputStream);
				
			return outputStream.toByteArray();
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Report exception");
		}
	}
	
	public String prettyPrintHTML(String rawHTML)
	{    
		/*
	    Tidy tidy = new Tidy();
	    tidy.setXHTML(true);
	    tidy.setQuoteNbsp(true);
	    tidy.setPrintBodyOnly(true);
	    tidy.setTidyMark(false);

	    // HTML to DOM
	    Document htmlDOM = tidy.parseDOM(new ByteArrayInputStream(rawHTML.getBytes()), null);

	    // Pretty Print
	    OutputStream out = new ByteArrayOutputStream();
	    tidy.pprint(htmlDOM, out);

	    return out.toString();
	    */
		
		String html = rawHTML;

        Document document = Jsoup.parse(html);   // pretty print HTML
        
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml); 
        document.outputSettings().escapeMode(org.jsoup.nodes.Entities.EscapeMode.xhtml);
        
        return document.html();
	}
}
