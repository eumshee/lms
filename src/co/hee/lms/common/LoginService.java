package co.hee.lms.common;

public interface LoginService<T> {	// generic type
	public T login(T vo);			// 로그인 정보
	
}
