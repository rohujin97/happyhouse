package com.ssafy.happyhouse.controller;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.WishListDto;
import com.ssafy.happyhouse.model.service.HappyHouseMapService;

@RestController
@RequestMapping("/map")
public class HappyHouseMapController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private HappyHouseMapService happyHouseMapService;

	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(happyHouseMapService.getSido(), HttpStatus.OK);
	}

	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(happyHouseMapService.getGugunInSido(sido), HttpStatus.OK);
	}

	@GetMapping("/dong")
	public ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(happyHouseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}

	@GetMapping("/apt")
	public ResponseEntity<String> apt(@RequestParam("gugun") String gugun, @RequestParam("time") String time) throws Exception {
		return new ResponseEntity<String>(happyHouseMapService.getAptInDong(gugun, time), HttpStatus.OK);
	}

	@GetMapping("/check")
	public ResponseEntity<Integer> check(@RequestParam Map<String, String> param) throws Exception {
		return new ResponseEntity<Integer>(happyHouseMapService.checkUserHouse(param.get("houseName"), param.get("dongName")), HttpStatus.OK);
	}
	
	@GetMapping("/starhouse")
	public ResponseEntity<List<HouseDealDto>> starhouse(@RequestParam("userid") String userid) throws Exception {
		return new ResponseEntity<List<HouseDealDto>>(happyHouseMapService.getStarHouse(userid), HttpStatus.OK);
	}
	
	@PostMapping("/userhouse")
	public ResponseEntity<String> userhouse(@RequestBody WishListDto wishListDto) throws Exception {
		if (happyHouseMapService.putUserHouse(wishListDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/star")
	public ResponseEntity<String> star(@RequestBody HouseDealDto houseDealDto) throws Exception {
		if (happyHouseMapService.checkStar(houseDealDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/plus")
	public ResponseEntity<String> plus(@RequestBody HouseDealDto houseDealDto) throws Exception {
		if (happyHouseMapService.plusStar(houseDealDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/minus")
	public ResponseEntity<String> minus(@RequestBody HouseDealDto houseDealDto) throws Exception {
		if (happyHouseMapService.minusStar(houseDealDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/delete/{userid}&{aptdong}")
	public ResponseEntity<String> nohouse(@PathVariable String userid, @PathVariable String aptdong) throws Exception {
		if (happyHouseMapService.deleteUserHouse(userid, aptdong)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@GetMapping("/aptSearch")
	public ResponseEntity<List<HouseInfoDto>> aptSearch(@RequestParam("name") String name, @RequestParam("dong") String dong) throws Exception {
		System.out.println(name);
		return new ResponseEntity<List<HouseInfoDto>>(happyHouseMapService.getAptInName(name, dong), HttpStatus.OK);
	}
}
