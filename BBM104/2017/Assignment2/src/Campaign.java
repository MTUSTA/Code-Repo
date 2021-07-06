/**
 * 
 * @author Mehmet Taha USTA
 * @param alinan_startdate	the campaign's start date
 * @param alinan_enddate	the campaign's end date
 * @param alinan_itemtype	the campaign's itemtype
 * @param alinan_rate	Discount applied to the campaign
 */
public class Campaign {
	public String startdate,enddate,itemtype;
	public int rate;
	public Campaign(String alinan_startdate,String alinan_enddate,String alinan_itemtype,int alinan_rate){
		startdate =alinan_startdate;
		enddate = alinan_enddate;
		itemtype = alinan_itemtype;
		rate = alinan_rate;
	}

}
