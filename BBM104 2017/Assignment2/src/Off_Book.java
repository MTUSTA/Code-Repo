/**
 * @author Mehmet Taha USTA
 * @version 1.0 12 April 2017 
 * @param alinan_item_id the Book's id
 * @param alinan_parasal_deger the Book's price
 * @param alinan_release_date the Book's release_date
 * @param alinan_cover_title the Book's cover_title
 * @param alinan_publisher the Book's publisher
 * @param alinan_author the Book's authors
 * @param alinan_number_of_pages the Book's pages
 * @see Office_Supplies#Office_Supplies(int, double, int, String)
 **/
public class Off_Book extends Office_Supplies{
	public String publisher,author;
	public int number_of_pages;
	public Off_Book(int alinan_item_id, double alinan_parasal_deger, int alinan_release_date,String alinan_cover_title,String alinan_publisher,String alinan_author,int alinan_number_of_pages) {
		super(alinan_item_id, alinan_parasal_deger, alinan_release_date, alinan_cover_title);
		publisher = alinan_publisher;
		author = alinan_author;
		number_of_pages = alinan_number_of_pages;
	}

}
