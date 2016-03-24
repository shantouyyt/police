package Utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class CharsetFilter implements Filter {

	private String encode = "UTF-8";// Ĭ��UTF-8����

	public void init(FilterConfig filterConfig) throws ServletException {
		String encoding = filterConfig.getInitParameter("encode");
		if (encoding != null) {
			encode = encoding;
		}
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		// ����request����
		request.setCharacterEncoding(encode);
		// ����response��Ӧ��Ϣ
		response.setContentType("text/html;charset=" + encode);
		response.setCharacterEncoding(encode);
		
		chain.doFilter(new CharacterEncodingRequest(request), response);
	}

	public void destroy() {

	}
}

/**
 * ��Get��ʽ���ݵ����������б���
 */
class CharacterEncodingRequest extends HttpServletRequestWrapper {
	private HttpServletRequest request = null;

	public CharacterEncodingRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	/**
	 * �Բ������±���
	 */
	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		if (value == null)
			return null;
		String method = request.getMethod();
		if ("get".equalsIgnoreCase(method)) {
			try {
				value = new String(value.getBytes("ISO8859-1"),
						request.getCharacterEncoding());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return value;
	}

}
