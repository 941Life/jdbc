package daoTest;

import java.util.List;

import dao.AuthorDao;
import jdbc.AuthorVo;

public class AuthorDaoTest {
	public static void main(String[] args) {
		//insertTest();
		updateTest();
		selectTest();
		deleteTest(1L);
		selectTest(1L);
	}

	public static void updateTest() {
		AuthorVo authorVo = new AuthorVo();
		authorVo.setNo(2L);
		authorVo.setName("robert2");
		authorVo.setDesc("blah~blah~");
	}

	public static void insertTest() {
		AuthorVo authorVo = new AuthorVo();
		authorVo.setName("robert");
		authorVo.setDesc("blah~blah~");

		new AuthorDao().insert(authorVo);
	}

	public static void selectTest() {
		List<AuthorVo> list = new AuthorDao().getList();
		for (AuthorVo vo : list) {
			System.out.println(vo + " select1");

		}
	}

	public static void selectTest(Long no) {
		AuthorVo vo = new AuthorDao().get(no);
		System.out.println(vo + " select2");

	}

	public static void deleteTest(Long no) {
		new AuthorDao().delete(no);
	}

}
