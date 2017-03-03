/**
 * Project: dubbo.registry.server-1.1.0-SNAPSHOT
 * <p>
 * File Created at 2010-6-28
 * <p>
 * Copyright 1999-2010 Alibaba.com Croporation Limited.
 * All rights reserved.
 * <p>
 * This software is the confidential and proprietary information of
 * Alibaba Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Alibaba.com.
 */
package com.alibaba.dubbo.governance.service.impl;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.governance.sync.RegistryServerSync;
import com.alibaba.dubbo.governance.web.common.utils.PrivilegesFilterUtils;
import com.alibaba.dubbo.governance.web.util.WebConstants;
import com.alibaba.dubbo.registry.RegistryService;
import com.alibaba.dubbo.registry.common.domain.UserExtend;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * IbatisDAO
 *
 * @author william.liangf
 */
public class AbstractService {

    protected static final Logger logger = LoggerFactory.getLogger(AbstractService.class);

    @Autowired
    private RegistryServerSync sync;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    protected RegistryService registryService;

    public ConcurrentMap<String, ConcurrentMap<String, Map<Long, URL>>> getRegistryCache() {
        UserExtend user = (UserExtend) request.getSession().getAttribute(WebConstants.CURRENT_USER_KEY);
        return PrivilegesFilterUtils.privilegesFilter(user, sync.getRegistryCache());
//        return sync.getRegistryCache();
    }


}
