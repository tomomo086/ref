package po.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


@WebFilter(filterName ="CharacterEncodingFilter",urlPatterns = "/*")
public class CharacterEncodingFilter implements Filter {


    public CharacterEncodingFilter() {

    }


    @Override
	public void destroy() {

	}


	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {

		arg0.setCharacterEncoding("utf-8");
		arg2.doFilter(arg0,arg1);
	}


	public void init(FilterConfig arg0) throws ServletException {

	}

}
