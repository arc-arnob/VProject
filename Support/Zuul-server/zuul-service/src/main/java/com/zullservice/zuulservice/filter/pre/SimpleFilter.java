package com.zullservice.zuulservice.filter.pre;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.ZuulFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleFilter extends ZuulFilter {

  private static Logger log = LoggerFactory.getLogger(SimpleFilter.class);

  @Override
  public String filterType() {
    return "pre";
  }

  @Override
  public int filterOrder() {
    return 1;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() {
    RequestContext ctx = RequestContext.getCurrentContext();
    StringBuffer strLog = new StringBuffer();
    strLog.append("\n------ Info For you------\n");
    strLog.append(String.format("Server: %s Metodo: %s Path: %s \n", ctx.getRequest().getServerName(), ctx.getRequest().getMethod(),
    ctx.getRequest().getRequestURI()));
    Enumeration < String > enume = ctx.getRequest().getHeaderNames();
    String header;
    while (enume.hasMoreElements()) {
    header = enume.nextElement();
    strLog.append(String.format("Headers: %s = %s \n", header, ctx.getRequest().getHeader(header)));
    };
    log.info(strLog.toString());
    return null;
  }

}