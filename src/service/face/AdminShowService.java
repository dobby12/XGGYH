package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.XAsk;
import util.Paging;

public interface AdminShowService {

	/**
	 * 
	 * @param req
	 * @return
	 */
	public Paging getPaging(HttpServletRequest req);

	/**
	 * 
	 * @param paging
	 * @return
	 */
	public List<XAsk> getShowList(Paging paging);


}
