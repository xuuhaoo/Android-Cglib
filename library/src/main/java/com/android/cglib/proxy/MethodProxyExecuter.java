package com.android.cglib.proxy;

import java.lang.reflect.Method;

public class MethodProxyExecuter {

  @SuppressWarnings({"rawtypes"})
  public static Object executeInterceptor(MethodInterceptor interceptor, Class superClass, String methodName,
      Class[] argsType, Object[] argsValue, Object object) {
    if (interceptor == null) {
      throw new EnhancerProxyException(" did not set method interceptor !!!");
    }
    try {
      MethodProxy methodProxy = new MethodProxy(superClass, methodName, argsType);
      return interceptor.intercept(object, argsValue, methodProxy);
    } catch (Throwable e) {
      throw new EnhancerProxyException(e);
    }
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public static Object executeMethod(Class subClass, String methodName, Class[] argsType, Object[] argsValue, Object object) {
    try {
      Method method = subClass.getDeclaredMethod(methodName + Const.SUBCLASS_INVOKE_SUPER_SUFFIX, argsType);
      return method.invoke(object, argsValue);
    } catch (Throwable e) {
      throw new EnhancerProxyException(e);
    }
  }

}
