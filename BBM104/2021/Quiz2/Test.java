import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		
		  SportCenter sc = new SportCenter("Fitness Center");
		  PersonalTrainer pt1=new PersonalTrainer(1,"Ali","Guven","Aerobic");
		  PersonalTrainer pt2=new PersonalTrainer(2,"Aysel","Demir","Spinning");
		  PersonalTrainer pt3=new PersonalTrainer(3,"Can","Yaman","Yoga");
		  PersonalTrainer pt4=new PersonalTrainer(4,"Halis","Genc","Kickbox");
		  sc.addPT(pt1);
		  sc.addPT(pt2);
		  sc.addPT(pt3);
		  sc.addPT(pt4);
		  Member mbr1 = new Member(1,"Halil","Yilmaz",85,1.83);		
		  Member mbr2 = new Member(2,"Veli","Demir",65,1.71);		
		  Member mbr3 = new Member(3,"Ayse","Haktan",95,1.53);		
		  Member mbr4 = new Member(4,"Azra","Talat",55,1.73);		
		  Member mbr5 = new Member(5,"Sinan","Can",115,1.85);
		  Member mbr6=new Member(6,"Banu","Bir",134,1.82);
		  Member mbr7=new Member(7,"Beyza","Kas",54,1.63);
		  Member mbr8=new Member(8,"Davut","Yalcin",66,1.53);
		  Member mbr9=new Member(9,"Melike","Bolu",100,1.73);
		  Member mbr10=new Member(10,"Mahmut","Tuncer",120,1.93);
		  Member mbr11=new Member(11,"Cem","Cemil",74,1.83);
		  Member mbr12=new Member(12,"Aysun","Kizil",60,1.69);
		  pt1.addMember(mbr1);	
		  pt1.addMember(mbr2);	
		  pt1.addMember(mbr3);	
		  pt1.addMember(mbr4);	
		  pt1.addMember(mbr5);
		  pt2.addMember(mbr6);
		  pt2.addMember(mbr7);
		  pt2.addMember(mbr8);
		  pt3.addMember(mbr9);
		  pt4.addMember(mbr10);
		  pt4.addMember(mbr11);
		  pt4.addMember(mbr12);	
	
		
				
		    //Test of addPT() method 
				
				
			if(sc.PTs[0]==pt1 && sc.PTs[1]==pt2 && sc.PTs[2]==pt3 && sc.PTs[3]==pt4)
				System.out.println("You passed addPT() method test");
			else 
			{
				System.out.println("You failed addPT() method test");
			}
		
		    
		    //Test of searchPT() method
		    PersonalTrainer TestPT1=sc.searchPT("Ali", "Guven");
		    PersonalTrainer TestPT2=sc.searchPT("Hakan", "Tasiyan");
		     
		    if (TestPT1==pt1 && TestPT2==null) 
		    	System.out.println("You passed searchPT() method test");
		    else 
		    {
				System.out.println("You failed searchPT() method test");
			}
		    
		    
		    
		    //Test of weightStatus() method 		
		    if (mbr1.weightStatus()=="Fat" && mbr3.weightStatus()=="Obese")
		    	
		    	System.out.println("You passed weightStatus() method test");
		    else 
		    {
				System.out.println("You failed weightStatus() method test");
			}
		    
		    
			//Test of ReturnFattestMember() method
		    
		    if(pt3.ReturnFattestMember()==mbr9 && pt1.ReturnFattestMember()==mbr5 && pt4.ReturnFattestMember()==mbr10)
		    	System.out.println("You passed ReturnFattestMember() method test");
		    else 
		    {
				System.out.println("You failed ReturnFattestMember() method test");
			}
		    
		    
		   //Test of returnMember() method 
		    
		    if(pt4.returnMember(10)==mbr10 && pt4.returnMember(11)==mbr11 && pt4.returnMember(12)==mbr12 )
		    	System.out.println("You passed returnMember() method test");
		    else 
		    {
		    	System.out.println("You failed returnMember() method test");
			}
		    
		    
		    //Test of returnCountofMembers() method
		    if(pt1.returnCountofMembers()==5 && pt2.returnCountofMembers()==3 && pt3.returnCountofMembers()==1 && pt4.returnCountofMembers()==3)
		    	System.out.println("You passed returnCountofMembers() method test");
		    else 
		    {
		    	System.out.println("You failed returnCountofMembers() method test");
			}
		 

	}
}
