package projectshop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/EncodingFilter/*")
public class EncodingFilter implements Filter {
    private String charSet;
	@Override
    public void init(FilterConfig fConfig) throws ServletException {
	    System.out.println("过滤器已加载");
	    this.charSet = fConfig.getInitParameter("charset");
	}

	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	    request.setCharacterEncoding(charSet);
	    chain.doFilter(request, response);
	}
	@Override
    public void destroy() {
		// TODO Auto-generated method stub
	}
}
