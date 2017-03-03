package com.alibaba.dubbo.governance.web.common.utils;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.registry.common.domain.UserExtend;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Erlei Chen on 2017/3/3.
 */
public class PrivilegesFilterUtils {

    /**
     * 权限过滤。目前只支持application、service规则过滤
     *
     * @param user
     * @param concurrentMap
     * @return
     */
    public static ConcurrentMap<String, ConcurrentMap<String, Map<Long, URL>>> privilegesFilter(UserExtend user, ConcurrentMap<String, ConcurrentMap<String, Map<Long, URL>>> concurrentMap) {
        ConcurrentMap<String, ConcurrentMap<String, Map<Long, URL>>> newConcurrentMap = new ConcurrentHashMap<String, ConcurrentMap<String, Map<Long, URL>>>();
        if (concurrentMap == null) return newConcurrentMap;
        ConcurrentMap<String, Map<Long, URL>> providers = new ConcurrentHashMap<String, Map<Long, URL>>();
        ConcurrentMap<String, Map<Long, URL>> consumers = new ConcurrentHashMap<String, Map<Long, URL>>();
        ConcurrentMap<String, Map<Long, URL>> configurators = new ConcurrentHashMap<String, Map<Long, URL>>();
        ConcurrentMap<String, Map<Long, URL>> routers = new ConcurrentHashMap<String, Map<Long, URL>>();
        ConcurrentMap<String, Map<Long, URL>> tempProviders = new ConcurrentHashMap<String, Map<Long, URL>>();
        ConcurrentMap<String, Map<Long, URL>> tempConsumers = new ConcurrentHashMap<String, Map<Long, URL>>();
        Map<Long, URL> tempMap;
        //检索providers
        if (concurrentMap.get(Constants.PROVIDERS_CATEGORY) != null) {
            for (Map.Entry<String, Map<Long, URL>> provider : concurrentMap.get(Constants.PROVIDERS_CATEGORY).entrySet()) {
                String key = provider.getKey();
                if (user.hasServicePrivilege(key)) {
                    Map<Long, URL> value = provider.getValue();
                    tempMap = new HashMap<Long, URL>();
                    for (Map.Entry<Long, URL> e1 : value.entrySet()) {
                        URL u = e1.getValue();
                        String application = u.getParameter(Constants.APPLICATION_KEY);
                        if (!StringUtils.isBlank(application) && user.hasAppPrivilege(application)) {
                            tempMap.put(e1.getKey(), u);
                        }
                    }
                    if (tempMap.size() > 0) {
                        providers.put(key, tempMap);
                    }
                }
            }
        }
        //检索consumers
        if (concurrentMap.get(Constants.CONSUMERS_CATEGORY) != null) {
            for (Map.Entry<String, Map<Long, URL>> consumer : concurrentMap.get(Constants.CONSUMERS_CATEGORY).entrySet()) {
                String key = consumer.getKey();
                if (user.hasServicePrivilege(key)) {
                    Map<Long, URL> value = consumer.getValue();
                    tempMap = new HashMap<Long, URL>();
                    for (Map.Entry<Long, URL> e1 : value.entrySet()) {
                        URL u = e1.getValue();
                        String application = u.getParameter(Constants.APPLICATION_KEY);
                        if (!StringUtils.isBlank(application) && user.hasAppPrivilege(application)) {
                            tempMap.put(e1.getKey(), u);
                        }
                    }
                    if (tempMap.size() > 0) {
                        consumers.put(key, tempMap);
                    }
                }
            }
        }

        //补充检索providers
        if (concurrentMap.get(Constants.PROVIDERS_CATEGORY) != null && consumers != null) {
            for (Map.Entry<String, Map<Long, URL>> provider : concurrentMap.get(Constants.PROVIDERS_CATEGORY).entrySet()) {
                String key = provider.getKey();
                for (String consumer : consumers.keySet()) {
                    if (consumer.equalsIgnoreCase(key)) {
                        tempProviders.put(key, provider.getValue());
                    }
                }
            }
        }

        //补充检索consumers
        if (concurrentMap.get(Constants.CONSUMERS_CATEGORY) != null && providers != null) {
            for (Map.Entry<String, Map<Long, URL>> consumer : concurrentMap.get(Constants.CONSUMERS_CATEGORY).entrySet()) {
                String key = consumer.getKey();
                for (String provider : providers.keySet()) {
                    if (provider.equalsIgnoreCase(key)) {
                        tempConsumers.put(key, consumer.getValue());
                    }
                }
            }
        }

        //合并检索结果
        providers.putAll(tempProviders);
        consumers.putAll(tempConsumers);

        //检索路由规则
        if (concurrentMap.get(Constants.ROUTERS_CATEGORY) != null) {
            for (Map.Entry<String, Map<Long, URL>> router : concurrentMap.get(Constants.ROUTERS_CATEGORY).entrySet()) {
                String key = router.getKey();
                for (String provider : providers.keySet()) {
                    if (provider.equalsIgnoreCase(key)) {
                        routers.put(key, router.getValue());
                    }
                }
            }
        }
        //检索动态配置
        if (concurrentMap.get(Constants.CONFIGURATORS_CATEGORY) != null) {
            for (Map.Entry<String, Map<Long, URL>> configurator : concurrentMap.get(Constants.CONFIGURATORS_CATEGORY).entrySet()) {
                String key = configurator.getKey();
                for (String provider : providers.keySet()) {
                    if (provider.equalsIgnoreCase(key)) {
                        configurators.put(key, configurator.getValue());
                    }
                }
            }
        }

        //封装返回结果
        newConcurrentMap.put(Constants.PROVIDERS_CATEGORY, providers);
        newConcurrentMap.put(Constants.CONSUMERS_CATEGORY, consumers);
        newConcurrentMap.put(Constants.ROUTERS_CATEGORY, routers);
        newConcurrentMap.put(Constants.CONFIGURATORS_CATEGORY, configurators);

        return newConcurrentMap;
    }

}
