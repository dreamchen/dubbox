/**
 * Function:
 * <p>
 * File Created at 2010-11-17
 * $Id: Menu.java 185206 2012-07-09 03:06:37Z tony.chenl $
 * <p>
 * Copyright 2009 Alibaba.com Croporation Limited.
 * All rights reserved.
 */
package com.alibaba.dubbo.governance.web.home.module.control;

import com.alibaba.citrus.service.requestcontext.parser.CookieParser;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.governance.sync.RegistryServerSync;
import com.alibaba.dubbo.governance.web.common.i18n.LocaleUtil;
import com.alibaba.dubbo.governance.web.common.pulltool.RootContextPath;
import com.alibaba.dubbo.governance.web.util.WebConstants;
import com.alibaba.dubbo.registry.common.domain.User;
import com.alibaba.dubbo.registry.common.domain.UserExtend;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * @author guanghui.shigh
 * @author ding.lid
 * @author tony.chenl
 */
public class Menu {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    ServletContext servletcontext;

    @Autowired
    RegistryServerSync registryServerSync;

    public void execute(HttpSession session, Context context, CookieParser parser) {

        User user = (UserExtend) session.getAttribute(WebConstants.CURRENT_USER_KEY);
        if (user != null) context.put("operator", user.getUsername());

        RootContextPath rootContextPath = new RootContextPath(request.getContextPath());
        context.put("rootContextPath", rootContextPath);
        if (!context.containsKey("bucLogoutAddress")) {
            context.put("bucLogoutAddress", rootContextPath.getURI("logout"));
        }
        if (!context.containsKey("helpUrl")) {
            context.put("helpUrl", "http://code.alibabatech.com/wiki/display/dubbo");
        }
        context.put(WebConstants.CURRENT_USER_KEY, user);
        String locale = parser.getString("locale");
        if (StringUtils.isBlank(locale)) {
            locale = StringUtils.isBlank(user.getLocale()) ? "zh" : user.getLocale();
            Locale newLocale = Locale.SIMPLIFIED_CHINESE;
            if ("en".equals(locale)) {
                newLocale = Locale.ENGLISH;
            } else if ("zh".equals(locale)) {
                newLocale = Locale.SIMPLIFIED_CHINESE;
            } else if ("zh_TW".equals(locale)) {
                newLocale = Locale.TRADITIONAL_CHINESE;
            }
            LocaleUtil.setLocale(newLocale);
        }
        context.put("language", locale);
        context.put("registryServerSync", registryServerSync);
    }
}
