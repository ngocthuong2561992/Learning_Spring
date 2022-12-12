package com.security.config.filters

import java.io.IOException
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MainFilter : Filter {
    @Throws(ServletException::class)
    override fun init(filterConfig: FilterConfig) {
        super.init(filterConfig)
    }

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(
        servletRequest: ServletRequest,
        servletResponse: ServletResponse,
        filterChain: FilterChain
    ) {
        val request = servletRequest as HttpServletRequest
        val response = servletResponse as HttpServletResponse
//        println("MainFilter");
//        String url = request.getRequestURI();
//        if(url.contains("/decrypt")) {
//            response.setStatus(401);
//            return;
//        }
        filterChain.doFilter(request, response)
    }

    override fun destroy() {
        super.destroy()
    }
}