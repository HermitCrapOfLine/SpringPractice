package weka;

import java.io.FileInputStream;

import weka.classifiers.trees.RandomForest;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;

public class SimpleWeka04 {

	public static void main(String[] args) throws Exception {

		String[] classLabels = { "SG", "C" };
		Instances dataSet = WekaUtil.makeInstances(6, classLabels);

		// 모델 불러오기
		RandomForest model = (RandomForest) SerializationHelper.read(new FileInputStream("c:/temp/baseket-j48.model"));

		// 분류할 데이터 준비
		double[] values = new double[] { 2.0, 2.9, 3.3, 2.5, 1.2, 0.2 }; // SG
	//	double[] values = new double[] { 0.0, 4.7, 7.7, 1.1, 1.1, 1.0 }; // C
		Instance data1 = new DenseInstance(1, values);
		data1.setDataset(dataSet);

		// 분류하기
		double result = model.classifyInstance(data1);

		// 정답 출력
		System.out.println(result);
		System.out.println(dataSet.classAttribute().value((int) result));
	}
}
