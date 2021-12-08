package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.service.JwtServiceImpl;
import com.ssafy.happyhouse.model.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/user")
@Api("사용자 컨트롤러  API V1")
public class MemberController {

	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private MemberService memberService;

	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberDto memberDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			MemberDto loginUser = memberService.login(memberDto);
			if (loginUser != null) {
				String token = jwtService.create("userid", loginUser.getUserid(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				MemberDto memberDto = memberService.userInfo(userid);
				resultMap.put("userInfo", memberDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "삭제", notes = "유저 아이디에 해당하는 회원을 탈퇴한다.", response = String.class)
	@DeleteMapping("/co/{userid}")
	public ResponseEntity<String> deleteComment(
			@PathVariable("userid") @ApiParam(value = "삭제eeqw할 유저의 아이디.", required = true) String userid) throws Exception {
		logger.info("deleteComment - 호출");
		if (memberService.deleteComment(userid)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "삭제", notes = "유저 아이디에 해당하는 회원을 탈퇴한다.", response = String.class)
	@DeleteMapping("/bo/{userid}")
	public ResponseEntity<String> deleteBoard(
			@PathVariable("userid") @ApiParam(value = "삭제ee할 유저의 아이디.", required = true) String userid) throws Exception {
		logger.info("deleteBoard - 호출");
		if (memberService.deleteBoard(userid)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "삭제", notes = "유저 아이디에 해당하는 회원을 탈퇴한다.", response = String.class)
	@DeleteMapping("/wi/{userid}")
	public ResponseEntity<String> deleteWishList(
			@PathVariable("userid") @ApiParam(value = "삭제할 유qq저의 아이디.", required = true) String userid) throws Exception {
		logger.info("deleteWishList - 호출");
		if (memberService.deleteWishList(userid)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "삭제", notes = "유저 아이디에 해당하는 회원을 탈퇴한다.", response = String.class)
	@DeleteMapping("/nt/{userid}")
	public ResponseEntity<String> deleteNotice(
			@PathVariable("userid") @ApiParam(value = "삭제할 유저의 we아이디.", required = true) String userid) throws Exception {
		logger.info("deleteNotice - 호출");
		if (memberService.deleteNotice(userid)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "회원 탈퇴", notes = "유저 아이디에 해당하는 회원을 탈퇴한다.", response = String.class)
	@DeleteMapping("/{userid}")
	public ResponseEntity<String> deleteMember(
			@PathVariable("userid") @ApiParam(value = "삭제할 유저의 아이디.", required = true) String userid) throws Exception {
		logger.info("deleteMember - 호출");
		if (memberService.deleteMember(userid)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "회원 가입", notes = "새로운 회원 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> registerMember(
			@RequestBody @ApiParam(value = "게시글 정보.", required = true) MemberDto memberDto) throws Exception {
		logger.info("registerMember - 호출");
		if (memberService.registerMember(memberDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "회원 정보 수정", notes = "회원 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> updateMember(
			@RequestBody @ApiParam(value = "수정할 글정보.", required = true) MemberDto memberDto) throws Exception {
		logger.info("updateMember - 호출");
		if (memberService.updateMember(memberDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}

	@ApiOperation(value = "ID 중복체크", notes = "기존에 가입한 ID인지 체크한다.", response = String.class)
	@GetMapping("/{userid}")
	public ResponseEntity<String> idCheck(
			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid) throws Exception {
		logger.info("idCheck - 호출");
		logger.info(userid);
		if (memberService.idCheck(userid)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
//	@ApiOperation(value = "ID 중복체크", notes = "기존에 가입한 ID인지 체크한다.", response = String.class)
//	@GetMapping("/{userid}")
//	public ResponseEntity<Boolean> idCheck(
//			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid) throws Exception {
//		logger.info("idCheck - 호출");
//		logger.info(userid);
//		if (memberService.idCheck(userid)) {
//			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
//		}
//		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
//	}
//	@ApiOperation(value = "ID 중복체크", notes = "기존에 가입한 ID인지 체크한다.", response = String.class)
//	@GetMapping("/{userid}")
//	public ResponseEntity<String> idCheck( @PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid) throws Exception {
//		logger.info("idCheck - 호출");
//		logger.info(userid);
//		if(memberService.idCheck(userid)) {
//			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		}
//		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
//	}
}
