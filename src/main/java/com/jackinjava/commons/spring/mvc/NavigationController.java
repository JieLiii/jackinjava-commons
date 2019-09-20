package com.jackinjava.commons.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 访问页面的控制器
 *
 * @author LiJie on 2019/9/19
 */

@RequestMapping("/nav")
public class NavigationController {
    public NavigationController() {
    }

    @GetMapping({"/**"})
    public String index(HttpServletRequest request) {
        return extractFilepath(request);
    }

    private static String extractFilepath(HttpServletRequest request) {
        String path = getPathWithinHandlerMapping(request);
        String bestMatchPattern = (String)request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
        String result = (new AntPathMatcher()).extractPathWithinPattern(bestMatchPattern, path);
        try {
            result = URLDecoder.decode(result, "UTF-8");
        } catch (UnsupportedEncodingException var5) {
        }
        return result;
    }

    private static String getPathWithinHandlerMapping(HttpServletRequest request) {
        return (String)request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
    }
}
