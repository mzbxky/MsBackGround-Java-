package com.ruoyi.framework.security.context;

public class PermissionContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setContext(String permission)
    {
        contextHolder.set(permission);
    }

    public static String getContext()
    {
        return contextHolder.get();
    }
}
