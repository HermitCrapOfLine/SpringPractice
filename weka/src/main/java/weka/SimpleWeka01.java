package weka;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

public class SimpleWeka01 {
	public static void main(String[] args) throws Exception {
		
		int numfold = 0;
		int numfolds = 10;
		int seed = 1;
		
		Instances data = new Instances (
					new BufferedReader(
							new FileReader("c:/temp/titanic_pre.arff"))
				);		
		Instances train = data.trainCV(numfolds,  numfold, new Random(seed));
		Instances test = data.testCV(numfolds,  numfold);
		
		train.setClassIndex(train.numAttributes()-1);
		test.setClassIndex(train.numAttributes()-1);
		
		RandomForest model = new RandomForest();
//		DecisionTable model = new DecisionTable();
//		J48 model = new J48();  //SVM

		Evaluation eval = new Evaluation(train);
		eval.crossValidateModel(model, train, numfolds, new Random(seed));
		
		model.buildClassifier(train);
		
		eval.evaluateModel(model, test);
		
		System.out.println(model);
		System.out.println(eval.toSummaryString());
		System.out.println(eval.toMatrixString());
	}
}
