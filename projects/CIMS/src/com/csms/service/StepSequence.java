package com.csms.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.csms.sqlmap.SqlMap;

@Component("orderDishStepSequence")
public class StepSequence {

	private ReentrantLock lock = new ReentrantLock();

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	@Resource
	private SqlMap sqlMap;

	private String id;
	private String step = "1";
	private String dataFormat;

	@SuppressWarnings("unchecked")
	public String create() {
		lock.lock();
		try {
			Map<String, Object> stepSequenceMap = (Map<String, Object>) sqlMap
					.selectOne("StepSequence.queryStepSequenceById", id);
			if (CollectionUtils.isEmpty(stepSequenceMap)) {
				stepSequenceMap = new HashMap<String, Object>() {
					private static final long serialVersionUID = 1L;

					{
						put("id", id);
						put("step", step);
						put("curval", "0");
					}
				};
				sqlMap.insert("StepSequence.addStepSequence", stepSequenceMap);
			}
			String step = (String) stepSequenceMap.get("step");
			String curval = (String) stepSequenceMap.get("curval");
			curval = String.valueOf(Integer.valueOf(step)
					+ Integer.valueOf(curval));
			String returnValue = sdf.format(new Date()) + curval;
			stepSequenceMap.put("curval", curval);
			sqlMap.update("StepSequence.updateStepSequence", stepSequenceMap);
			return returnValue;
		} finally {
			lock.unlock();
		}

	}

	public String getDataFormat() {
		return dataFormat;
	}

	public void setDataFormat(String dataFormat) {
		this.dataFormat = dataFormat;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
