package com.kh.strap.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.kh.strap.board.domain.Board;
import com.kh.strap.board.service.logic.BoardServiceImpl;


@Controller
public class BoardController {
	
	@Autowired
	private BoardServiceImpl bService;
	
	/**
	 * 게시글 작성 페이지 이동
	 * @return : "/board/boardWrite"
	 */
	@RequestMapping(value = "/board/writeView.strap", method = RequestMethod.GET)
	public String registerView() {

		return "/board/boardWrite";
	}
	
	/**
	 *  게시글 작성 페이지
	 * @param mv
	 * @param board
	 * @return mv.setViewName("/board/list.strap")
	 */
	@RequestMapping(value = "/board/boardWrite.strap", method = RequestMethod.POST)
	public ModelAndView boardWrite(ModelAndView mv, 
			@ModelAttribute Board board) {
		
		int result = bService.registerBoard(board);
		try {
			if (result > 0) {
				mv.setViewName("redirect:/board/list.strap?currnentPage=1");
			} else {
				mv.addObject("msg", "게시물 저장에 실패하였습니다.").setViewName("/common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage()).setViewName("/common/errorPage");
		}
		return mv;
	}
	
	/**
	 * 게시판 목록 페이지 출력
	 * @param mv
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/board/list.strap", method=RequestMethod.GET)
	public ModelAndView boardListView(
			ModelAndView mv
			,@RequestParam(value="page", required=false) Integer page) {
		int currentPage = (page != null) ? page : 1;
		int totalCount = bService.getTotalCount("","");
		int boardLimit = 10;
		int naviLimit = 5;
		int maxPage;
		int startNavi;
		int endNavi;
		maxPage = (int)((double)totalCount/boardLimit + 0.9);
		startNavi = ((int)((double)currentPage/naviLimit+0.9)-1)*naviLimit+1;
		endNavi = startNavi + naviLimit - 1;
		if(maxPage < endNavi) {
			endNavi = maxPage;
		}
		List<Board> bList = bService.printAllBoard(currentPage, boardLimit);
		if(!bList.isEmpty()) {
			mv.addObject("urlVal", "list");
			mv.addObject("maxPage", maxPage);
			mv.addObject("currentPage", currentPage);
			mv.addObject("startNavi", startNavi);
			mv.addObject("endNavi", endNavi);
			mv.addObject("bList", bList);
		}
		mv.setViewName("board/boardListView");
		return mv;
	}
	
	/**
	 * 게시판 검색
	 * @param mv
	 * @param search
	 * @param currentPage
	 * @return
	 */
	@RequestMapping(value="/board/search.strap",method=RequestMethod.GET)
	public ModelAndView boardSearchView(
			ModelAndView mv
			, @RequestParam("searchCondition") String searchCondition
			, @RequestParam("searchValue") String searchValue
			, @RequestParam(value="page", required=false) Integer page) {
		try {
			int currentPage = (page != null) ? page : 1;
			int totalCount = bService.getTotalCount(searchCondition, searchValue);
			int boardLimit = 10;
			int naviLimit = 5;
			int maxPage;
			int startNavi;
			int endNavi;
			maxPage = (int)((double)totalCount/boardLimit + 0.9);
			startNavi = ((int)((double)currentPage/naviLimit+0.9)-1)*naviLimit+1;
			endNavi = startNavi + naviLimit - 1;
			if(maxPage < endNavi) {
				endNavi = maxPage;
			}
			List<Board> bList = bService.printSearchBoard(
					searchCondition, searchValue, currentPage, boardLimit);
			if(!bList.isEmpty()) {
				mv.addObject("bList", bList);
			}else {
				mv.addObject("bList", null);
			}
			mv.addObject("urlVal", "search");
			mv.addObject("searchCondition", searchCondition);
			mv.addObject("searchValue", searchValue);
			mv.addObject("currentPage", currentPage);
			mv.addObject("maxPage", maxPage);
			mv.addObject("startNavi", startNavi);
			mv.addObject("endNavi", endNavi);
			mv.setViewName("board/listView");
		} catch (Exception e) {
			mv.addObject("msg", e.toString()).setViewName("common/errorPage");
		}
		return mv;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/board/uploadSummernoteImageFile", method = RequestMethod.POST)
	public JsonObject uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile,
			HttpServletRequest request) {
		
		JsonObject jsonObject = new JsonObject();
		try {
			// 에디터에서 업로드한 file을 MultipartFile로 받았다
			
			// 1.파일 이름과 경로를 설정한다
			String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
			System.out.println(originalFileName);
			String root = request.getSession().getServletContext().getRealPath("resources");
			String savePath = root + "\\image\\board\\summerImageFiles";	//저장될 외부 파일 경로
			
			//2.파일이름이 중복되지 않도록 재정의 해준다
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);	//파일 확장자
			String boardFileRename = sdf.format(new Date(System.currentTimeMillis())) + "." + extension;
			
			// 3.저장할 경로의 폴더(디렉토리)가 없으면 새로 만든다
			File targetFile = new File(savePath);
			if (!targetFile.exists()) {
				targetFile.mkdir();
			}
		
			// 4.설정한경로에 재정의한 이름으로 파일을 저장한다
			multipartFile.transferTo(new File(savePath + "\\" + boardFileRename));
		
			// 5.ajax의 success로 리턴해줄 json오브젝트에 프로퍼티를 저장해준다
			// 1)썸머노트의 insertImage 설정값에 넣어줄 파일의 경로
			// 2)원래 파일이름
			// 3)ajax 성공여부
			jsonObject.addProperty("url", "/resources/image/board/summerImageFiles/" + boardFileRename);
			jsonObject.addProperty("originName", originalFileName);
			jsonObject.addProperty("responseCode", "success");
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		return jsonObject;
	}
}
