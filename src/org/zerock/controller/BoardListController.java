package org.zerock.controller;

//d
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.persistence.BoardDAO;
import org.zerock.persistence.BoardVO;
import org.zerock.persistence.Pager;

/**
 * Servlet implement1ation class BoardListController
 */
@WebServlet("/list")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardDAO dao;

	@Override
	public void init(ServletConfig config) throws ServletException {

		dao = (BoardDAO) config.getServletContext().getAttribute("BoardDAO");
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardListController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int page = 1;
		int total = -1;
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
		}

		List<BoardVO> list = null;
		try {
			list = dao.showList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("pageNum", new Pager(page, total));

		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/board/list.jsp?page=" + page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
