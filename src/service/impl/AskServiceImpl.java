package service.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.AskDao;
import dao.impl.AskDaoImpl;
import dto.XAsk;
import service.face.AskService;
import util.Paging;

public class AskServiceImpl implements AskService {
	
	//AskDao 객체 생성
	private AskDao askDao = new AskDaoImpl();
	
	@Override
	public List<XAsk> getList() {
		
		return askDao.selectAll(JDBCTemplate.getConnection());
		
	}
	
	@Override
	public List<XAsk> getList(Paging paging) {

		return askDao.selectAll(JDBCTemplate.getConnection(), paging);
		
	}
	
	@Override
	public Paging getPaging(HttpServletRequest req) {

		//전달파라미터 curPage 파싱
		String param = req.getParameter("curPage");
		int curPage = 0;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNING] curPage값이 null이거나 비어있습니다");
		}
		
		int totalCount = askDao.selectCntAll(JDBCTemplate.getConnection());
		
		Paging paging = new Paging(totalCount, curPage);
		
		return paging;
	}

	@Override
	public XAsk getAskNo(HttpServletRequest req) {
		
		XAsk askno = new XAsk();
		
		String param = req.getParameter("askno");
		if(param!=null && !"".equals(param)) {

			askno.setAskNo( Integer.parseInt(param) );
		}
		
		return askno;
	}

	@Override
	public void write(HttpServletRequest req) {
		
		try {
			req.setCharacterEncoding("UTF-8");
		} catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		XAsk ask = new XAsk();

		ask.setAskTitle( req.getParameter("title") );
		ask.setAskKind(req.getParameter("kind"));
		ask.setAskContent( req.getParameter("content") );
		ask.setAskState("n");
		
		//작성자id 처리
		ask.setMemId((String) req.getSession().getAttribute("memid"));

		if(ask.getAskTitle()==null || "".equals(ask.getAskTitle())) {
			ask.setAskTitle("(제목없음)");
		}

		Connection conn = JDBCTemplate.getConnection();

		//게시글 번호 생성 - DAO 이용
		int askno = askDao.selectNextAskNo(conn);
		
		//게시글 정보가 있을 경우
		if(ask != null) {
			
			//작성자 memid 입력
			ask.setMemId( (String)req.getSession().getAttribute("memid") );

			ask.setAskNo(askno); //게시글 번호 입력 (PK)
			
			if(ask.getAskTitle()==null || "".equals(ask.getAskTitle())) {
				ask.setAskTitle("(제목없음)");
			}
			
			if( askDao.insert(conn, ask) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
	}

	@Override
	public Paging getPagingByMemId(HttpServletRequest req, String memid) {
		
		String param = req.getParameter("curPage");
		int curPage = 0;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		} else {
			System.out.println("[WARNING] curPage값이 null이거나 비어있습니다");
		}
		
		int totalCount = askDao.selectCntByMemId(JDBCTemplate.getConnection(), memid);
		
		Paging paging = new Paging(totalCount, curPage);
		
		return paging;
		
	}

	@Override
	public List<XAsk> getAskListByMemid(Paging paging, String memid) {
		
		return askDao.selectAllByMemid(JDBCTemplate.getConnection(), paging, memid);
	}
	

}
