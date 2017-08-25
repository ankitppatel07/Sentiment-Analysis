package twitteroperation;

import java.io.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.LibSVM;
import weka.classifiers.functions.SMO;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.converters.ArffLoader.ArffReader;


public class Classification {
	public static Instances trainData,trainDataFiltered;
	public static Instances testData,testDataFiltered;
	public static StringToWordVector x3,x4,filter;
	public static FilteredClassifier fclassifier;

	
	public Evaluation summary() throws Exception
	{	

			BufferedReader br1 = new BufferedReader(new FileReader("C:/B.E Project/SVM/TrainingSet/TrainingSet.arff"));
			ArffReader x1 = new ArffReader(br1);
			trainData = x1.getData();
			x3 = new StringToWordVector();
//			x3.setAttributeNamePrefix(null);

			
			x3.setAttributeIndices("first-last");			
		
			x3.setStopwords(new File("C:/B.E Project/SVM/StopWords/stop_words2.txt"));
			
		//	BufferedReader br2 = new BufferedReader(new FileReader("C:/B.E Project/SVM/dataset.arff"));
			BufferedReader br2 = new BufferedReader(new FileReader("C:/B.E Project/SVM/Test Dataset/dataset.arff"));
			ArffReader x2 = new ArffReader(br2);
			testData = x2.getData();
			
			
			x3.setInputFormat(testData);  //setInputFormat should be the last call before the filter is applied
			trainDataFiltered = Filter.useFilter(trainData, x3);
			testDataFiltered = Filter.useFilter(testData, x3);
	
			FileWriter fw1 = new FileWriter("C:/B.E Project/SVM/TrainingSet/Trainingsetnumeric1.arff");
			BufferedWriter out1 = new BufferedWriter(fw1);
			out1.write(trainDataFiltered.toString());
			System.out.println(trainDataFiltered);
			out1.flush();
		    out1.close();
		    System.out.println("===== Loaded training dataset: =====");

	
		  
		    FileWriter fw2 = new FileWriter("C:/B.E Project/SVM/Test Dataset/datasetnumeric1.arff");
			BufferedWriter out2 = new BufferedWriter(fw2);
			out2.write(testDataFiltered.toString());
		    System.out.println(testDataFiltered);
		    out2.flush();
		    out2.close();
		    System.out.println("===== Loaded test dataset:=====");

			trainDataFiltered.setClassIndex(0);
		    testDataFiltered.setClassIndex(0);
		   
		    SMO classifier = new SMO();
		    //LibSVM classifier = new LibSVM();
		 classifier.buildClassifier(trainDataFiltered); 
		 			
		System.out.println("Build classifier done. Evaluating.......");
		
		Evaluation evalfinal = new Evaluation(trainDataFiltered);
		evalfinal.evaluateModel(classifier, testDataFiltered);
	
		System.out.println(evalfinal.toClassDetailsString());
		System.out.println(evalfinal.toSummaryString());
		String finalString= evalfinal.toClassDetailsString();
				//+"\n"+evalfinal.toSummaryString();
		System.out.println("===== Evaluating on filtered (test) dataset done =====");
		Double corr = evalfinal.correct();
		Double incorr = evalfinal.incorrect();
		Double tpr =  evalfinal.truePositiveRate(0);
		Double tnr =  evalfinal.trueNegativeRate(0);
		Double fpr = evalfinal.falsePositiveRate(0);
		Double fnr = evalfinal.falseNegativeRate(0);
		
		Double Accuracy = ((tpr*corr)+(tnr*incorr))/((tpr*corr)+(tnr*incorr)+(fpr*incorr)+(fnr*corr));
		
		System.out.println("Correct = " + corr + "incorrect = " + incorr + "Accuracy =" + Accuracy);
		
	
		DefaultPieDataset datasetposneg = new DefaultPieDataset( );
	      datasetposneg.setValue("Positive Tweets:", corr);
	      datasetposneg.setValue("Negative Tweets:", incorr);

	      JFreeChart chartposneg = ChartFactory.createPieChart(
	         "Positive and Negative Tweets",   // chart title
	         datasetposneg,          // data
	         true,             // include legend
	         true,
	         false);
	         
	      int width = 400;   /* Width of the image */
	      int height = 300;  /* Height of the image */ 
	      File pieChart = new File( "C:/Users/ankitpatel/workspaceEE/Twitter_Sentiment_Analysis/WebContent/PieChart.jpeg" ); 
	      ChartUtilities.saveChartAsJPEG( pieChart , chartposneg , width , height );
	      
	  
		System.out.println(finalString);
		return evalfinal;
		
	
	}
}
