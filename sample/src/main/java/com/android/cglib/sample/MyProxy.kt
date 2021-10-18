package com.android.cglib.sample

import android.content.Context
import android.util.Log
import com.android.cglib.proxy.Enhancer
import com.android.cglib.proxy.MethodInterceptor
import com.android.cglib.proxy.MethodProxy
import java.util.Arrays

class MyProxy(private val context: Context) : MethodInterceptor {
  fun getProxy(cls: Class<*>?): Any {
    val e = Enhancer(context)
    e.setSuperclass(cls)
    e.setInterceptor(this)
    return e.create()
  }

  @Throws(Exception::class)
  override fun intercept(enhancer: Any?, args: Array<Any>?, methodProxy: MethodProxy): Any? {
    Log.i(
      "MyProxy", "methodName:${methodProxy.methodName} " +
          "originalMethodClass:${methodProxy.originalMethod} " +
          "proxyMethodClass:${methodProxy.proxyMethod} " +
          "enhancer:${enhancer?.javaClass?.name} " +
          "args:${Arrays.toString(args)}"
    )
    return null
  }
}