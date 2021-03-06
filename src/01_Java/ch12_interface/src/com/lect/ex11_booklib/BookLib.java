package com.lect.ex11_booklib;
public class BookLib extends BookInfo implements ILendable {
	private String borrower;  // 대출인(입력)
	private String checkOutDate;//대출일(입력)
	private byte state;       // 대출상태(0(STATE_NORMAL):대출가능, 
	                          //        1(STATE_BORROWED):대출중)
	public BookLib(String bookNo, String bookTitle, String writer) {
		super(bookNo, bookTitle, writer);
//		borrower = null;
//		checkOutDate = null;
//		state = STATE_NORMAL;
	}
	// BookLib book1 = new BookLib("890ㄱ-105나","이것이베개다","홍길동");
	// book1.checkOut("김길순", "05-20");
	@Override
	public void checkOut(String borrower, String checkOutDate) {
		if(state!=STATE_NORMAL) {
			System.out.println("대출중인 도서입니다");
			return;
		}
		// 대출처리 로직
		this.borrower = borrower;
		this.checkOutDate = checkOutDate;
		state = STATE_BORROWED;
		// 대출완성후 메세지 : "베개" 도서가 대출되었습니다
		System.out.println("\""+getBookTitle()+"\" 도서가 대출되었습니다");
	}
	// book1.checkIn();
	@Override
	public void checkIn() {
		if(state!=STATE_BORROWED) {
			System.out.println("대출 중인 도서가 아닙니다. 이상합니다");
		}else {
			// 반납 처리 로직
			borrower = null;
			checkOutDate = null;
			state = STATE_NORMAL;
			System.out.println("\""+getBookTitle()+"\" 도서가 반납되었습니다");
		}
	}
	@Override
	public String toString() {
		String result = String.format("\"%s\"\t%s저\t", getBookTitle(), getWriter());
		//result += state==STATE_NORMAL? "대출가능":"대출중";
		if(state == STATE_NORMAL) {
			result += " 대출가능";
		}else if(state == STATE_BORROWED) {
			result += " 대출중";
		}else {
			result += " 직원문의(이상상태)";
		}
		return result;
	}
	// getter
	public String getBorrower() {
		return borrower;
	}
	public String getCheckOutDate() {
		return checkOutDate;
	}
	public byte getState() {
		return state;
	}
}










