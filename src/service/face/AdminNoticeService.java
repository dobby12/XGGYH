package service.face;

import java.util.List;

import dto.XFile;
import dto.XNotice;

public interface AdminNoticeService {

	/**
	 * 모든 XNotice객체를 담은 List 요청
	 * @return XNotice객체가 담긴 List
	 */
	public List<XNotice> getNoticeList();

	/**
	 * 절달받은 숫자를 noticeno로 가진 XNotice객체 반환
	 * @param noticeno - 조회할 noticeno
	 * @return DB에서 조회한 XNotice객체
	 */
	public XNotice getNoticeDetail(int noticeno);

	/**
	 * 전달받은 숫자를 noticeno로 가진 XFile객체 반환
	 * @param noticeno - 조회할 noticeno
	 * @return DB에서 조회한 XFile객체
	 */
	public XFile getFile(int noticeno);

}
