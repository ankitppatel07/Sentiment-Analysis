package twitteroperation;
import twitter4j.*;
import java.io.*;
import java.io.IOException;
import twitter4j.conf.ConfigurationBuilder;
import java.util.*;

public class PrintSampleStream {
   static int c = 0;
   static String s1;
   static String keyword;
   
    public ArrayList<String> collectTweets(String userKeyword) throws TwitterException, IOException, Exception {
    	  ArrayList<String> arr= new ArrayList<String>();
    	  File f= new File("C:/B.E Project/SVM/Test Dataset/output.txt");
    	  FileWriter fw= new FileWriter("C:/B.E Project/SVM/Test Dataset/output.txt");
	      BufferedWriter bw= new BufferedWriter(fw);
	      keyword=userKeyword;
    	
    	 ConfigurationBuilder cb = new ConfigurationBuilder();
         
         cb.setDebugEnabled(true)
               .setOAuthConsumerKey("CC1Gh1T0zDGhq0Y6IVnVQRdb5")
               .setOAuthConsumerSecret("5QuJDxqScuGpWYFpGQ34Ep2fg2nONBzYpU2L0hcbQ6Z69HlJRV")
               .setOAuthAccessToken("2899322738-f7kxOGu03Wa4FMYKXWEsS83fvlAtva9km6D5CNP")
               .setOAuthAccessTokenSecret("m1ljUBiUL4yUtT1EyHTHranwyOzfWOy0zPQwB1XxGAN2H");
     
         TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
        StatusListener listener = new StatusListener() {
         
        	@Override
            public void onStatus(Status status) {
        		try{
        		String filterTweet=null;
        				String language= status.getLang();
        				String filterLang="en";
        			
        				
        				if (filterLang.matches(language)){
        				filterTweet=status.getText();
        				
        		 s1 = filterTweet;
        		
        			bw.write(s1);
        			arr.add(s1);
        			bw.newLine();
        			bw.flush();
                	
				}
        			
        				}catch (IOException e) {
        					System.out.println("Error in onstatus");
        					e.printStackTrace();
        				}
				c = c + 1;
        		if(c%500==0){try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}twitterStream.shutdown();}
         	}

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
               // System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
               // System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
               // System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
               // System.out.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
            	System.out.println("here1");
                ex.printStackTrace();
            }
             
        };
        
        FilterQuery fq = new FilterQuery();        

      //  InputStreamReader isr = new InputStreamReader(System.in);
   	   // BufferedReader br = new BufferedReader(isr);
        
       // System.out.println("Enter your Query:");
        //keyword = br.readLine();
        

        fq.track(keyword);    
        
        twitterStream.addListener(listener);
        twitterStream.filter(fq);    
     //  twitterStream.sample();
  /*      
        boolean b1 = false;
        boolean b2 = false;
        File f1 = new File("C:/B.E Project/SVM/Code");
        f1.mkdir();
        File f2 = new File(f1,"dataset.arff");
        b1 = f2.exists();
        if(b1)
        {
        	System.out.println("File Exists!!!");
        }
        else
        {
        	b2 = f2.createNewFile();
        }
        if(b2)
        {
        	System.out.println("File Created!!!");	
        }
        
   
        
        FileOutputStream fos = new FileOutputStream(f2);
        PrintStream ps = new PrintStream(fos);
        System.setOut(ps);
        
        System.out.println("@relation twitter-sentiment-analysis \n @attribute sentimentclass {negativeC,positiveC} \n @attribute tweet String \n");
        System.out.println("@data");

     */
        //twitterStream.shutdown();
        
       return arr;
    }
}