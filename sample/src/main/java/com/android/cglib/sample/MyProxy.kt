package com.android.cglib.sample

import android.content.Context
import android.util.Log
import com.android.cglib.proxy.Enhancer
import com.android.cglib.proxy.MethodInterceptor
import com.android.cglib.proxy.MethodProxy
import java.io.File
import java.util.Arrays

class MyProxy(private val context: Context, private val original: Any) : MethodInterceptor {
  fun getProxy(): Any {
    val clsName: String = original.javaClass.name.replace(".", "/")
    val clsDir = File(context.cacheDir, clsName)
    if (clsDir.exists()) {
      clsDir.deleteRecursively()
    }
    clsDir.mkdirs()
    val enhancer = Enhancer(context)
    enhancer.setSuperclass(original.javaClass)
    enhancer.setInterceptor(this)
    return enhancer.create(clsDir)
  }

  @Throws(Exception::class)
  override fun intercept(enhancer: Any?, args: Array<Any>?, methodProxy: MethodProxy): Any? {
    Log.i(
      "MyProxy", "methodName:${methodProxy.methodName} " +
          "originalMethodClass:${methodProxy.enhancerMethod} " +
          "proxyMethodClass:${methodProxy.superProxyEnhancerMethod} " +
          "enhancer:${enhancer?.javaClass?.name} " +
          "args:${Arrays.toString(args)}"
    )
    methodProxy.invokeSuper(original, args)
    return null
  }
}