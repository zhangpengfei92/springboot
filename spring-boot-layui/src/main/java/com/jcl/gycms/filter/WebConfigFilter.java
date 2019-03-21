package com.jcl.gycms.filter;

import com.jcl.gycms.uitl.page.PaginationContext;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class WebConfigFilter {

    private String[] filters = {"/index"};

    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    @Bean
    public FilterRegistrationBean testFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("MyFilter");
        registration.setOrder(1);
        return registration;
    }

    public class MyFilter implements Filter {
        @Override
        public void destroy() {
            // TODO Auto-generated method stub
        }

        @Override
        public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)
                throws IOException, ServletException {
                HttpServletResponse response = (HttpServletResponse) sresponse;
                HttpServletRequest req = (HttpServletRequest) srequest;
                /*解决跨域问题*/
                response.setHeader("Access-Control-Allow-Origin","*");
                response.setHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS,DELETE");
                response.setHeader("Access-Control-Max-Age","3600");
                response.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
                /*路径jsp绝对路径*/
                  if(null == req.getSession().getAttribute("path")){
                  String path = req.getContextPath();
                  String basePath = req.getScheme() + "://" + req.getServerName()
                        + ":" + req.getServerPort() + path;
                  req.getSession().setAttribute("path", path);

                }
                /*解决缓存问题*/
                 response.setDateHeader("Expires",-1);
                 response.setHeader("Cache-Control","no-cache");
                 response.setHeader("Pragma","no-cache");
                 /*分页*/
                PaginationContext.setPageNum(getPageNum(req));
                PaginationContext.setPageSize(getPageSize(req));
                try{

                    filterChain.doFilter(srequest, sresponse);
                }catch(Exception e){
                    e.printStackTrace();
                }finally {
                    PaginationContext.removePageNum();
                    PaginationContext.removePageSize();
                }

        }

        @Override
        public void init(FilterConfig arg0) throws ServletException {

        }

        /**
         * 获得pager.offset参数的值
         *
         * @param request
         * @return
         */
        protected int getPageNum(HttpServletRequest request) {
            int pageNum = 1;
            try {
                String pageNums = request.getParameter("page");
                if (pageNums != null) {
                    pageNum = Integer.parseInt(pageNums);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            return pageNum;
        }

        /**
         * 设置默认每页大小
         *
         * @return
         */
        protected int getPageSize(HttpServletRequest request) {
            int pageSize = 10; // 默认每页10条记录
            try {
                String pageSizes = request.getParameter("limit");
                if (pageSizes != null) {
                    pageSize = Integer.parseInt(pageSizes);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            return pageSize;
        }


    }

}
