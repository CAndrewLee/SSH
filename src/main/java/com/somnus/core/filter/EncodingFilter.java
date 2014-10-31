package com.somnus.core.filter;

import java.io.IOException;   
import java.util.HashMap;
import javax.servlet.Filter;   
import javax.servlet.FilterChain;   
import javax.servlet.FilterConfig;   
import javax.servlet.ServletException;   
import javax.servlet.ServletRequest;   
import javax.servlet.ServletResponse;   
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.somnus.service.BookService;
  
public class EncodingFilter implements Filter {   
  
    protected FilterConfig config;   
  
    protected String Encoding = null;   
  
    public void init(FilterConfig config) throws ServletException {   
  
        this.config = config;   
        this.Encoding = config.getInitParameter("Encoding");   
        WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
        BookService service = (BookService)applicationContext.getBean("bookService");
        try {
			int count= service.queryBookCount(new HashMap(){});
			System.out.println("-----------总数据量-----------："+count);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }   
  
    public void doFilter(ServletRequest request, ServletResponse response,   
            FilterChain chain) throws IOException, ServletException {   
  
        if (request.getCharacterEncoding() == null) {   
            if (Encoding != null) {   
                request.setCharacterEncoding(Encoding);   
                response.setCharacterEncoding(Encoding);   
            }   
        }   
        chain.doFilter(request, response);   
    }   
  
    public void destroy() {   
    }   
  
}  


