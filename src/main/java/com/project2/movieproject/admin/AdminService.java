package com.project2.movieproject.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.project2.movieproject.command.Criteria;
import com.project2.movieproject.command.MovieVO;

import com.project2.movieproject.command.adminVO;

public interface AdminService {
	public int movieRegist(MovieVO vo);
	public int movieSearch(MovieVO vo);
	public ArrayList<MovieVO> getMovieList();
	public MovieVO getMovieDetail(int movie_koficCd);
	
	public int noticeRegist(adminVO vo,MultipartFile list );//등록
	public ArrayList<adminVO> List(Criteria cri);//목록
	public int total(Criteria cri);//전체게시글수
	public adminVO getcontent(int admin_key);//상세
	public int hit(adminVO adminvo);//조회수
	public int update(adminVO adminvo);//수정
	public int delete(int admin_id);//삭제
	
}
