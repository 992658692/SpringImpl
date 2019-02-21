package com.lonton.context;

import com.lonton.core.io.AutowireCapableBeanFactory;
import com.lonton.core.io.DefaultResourceLoader;

/**
 * Created by xinpc on 2019-02-21
 *
 * @desc
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ApplicationContext, AutowireCapableBeanFactory{
}
