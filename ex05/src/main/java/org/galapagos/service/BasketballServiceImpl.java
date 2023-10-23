package org.galapagos.service;

import java.io.FileInputStream;

import org.galapagos.domain.BasketballVO;
import org.springframework.stereotype.Service;

import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;

@Service
public class BasketballServiceImpl implements BasketballService {

	Classifier model;
	Instances dataSet;
	
	public BasketballServiceImpl() {
		
		try {
			String[] classLabels = { "SG", "C" };
			dataSet = WekaUtil.makeInstances(7, classLabels);
			model = (Classifier) SerializationHelper.read(
					new FileInputStream("c:/temp/baseket-j48.model"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	@Override
	public String predict(BasketballVO value) {
		
		Instance data = value.toInstance();
		data.setDataset(dataSet);
		
		double result = 0;
		try {
			result = model.classifyInstance(data);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return dataSet.classAttribute().value((int)result);
	
	}

}
