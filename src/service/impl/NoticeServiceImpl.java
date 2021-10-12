package service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.NoticeDao;
import dao.impl.NoticeDaoImpl;
import dto.XNotice;
import service.face.NoticeService;
import util.Paging;

public class NoticeServiceImpl implements NoticeService {

	//NoticeDao 객체 생성
	private NoticeDao noticeDao = new NoticeDaoImpl();
	
	@Override
	public List<XNotice> getNoticeList() {
		
		return noticeDao.selectAll(JDBCTemplate.getConnection());
		
	}
	
	@Override
	public List<XNotice> getNoticeList(Paging paging) {

		//페이징 추가
		return noticeDao.selectAll(JDBCTemplate.getConnection(), paging);
		
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
		
		//Notice 테이블의 총 게시글 수를 조회한다
		int totalCount = noticeDao.selectCntAll(JDBCTemplate.getConnection());
		
		//Paging객체 생성
		Paging paging = new Paging(totalCount, curPage);
		
		return paging;
	}

	@Override
	public XNotice getNoticeNo(HttpServletRequest req) {
		
		XNotice noticeno = new XNotice();
		
		String param = req.getParameter("noticeno");
		if(param!=null && !"".equals(param)) {
			
			noticeno.setNoticeNo( Integer.parseInt(param));
		}
		
		return noticeno;
	}

	@Override
	public String getAdminName(XNotice viewNotice) {
		return noticeDao.selectNameByAdminId(JDBCTemplate.getConnection(), viewNotice);
	}
}