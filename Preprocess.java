package twitteroperation;

import java.io.*;
import java.util.ArrayList;

public class Preprocess {

	public static void main(String args[]) throws IOException
	{
	Preprocess p = new Preprocess();
	p.datapreprocessing();
		
	}
	
	public ArrayList<String> datapreprocessing() throws IOException
	{
FileReader fr = new FileReader("C:/B.E Project/SVM/Test Dataset/output.txt");
FileWriter fw = new FileWriter("C:/B.E Project/SVM/Test Dataset/dataset.arff");
BufferedReader in = new BufferedReader(fr);
BufferedWriter out = new BufferedWriter(fw);

out.write("@relation twitter-sentiment-analysis \n"); 
out.write("@attribute sentimentclass {negativeC,positiveC} \n");
out.write("@attribute tweet String \n");
out.write("@data");
out.newLine();
String line;
String finalString = "";
ArrayList<String> arr= new ArrayList<String>();
while((line=in.readLine())!=null)
{
		String s101 = line.replaceAll("((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)","");	
    	
		String s1 = s101.replace("#","");
		String s3 = s1.replace("@","");
		String s4 = s3.replace("?","");
		String s5 = s4.replace("0|1|2|3|4|5|6|7|8|9","");
		String s15 = s5.replace("/","");
		String s16 = s15.replace("&","");
		String s17 = s16.replace("_","");
		String s18 = s17.replace("%","");
		String s19 = s18.replace("$","");
		String s20 = s19.replace("^","");
		String s21 = s20.replace("*","");
		String s22 = s21.replace("(","");
		String s23 = s22.replace(")","");
		String s24 = s23.replace("[","");
		String s25 = s24.replace("]","");
		String s26 = s25.replace("{","");
		String s27 = s26.replace("}","");
		String s28 = s27.replace("|","");
		String s29 = s28.replace("RT","");
		String s30 = s29.replace(":","");
		String s31 = s30.replace("-","");
		String s32 = s31.replace(",","");
		String s33 = s32.replace(".","");
		String s34 = s33.replace("!","");
		String s36 = s34.replace("'","");
		String s37 = s36.replace("\"","");
//	String s37 = s36.replace(" ","");
		
		out.write("positiveC");
		out.write(",");
		out.write("\"");
		out.write(" ");
		out.write(s37);
		out.write(" ");
		out.write("\"");
		out.write(",");
		out.newLine();
		arr.add(s37);
	}

	in.close();
	out.close();
	return arr;
	}
	
}