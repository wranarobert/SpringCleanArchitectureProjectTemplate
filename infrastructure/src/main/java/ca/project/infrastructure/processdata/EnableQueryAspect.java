package ca.project.infrastructure.processdata;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.extern.log4j.Log4j2;

@Aspect
@Component
@Log4j2
public class EnableQueryAspect {
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	@Around("@annotation(EnableQuery)")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod();
		var groupByProperty = method.getParameterCount();
 		
 		System.out.println("Query checked " + groupByProperty);
	    Object returnedObject = pjp.proceed();
	       	 		
	    return returnedObject;
	}

 	@AfterReturning(
		 pointcut="@annotation(EnableQuery)",
		 returning="returnedObject"
 	)
	public void doAccessCheck(Object returnedObject) throws IOException, InterruptedException, ExecutionException {
 		// Reqest query manager
 		var groupByProperty = "id"; //getQueryParameter("groupBy");
 		
 		if(groupByProperty == null) {
 			return;
 		}
 		System.out.println("Query checked");
 		
 		// Return type manager
 		Object returnObject = returnedObject;
 		
 		if(returnObject instanceof CompletableFuture) {
 			System.out.println("async checked");
 			returnObject = ((CompletableFuture<?>) returnedObject).get();
 			return;
 		}
 		
 		if(!(returnObject instanceof List<?>)) {
		    return;
		}
 		System.out.println("It is a list checked");
 		List<?> list = (List<?>) returnObject;
 		
 		// Proccess data manager
	    ObjectMapper mapper = new ObjectMapper();
	    
	    ArrayNode jsonArray = mapper.valueToTree(list);
	    
	    JsonNode groupingJsonObject = mapper.createObjectNode();
	    
	    for (JsonNode jsonNode : jsonArray) {
	    	((ObjectNode) groupingJsonObject).set(jsonNode.get(groupByProperty).asText(), jsonNode);
	    }
		
	    // Output data manager
		var o = response.getOutputStream();
		
		o.flush();
		o.write(groupingJsonObject.toString().getBytes());
		o.close();
 	}
 	
}
