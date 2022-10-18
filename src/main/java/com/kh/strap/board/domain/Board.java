package com.kh.strap.board.domain;

import java.sql.Date;

public class Board {
	private int boardNo; 			// 게시글 번호
	private String boardWriter; 	// 작성자
	private String boardTitle; 		// 게시글 제목
	private String boardContents;	// 게시글 내용
	private String boardCategory; 	// 게시글 카테고리
	private String bFileName; 		// 파일 이름
	private String bFileRename; 	// 변경된 파일 이름
	private String bFilePath; 		// 파일 경로
	private Date boardDate; 		// 작성 날짜
	private int boardCount; 		// 게시글 조회수
	private int boardLike; 			// 게시글 추천수
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContents() {
		return boardContents;
	}
	public void setBoardContents(String boardContents) {
		this.boardContents = boardContents;
	}
	public String getBoardCategory() {
		return boardCategory;
	}
	public void setBoardCategory(String boardCategory) {
		this.boardCategory = boardCategory;
	}
	public String getbFileName() {
		return bFileName;
	}
	public void setbFileName(String bFileName) {
		this.bFileName = bFileName;
	}
	public String getbFileRename() {
		return bFileRename;
	}
	public void setbFileRename(String bFileRename) {
		this.bFileRename = bFileRename;
	}
	public String getbFilePath() {
		return bFilePath;
	}
	public void setbFilePath(String bFilePath) {
		this.bFilePath = bFilePath;
	}
	public Date getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}
	public int getBoardCount() {
		return boardCount;
	}
	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}
	public int getBoardLike() {
		return boardLike;
	}
	public void setBoardLike(int boardLike) {
		this.boardLike = boardLike;
	}
	
	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardWriter=" + boardWriter + ", boardTitle=" + boardTitle
				+ ", boardContents=" + boardContents + ", boardCategory=" + boardCategory + ", bFileName=" + bFileName
				+ ", bFileRename=" + bFileRename + ", bFilePath=" + bFilePath + ", boardDate=" + boardDate
				+ ", boardCount=" + boardCount + ", boardLike=" + boardLike + "]";
	}
}
