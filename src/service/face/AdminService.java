package service.face;

import javax.servlet.http.HttpServletRequest;

import dto.XAdmin;

public interface AdminService {

	/**
	 * req를 XAdmin객체로 반환
	 * @param req - 전달 받은 요청 파라미터
	 * @return req에 있는 정보를 담은 XAdmin객체
	 */
	public XAdmin getLoginAdmin(HttpServletRequest req);

	/**
	 * admin을 DB 정보와 조회하여 로그인을 시도하여 boolean 데이터 타입으로 반환
	 * @param admin - DB와 대조할 XMem객체
	 * @return 로그인 시도 성공 시 true, 실패 시 false
	 */
	public boolean loginAdmin(XAdmin admin);

	/**
	 * admin의 adminid와 일치하는 XAdmin객체를 조회하여 반환
	 * @param admin
	 * @return DB에 저장되어 있던 정보를 담은 XAdmin객체
	 */
	public XAdmin getAdmin(XAdmin admin);

	/**
	 * DB에 존재하는 adminid로 로그인이 되어 있는 지 확인
	 * @param attribute - session에 저장되어 있는 adminid
	 * @return 있을 시 true, 없을 시 false
	 */
	public boolean authorAdmin(String attribute);
	
}
