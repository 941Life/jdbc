package daoTest;

import dao.AuthorDao;
import dao.BookDao;
import jdbc.AuthorVo;
import jdbc.BookVO;

public class BookDaoTest {
	public static void main(String[] args) {
		insertTest();
		updateTest();
	}
	
	public static void insertTest(){
		BookVO bookVO = new BookVO();
		bookVO.setTitle("hi");
		bookVO.setPrice(12000);
		
		new BookDao().insert(bookVO);
		System.out.println("성공");
	}
	
	public static void updateTest() {
		BookVO bookVO = new BookVO();
		bookVO.setIsbn(2L);
		bookVO.setTitle("하이");
		bookVO.setPrice(22000);
	}
	
	public static void selectTest(Long isbn) {
		BookVO vo = new BookDao().get(isbn);
		System.out.println(vo + " select");

	}

	public static void deleteTest(Long isbn) {
		new BookDao().delete(isbn);
	}
}
