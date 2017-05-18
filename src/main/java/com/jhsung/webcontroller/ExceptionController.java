package com.jhsung.webcontroller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhsung.common.config.URL;
import com.jhsung.common.exception.CustomException;
import com.jhsung.common.util.RequestUtil;

@RestController
public class ExceptionController implements URL {

	@RequestMapping(value = EX_CONTROL, method = GET)
	public void exControll(HttpServletRequest request) {
		throw new CustomException(RequestUtil.getErrorName(request));
	}

}
