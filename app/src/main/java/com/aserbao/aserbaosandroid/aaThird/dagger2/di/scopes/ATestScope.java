package com.aserbao.aserbaosandroid.aaThird.dagger2.di.scopes;

/**
 * 功能:
 *
 * @author aserbao
 * @date : On 2019/6/13 11:36 AM
 * @project:AserbaosAndroid
 * @package:com.aserbao.aserbaosandroid.aaThird.dagger2.di.scopes
 */

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Scope
@Retention(RUNTIME)
public @interface ATestScope {}

