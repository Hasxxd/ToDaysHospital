package net.daum.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Action {

	public abstract ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	//추상메서드로 {}가 없고,호출이 불가능하고, 실행문장이 없다.
}
