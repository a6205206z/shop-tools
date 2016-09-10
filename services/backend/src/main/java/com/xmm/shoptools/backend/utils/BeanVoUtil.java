package com.xmm.shoptools.backend.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.xmm.shoptools.backend.entity.BaseModel;
import com.xmm.shoptools.backend.vo.BaseVo;

public class BeanVoUtil {
	private static final Logger log = LoggerFactory.getLogger(BeanVoUtil.class);

	/**
	 * 将list对象复制成vo
	 * 
	 * @param source
	 * @param clazz
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@SuppressWarnings("unchecked")
	public static <S extends BaseModel, T extends BaseVo> List<T> copyList(
			List<S> source, @SuppressWarnings("rawtypes") Class clazz) {
		List<T> result = new ArrayList<T>();
		if (null != source && !source.isEmpty()) {
			for (S t : source) {
				try {
					// 目标对象实例
					Object target = clazz.newInstance();
					// 使用spring.beanutils进行复制
					BeanUtils.copyProperties(t, target);
					result.add((T) target);
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
		}
		return result;
	}

	// 对象考贝
	public static void copyObject(Object source, Object target) {
		BeanUtils.copyProperties(source, target);
	}

	/**
	 * 对象间复制(参照)
	 * 
	 * @param from
	 * @param to
	 */
	@SuppressWarnings("unused")
	private static void jdkCopyPriperties(Object from, Object to) {
		String fileName, str, getName, setName;
		List<Field> fields = new ArrayList<Field>();
		Method getMethod = null;
		Method setMethod = null;
		try {
			Class<? extends Object> c1 = from.getClass();
			Class<? extends Object> c2 = to.getClass();

			Field[] fs1 = c1.getDeclaredFields();
			Field[] fs2 = c2.getDeclaredFields();
			// 两个类属性比较剔除不相同的属性，只留下相同的属性
			for (int i = 0; i < fs2.length; i++) {
				for (int j = 0; j < fs1.length; j++) {
					if (fs1[j].getName().equals(fs2[i].getName())) {
						fields.add(fs1[j]);
						break;
					}
				}
			}
			if (null != fields && fields.size() > 0) {
				for (int i = 0; i < fields.size(); i++) {
					// 获取属性名称
					Field f = (Field) fields.get(i);
					fileName = f.getName();
					// 属性名第一个字母大写
					str = fileName.substring(0, 1).toUpperCase();
					// 拼凑getXXX和setXXX方法名
					getName = "get" + str + fileName.substring(1);
					setName = "set" + str + fileName.substring(1);
					// 获取get、set方法
					getMethod = c1.getMethod(getName, new Class[]{});
					setMethod = c2.getMethod(setName, new Class[]{f.getType()});

					// 获取属性值
					Object o = getMethod.invoke(from, new Object[]{});
					// 将属性值放入另一个对象中对应的属性
					if (null != o) {
						setMethod.invoke(to, new Object[]{o});
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
