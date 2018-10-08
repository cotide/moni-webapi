package com.gold.moni.webapi.handler;

import com.alibaba.fastjson.JSON;
import com.gold.moni.helper.common.api.HttpStatusCode;
import com.gold.moni.helper.common.api.WebResult;
import com.gold.moni.helper.exception.BusinessException;
import com.gold.moni.helper.exception.PowerException;
import com.gold.moni.helper.logging.Log4jUtil;
import com.sun.org.apache.bcel.internal.generic.RET;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;


@Component
public class ExceptionHandler implements HandlerExceptionResolver {


    /**
     * 是否Debug模式
     */
    @Value("${global.debug:false}")
    private boolean isDebug;




    /**
     * 日志服务
     */
    Log4jUtil logger = Log4jUtil.getLogger(this.getClass());

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        // 输出 异常信息
        logger.error("发生异常:", "[异常拦截]"+ getExceptionMsg(ex));

        // 将异常信息转json输出
        this.writeJsonByFilter(
                response,
                this.resolveExceptionCustom(ex),
                null,
                null);
        return new ModelAndView();
    }



    /**
     * 异常信息解析方法
     *
     * @param ex
     */
    private WebResult resolveExceptionCustom(Exception ex) {

        WebResult model;
        String errorMsg = getExceptionMsg(ex,isDebug);
        if(ex instanceof  MethodArgumentNotValidException)
        {
            // 实体验证异常
            model = new WebResult(((MethodArgumentNotValidException) ex)
                    .getBindingResult()
                    .getFieldError()
                    .getDefaultMessage(),
                    HttpStatusCode.RequestDataError);
        }else if (ex instanceof PowerException) {
            // 没有权限的异常
            model =  new WebResult(errorMsg, HttpStatusCode.Unauthorized);
        }
        else if(ex instanceof BusinessException)
        {
            // 业务异常
            model =  new  WebResult(errorMsg,HttpStatusCode.BusinessError);
        }
        else {

            // 未知错误
            model =  new WebResult(errorMsg, HttpStatusCode.InternalServerError);
        }
        return model==null
                ?new WebResult(errorMsg,HttpStatusCode.InternalServerError)
                :model;
    }



    /**
     * 将对象转换成JSON字符串，并响应回前台
     */
    protected void writeJsonByFilter(HttpServletResponse response,
                                     Object object, String[] includesProperties,
                                     String[] excludesProperties) {
        try {
            String json = JSON.toJSONString(object);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            logger.error("An error occurred when object was converted to JSON");
        }
    }

    // region Helper

    private String getExceptionMsg(Exception ex) {
        return getExceptionMsg(ex,true);
    }

    private String getExceptionMsg(Exception ex,boolean isDebug) {

        if(isDebug)
        {
            // Debug模式
            try(StringWriter stack = new StringWriter()){
                ex.printStackTrace(new PrintWriter(stack));
                return String.format("[%s][%s]",
                        (ex.getMessage()==null
                                ?ex.toString()
                                :ex.getMessage()),
                        stack.toString());
            }catch (IOException e) {
                e.printStackTrace();
            }
            return ex.getMessage();
        }else{
            if(ex.getMessage() != null)
            {
                return ex.getMessage();
            }else{
                return ex.toString();
            }
        }
    }

    // endregion
}
