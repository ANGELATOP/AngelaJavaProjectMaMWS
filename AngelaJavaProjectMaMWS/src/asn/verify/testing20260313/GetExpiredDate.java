package asn.verify.testing20260313;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetExpiredDate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(getExpiredDate(1800));
	}

	public static String getExpiredDate(int addSecs) {
		
		int additionalSecs = addSecs - 120; //instead of using exact secs the token is valid for, backing off by 120 secs to avoid being to close to the expired time.
		
		Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.SECOND, additionalSecs); // Add seconds to get the expired time.
        Date expiredDate = calendar.getTime();

        //System.out.println("Expired Date:  " + expiredDate);
        
        String currDateString    = convertToStringDate(currentDate);
        String expiredDateString = convertToStringDate(expiredDate);

        
        return convertToStringDate(expiredDate);
	}
	public static String convertToStringDate(Date inputDate) {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSS");
		return fmt.format(inputDate);
	}
}
