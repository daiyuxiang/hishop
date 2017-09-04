package cn.com.do1.component.common.weixin.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.web.util.WebUtils;

import cn.com.do1.component.common.weixin.util.WxapiConstants;

import com.huojuit.hishop.common.utils.SpringContextHolder;
import com.huojuit.hishop.modules.wx.service.WxUserinfoService;


/**
 * 微信快速登录
 */
public class WxLoginFilter implements Filter {

	private static Logger log = Logger.getLogger(WxLoginFilter.class);
    private String scope;

    @Override
    public void init(FilterConfig config) throws ServletException {
        scope = config.getInitParameter("scope");
        if (scope == null) {
            scope = WxapiConstants.SCOPE_BASE;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        
        String openid = (String) session.getAttribute("openid");
        System.out.println("======WxLoginFilter:"+openid+"=========");
        Object o = request.getAttributeNames();
        // 已经拿到了openid
        if (openid != null) {
            chain.doFilter(request, response);
            return;
        }

        // 获取code
        String code = request.getParameter("code");
        System.out.println("======WxLoginFilter-code:"+code+"=========");
        if (code == null) {
            String url = WxapiConstants.HOME_URL + httpRequest.getServletPath().substring(1);
            if (httpRequest.getQueryString() != null) {
                url += "?" + httpRequest.getQueryString();
            }
            url = URLEncoder.encode(url, "UTF-8");
            System.out.println("======WxLoginFilter-url:"+url+"=========");
            System.out.println(WxapiConstants.getLoginAuthorizeUrl(url, scope));
            WebUtils.issueRedirect(request, response, WxapiConstants.getLoginAuthorizeUrl(url, scope));

            return;
        }

        /*ServletContext servletContext = request.getServletContext(); 
        WebApplicationContext context =   
                WebApplicationContextUtils.getWebApplicationContext(servletContext);  
        WxUserinfoService wxUserinfoFacade = (WxUserinfoService) context.getBean("wxUserinfoService");  */
        
        	//获取openid
        try {
        	WxUserinfoService wxUserInfoService = SpringContextHolder.getBean(WxUserinfoService.class);
            openid = wxUserInfoService.queryOpenid(code);
            System.out.println("##############openid="+openid);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new IOException(e);
        }

        session.setAttribute("openid", openid);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
