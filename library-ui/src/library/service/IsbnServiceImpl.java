package library.service;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.ejb.MessageDriven;
import javax.ejb.Stateless;

@Stateless
public class IsbnServiceImpl implements IIsbnService{

	private Pattern isbnRegex = Pattern.compile("(\\d\\d)-(\\d\\d\\d)-(\\d\\d\\d\\d)-([0-9X])");
	
	private void nextDigit(String[] s, int[] checksum, int position, Random r){
		int digit = r.nextInt(10);
		checksum[0]+=digit*position;
		s[0]+=digit;
	}
	
	private String makeISBN(){
		Random r = new Random();
		String[] s = {""};
		int[] checksum = new int[1];
		nextDigit(s, checksum, 10, r);
		nextDigit(s, checksum, 9, r);
		s[0]+="-";
		nextDigit(s, checksum, 8, r);
		nextDigit(s, checksum, 7, r);
		nextDigit(s, checksum, 6, r);
		s[0]+="-";
		nextDigit(s, checksum, 5, r);
		nextDigit(s, checksum, 4, r);
		nextDigit(s, checksum, 3, r);
		nextDigit(s, checksum, 2, r);
		s[0]+="-";
		checksum[0]=checksum[0]%11;
		s[0]+=checksum[0]==10?"X":""+checksum[0];
		return s[0];
	}

	public boolean isValidIsbn(String isbn){
		Matcher m;
		if (isbn == null)
			return false;
		m = isbnRegex.matcher(isbn);
		if (!m.matches())
			return false;
		int cs = 0;
		int position = 10;
		for (int i=0;i<isbn.length()-1;i++){
			char c = isbn.charAt(i);
			if (c=='-')
				continue;
			int digit = Integer.parseInt(""+c);
			cs+=digit*(position--);
		}
		cs=cs % 11;
		char strcs = (cs==11?"X":""+cs).charAt(0);
		return strcs == isbn.charAt(isbn.length()-1);
	}
	
	public String allocateNewIsbn(){
		return makeISBN();
	}

}
