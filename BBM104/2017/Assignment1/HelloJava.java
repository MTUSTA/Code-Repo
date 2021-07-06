import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HelloJava {
	public static void main(String[] args){		
		try{
			File girdi = new File(args[0]);//read command line
			Scanner girdi_tarama = new Scanner(girdi);
			while(girdi_tarama.hasNextLine()){//line reading
				String line = girdi_tarama.nextLine();
				String[] splitlenmis_line = line.split(" ");//line split
				if(splitlenmis_line[1].toLowerCase().equals("func1")){//do String word comparison and choose the right path
					Double splitlenmis_line_alt_sinir = Double.parseDouble(splitlenmis_line[2]);//String converts double
					Double splitlenmis_line_ust_sinir = Double.parseDouble(splitlenmis_line[3]);//String converts double
					Double splitlenmis_line_kare_sayisi = Double.parseDouble(splitlenmis_line[4]);//String converts double
					System.out.println("integrateReimann Func1 "+splitlenmis_line[2]+" "+ splitlenmis_line[3]+" "+splitlenmis_line[4]+" Result: "+ integrateRiemann1(splitlenmis_line_alt_sinir,splitlenmis_line_ust_sinir,splitlenmis_line_kare_sayisi));
				}
				if(splitlenmis_line[1].toLowerCase().equals("func2")){//do String word comparison and choose the right path
					Double splitlenmis_line_alt_sinir = Double.parseDouble(splitlenmis_line[2]);//String converts double
					Double splitlenmis_line_ust_sinir = Double.parseDouble(splitlenmis_line[3]);//String converts double
					Double splitlenmis_line_kare_sayisi = Double.parseDouble(splitlenmis_line[4]);//String converts double
					System.out.println("integrateReimann Func2 "+ splitlenmis_line[2]+" "+ splitlenmis_line[3]+" "+splitlenmis_line[4]+" Result: "+ integrateRiemann2(splitlenmis_line_alt_sinir,splitlenmis_line_ust_sinir,splitlenmis_line_kare_sayisi));
				}
				if(splitlenmis_line[1].toLowerCase().equals("func3")){//do String word comparison and choose the right path
					Double splitlenmis_line_alt_sinir = Double.parseDouble(splitlenmis_line[2]);//String converts double
					Double splitlenmis_line_ust_sinir = Double.parseDouble(splitlenmis_line[3]);//String converts double
					Double splitlenmis_line_kare_sayisi = Double.parseDouble(splitlenmis_line[4]);//String converts double
					System.out.println("integrateReimann Func3 "+splitlenmis_line[2]+" "+ splitlenmis_line[3]+" "+splitlenmis_line[4]+" Result: "+ integrateRiemann3(splitlenmis_line_alt_sinir,splitlenmis_line_ust_sinir,splitlenmis_line_kare_sayisi));
				}
				if(splitlenmis_line[0].equals("Arcsinh")){//do String word comparison and choose the right path
					Double splitlenmis_line_arcsinh = Double.parseDouble(splitlenmis_line[1]);//String converts double
					System.out.print("Archsinh "+splitlenmis_line_arcsinh);
					if(splitlenmis_line_arcsinh< 0){
						splitlenmis_line_arcsinh = splitlenmis_line_arcsinh * -1;
					}
					if(splitlenmis_line_arcsinh<1){	//String word comparison and separate the right path
						System.out.println(" Result: "+func3(splitlenmis_line_arcsinh));						
					}
				}	
				if(splitlenmis_line[0].toLowerCase().equals("armstrong")){//do String word comparison and choose the right path
					Double splitlenmis_line_armstrong = Double.parseDouble(splitlenmis_line[1]);//String converts double
					int donecek_sayi1 = 0;
					double kuvvet_olacak= Math.pow(10, splitlenmis_line_armstrong);//Define step boundary
					System.out.print("Armstrong "+splitlenmis_line[1]+" Result: ");
					while(donecek_sayi1 < kuvvet_olacak){
						String string_donecek_sayi1 = Integer.toString(donecek_sayi1);//we need use for for_loop
						int armstrong_dongusunde_kullanilacak_sayi = 0;//use for loop
						double basamak_toplami = 0;//Sum of digits
						double uslu_olarak_uygulayacagim_kuvvet = string_donecek_sayi1.length();//we need for power and for_loop
						for ( double uzunluk = string_donecek_sayi1.length(); armstrong_dongusunde_kullanilacak_sayi < uzunluk; armstrong_dongusunde_kullanilacak_sayi++ ){
							char tek_basamak = string_donecek_sayi1.charAt(armstrong_dongusunde_kullanilacak_sayi);//Determine single digit
							double ilk_basamak_int_donusumu = Character.getNumericValue(tek_basamak);//Determine single digit
							double kuvvet_olacak2= Math.pow(ilk_basamak_int_donusumu, uslu_olarak_uygulayacagim_kuvvet);//find digit power
							basamak_toplami += kuvvet_olacak2;
						}
						if (donecek_sayi1 == basamak_toplami){//If the number is equal to the sum of the digits,progress goes print function
							System.out.print(donecek_sayi1+" ");
						}	
						donecek_sayi1 += 1;
					}
				System.out.println("");
				}
			}
			System.out.println("");
			girdi_tarama.close();
		} 
		catch (FileNotFoundException ex) {
		System.out.println("No File Found!");
		return;
		}
}
	public static double func1(double x){//function
		return x*x-x+3;
	}	
	public static double func2(double x){
		return Math.pow(3*Math.sin(x)-4, 2);//Square of the (3(sin(x)-4)
	}
	public static double func3(double x){//definition of arcsinh(x)
		double en_sonuc_result = 0;
		for(int n=0;n < 30; n++){
			double eksi_bir_uzeri_n = Math.pow(-1, n);//Definition -1 power n
			double dort_uzeri_n = Math.pow(4, n);//Definition 4 power n
			double iki_n_arti_1 = 2*n+1;//Definition 2 times n plus 1
			double x_uzeri_iki_n_arti_1 = Math.pow(x, iki_n_arti_1);//Definition input times (2 times n plus 1)
			int faktoriyel_2n = 1;//Definition 2 timen n factorial
			int iki_n = n*2;//Definition 2 times n
			for (int i = 1; i <= iki_n; i++) {//2 times n factorial  
				faktoriyel_2n = faktoriyel_2n * i;
			}
			int faktoriyel_n = 1;//Definition n factorial
			for (int i = 1; i <= n; i++) {// N factorial
				faktoriyel_n = faktoriyel_n * i;
			}
			double n_faktoriyel_ikinci_kuvveti = Math.pow(faktoriyel_n, 2);//Square of N factorial
			double result = ((eksi_bir_uzeri_n*faktoriyel_2n)/(dort_uzeri_n*iki_n_arti_1*n_faktoriyel_ikinci_kuvveti))*x_uzeri_iki_n_arti_1;
			en_sonuc_result += result;
		}
		return en_sonuc_result;
	}
	public static double integrateRiemann1(double a,double b,double n){
		double toplam_alan =0, bar_genisligi =0, forda_donecek_sayi=0;
		if(a>b){
			return integrateRiemann1(b,a,n);//Edit numbers
		}
		else{
			bar_genisligi =(b-a)/n;//Range determination
			for(forda_donecek_sayi=a+bar_genisligi/2;forda_donecek_sayi<=b;forda_donecek_sayi=forda_donecek_sayi+bar_genisligi){
				toplam_alan += bar_genisligi*func1(forda_donecek_sayi);//total area
			}
		}
		return toplam_alan;
	}
	public static double integrateRiemann2(double a,double b,double n){
		double toplam_alan =0, bar_genisligi =0, forda_donecek_sayi=0;
		if(a>b){
			return integrateRiemann2(b,a,n);//Edit numbers
		}
		else{
			bar_genisligi =(b-a)/n;//Range determination
			for(forda_donecek_sayi=a+bar_genisligi/2;forda_donecek_sayi<=b;forda_donecek_sayi=forda_donecek_sayi+bar_genisligi){
				toplam_alan += bar_genisligi*func2(forda_donecek_sayi);//total area
			}
		}
		return toplam_alan;
	}
	public static double integrateRiemann3(double a,double b,double n){
		double toplam_alan =0, bar_genisligi =0, forda_donecek_sayi=0;
		if(a>b){
			return integrateRiemann3(b,a,n);//Edit numbers
		}
		else{
			bar_genisligi =(b-a)/n;//Range determination
			for(forda_donecek_sayi=a+bar_genisligi/2;forda_donecek_sayi<=b;forda_donecek_sayi=forda_donecek_sayi+bar_genisligi){
				toplam_alan += bar_genisligi*func3(forda_donecek_sayi);//total area
			}
		}
		return toplam_alan;
	}
}