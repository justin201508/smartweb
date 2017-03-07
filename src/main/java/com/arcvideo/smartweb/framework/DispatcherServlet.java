package com.arcvideo.smartweb.framework;

import com.arcvideo.smartweb.framework.bean.Data;
import com.arcvideo.smartweb.framework.bean.Handler;
import com.arcvideo.smartweb.framework.bean.Param;
import com.arcvideo.smartweb.framework.bean.View;
import com.arcvideo.smartweb.framework.helper.ControllerHelper;
import com.arcvideo.smartweb.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by st@arcvideo.com on 2017/3/7.
 */
@WebServlet(urlPatterns="/*",loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet{

    @Override
    public void init(ServletConfig servletConfig) throws ServletException{
        // 初始化smartweb 框架
        Bootstrap.init();
        ServletContext servletContext = servletConfig.getServletContext();

        //注册JSP servlet
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");

        //注册静态资源的默认Servlet
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");

    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException,IOException{
        //获取请求方法和路径
        String method = request.getMethod().toLowerCase();
        String path = request.getPathInfo();

        Handler handler = ControllerHelper.getHandler(method, path);
        if (handler != null) {
            //获取Controller类和Bean实例
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);

            //创建并初始化请求对象
            Map<String,Object> paramMap = new HashMap<String,Object>();
            Enumeration<String> parameterNames = request.getParameterNames();
            while(parameterNames.hasMoreElements()){
                String paramName = parameterNames.nextElement();
                String paramValue = request.getParameter(paramName);
                paramMap.put(paramName,paramValue);
            }

            //解析请求body
            String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
            if(!StringUtil.isEmpty(body)){
                String[] params = body.split("&");
                if(ArrayUtil.isNotEmpty(params)){
                    for(String param : params){
                       if(!StringUtil.isEmpty(param)){
                           String[] kv = param.split("=");
                           if(ArrayUtil.isNotEmpty(kv) && kv.length == 2){
                               String paramName = kv[0];
                               String paramValue = kv[1];
                               paramMap.put(paramName,paramValue);
                           }
                       }
                    }
                }
            }

            Param param = new  Param(paramMap);
            // 调用Action方法
            Method action = handler.getAction();
            Object result = ReflectionUtil.invoke(controllerBean, action, param);

            if (result instanceof View){
                View view = (View)result;
                if (path.startsWith("/")) {
                    response.sendRedirect(request.getContextPath()+path);
                }
                else{
                    //如果path是view的名称，先讲model中的参数写入reqeust
                    Map<String,Object> model = view.getModel();
                    for(Map.Entry<String,Object> entry : model.entrySet()){
                        request.setAttribute(entry.getKey(),entry.getValue());
                    }

                    //然后转发到JSP目录
                    request.getRequestDispatcher(ConfigHelper.getAppJspPath() + path)
                            .forward(request,response);
                }
            }
            else if(result instanceof Data){
                Data data = (Data)result;
                Object model = data.getModel();
                if (model != null) {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter writer = response.getWriter();
                    String json = JsonUtil.toJson(data);
                    writer.write(json);
                    writer.flush();
                    writer.close();
                }
            }
        }
    }
}
