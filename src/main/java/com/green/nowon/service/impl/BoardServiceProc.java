package com.green.nowon.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.green.nowon.domain.dto.board.BoardDetailDTO;
import com.green.nowon.domain.dto.board.BoardListDTO;
import com.green.nowon.domain.dto.board.BoardSaveDTO;
import com.green.nowon.domain.dto.board.BoardUpdateDTO;
import com.green.nowon.domain.dto.board.GenBoardDetailDTO;
import com.green.nowon.domain.dto.board.GenBoardListDTO;
import com.green.nowon.domain.dto.board.GenBoardSaveDTO;
import com.green.nowon.domain.dto.board.GenBoardUpdateDTO;
import com.green.nowon.domain.entity.board.BoardEntity;
import com.green.nowon.domain.entity.board.BoardEntityRepository;
import com.green.nowon.domain.entity.board.BoardImgEntityRepository;
import com.green.nowon.domain.entity.board.GenBoardEntityRepository;
import com.green.nowon.domain.entity.board.GeneralBoardEntity;
import com.green.nowon.domain.entity.member.MemberEntity;
import com.green.nowon.domain.entity.member.MemberEntityRepository;
import com.green.nowon.service.BoardService;
import com.green.nowon.util.MybFileUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceProc implements BoardService{

	@Value("${file.location.noticetemp}")
	private String locationTemp;
	
	@Value("${file.location.noticeupload}")
	private String locationUpload;
	
	@Autowired
	private final BoardEntityRepository repository;
	@Autowired
	private final GenBoardEntityRepository geRepo;
	@Autowired
	private final BoardImgEntityRepository imgRepo; //이미지
	@Autowired
	private final MemberEntityRepository memRepo;//멤버
	
	
	@Transactional
	@Override
	public void getListAll(int page, Model model) {
		//board list를 페이지로 전송
		
		//페이징 작업 pageable이용
		int size=5;
		Sort sort=Sort.by(Direction.DESC, "bno");
		
		Pageable pageable=PageRequest.of(page-1, size ,sort);
		Page<BoardEntity> result=repository.findAll(pageable);
		
		
		
		int nowPage = result.getNumber()+1;
		int startPage = Math.max(nowPage -3, 1);
		int endPage = Math.min(nowPage +3, result.getTotalPages());
		int totPage= result.getTotalPages();
		
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("totPage", totPage);
		
		model.addAttribute("p", result);
		model.addAttribute("list", result.stream()
				.map(BoardListDTO::new)
				.collect(Collectors.toList()));
	}

    //검색, 페이징
    @Transactional
	@Override
	public void search(String keyword,  Model model, int page) {
    	
		/*
		 * List<BoardListDTO> searchList =
		 * repository.findByTitleContaining(keyword).stream().map(BoardListDTO::new)
		 * .collect(Collectors.toList());
		 */ //검색만
    	
    	int size=5;
 		Sort sort=Sort.by(Direction.DESC, "bno");
 		Pageable pageable=PageRequest.of(page-1, size ,sort);
 		
 		Page<BoardEntity> result=repository.findByTitleContaining(keyword, pageable); //검색, 페이징 동시에
 		
 		int nowPage = result.getNumber()+1;
 		int startPage = Math.max(nowPage -3, 1);
 		int endPage = Math.min(nowPage +3, result.getTotalPages());
 		int totPage= result.getTotalPages();
 		
 		model.addAttribute("nowPage", nowPage);
 		model.addAttribute("startPage", startPage);
 		model.addAttribute("endPage", endPage);
 		model.addAttribute("totPage", totPage);
 		
 		model.addAttribute("keyword", keyword);
    	model.addAttribute("p", result);
    	model.addAttribute("searchList", result.stream().map(BoardListDTO::new).collect(Collectors.toList())); //검색, 페이징 동시에
		
    	//System.err.println(">>>>>>>>>>>>"+keyword);
	}
	
	@Transactional
	@Override
	public void sendDetail(long bno, Model model) {
		
		Optional<BoardEntity> temp= repository.findById(bno);
		
		System.out.println(">>>>>>>>>>>>>>>>"+temp.isEmpty());
		
		model.addAttribute("detail", repository.findById(bno)
				.map(BoardDetailDTO::new)
				.orElseThrow());
		
	}
	

	@Override
	public void save(BoardSaveDTO dto, String name) {
	}
	
	//등록
	@Transactional
	@Override
	public void save(BoardSaveDTO dto) {
		
		BoardEntity entity=BoardEntity.builder()
				.title(dto.getTitle()).content(dto.getContent())
				.member(MemberEntity.builder().mno(dto.getMno()).build())
				.build();
		repository.save(entity);
		
		dto.toBoardListImgs(entity, locationUpload).forEach(imgRepo::save); //이미지
		
	}
	
	@Override //이미지
	public Map<String, String> fileTempUpload(MultipartFile bimg) {
		
		return MybFileUtils.fileUpload(bimg, locationTemp);
	}

	@Transactional
	@Override
	public void delete(long bno) {
		repository.deleteById(bno);
	}

	@Transactional
	@Override
	public void update(long bno, BoardUpdateDTO dto) {
		BoardEntity entityImg=null;
		
		//findById(bno) 통해서 엔티티의 데이터 찾음 -> result
		Optional<BoardEntity> result= repository.findById(bno);
		
		//존재하면 수정
		if(result.isPresent()) {
			BoardEntity entity=result.get();
			//이미지 저장 전에 삭제
			imgRepo.deleteByBoard_bno(bno);
			entity.update(dto); //board 엔티티에 있는 편의메서드
			//업데이트 반영
			entityImg = repository.save(entity);
			//이미지 저장
			dto.toListImgs(entityImg, locationUpload).forEach(imgRepo::save);
		}
		
	}
	
	//공지사항 조회수
    @Transactional
    public int updateReadCount(Long bno) {
        return repository.updateReadCount(bno);
    }
    

	
    
    
	/* 여기서부터 자유게시판 입니다 */
    
	
    
	@Transactional
	@Override
	public void getListAll02(int page, Model model) {
		//board list를 페이지로 전송
		int size=5;
		Sort sort=Sort.by(Direction.DESC, "bno");
		
		
		List<MemberEntity> writerMemList = memRepo.findAll();
		HashMap<String , String> writerMemMap=new HashMap<>();
		for(MemberEntity mem : writerMemList) {
			writerMemMap.put(String.valueOf(mem.getMno()), mem.getName());
		
		}
		
		
		Pageable pageable=PageRequest.of(page-1, size, sort);
		Page<GeneralBoardEntity> result=geRepo.findAll(pageable);
		int nowPage = result.getNumber()+1;
		int startPage = Math.max(nowPage-3, 1);
		int endPage = Math.min(nowPage+3, result.getTotalPages());
		int totPage= result.getTotalPages();
		
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("totPage",totPage);
		
		model.addAttribute("p2", result);
		model.addAttribute("list2", result.stream()
				.map(GenBoardListDTO::new)
				.collect(Collectors.toList()));
		model.addAttribute("writerMemMap",writerMemMap);
		
	}

	@Override //자유게시판 상세페이지
	public void sendDetail02(long bno, Model model) {
		
		GenBoardDetailDTO boDetail = geRepo.findById(bno).map(GenBoardDetailDTO::new).orElseThrow();
		Optional<MemberEntity> writerMem = memRepo.findById(boDetail.getMno());
		boDetail.setWriterId(writerMem.get().getId());
		model.addAttribute("writerName", writerMem.get().getName());
		model.addAttribute("detail2", boDetail);

		
//		model.addAttribute("detail2", repo.findById(bno)
//				.map(GenBoardDetailDTO::new)
//				.orElseThrow());
		
	}

	@Override
	public void save02(GenBoardSaveDTO dto, String name) {
	}
	
	@Override
	public void save02(GenBoardSaveDTO dto) {
		
		GeneralBoardEntity entity=GeneralBoardEntity.builder()
				.title(dto.getTitle()).content(dto.getContent())
				.mno(dto.getMno())
				.build();
				//.member(MemberEntity.builder().mno(dto.getMno()).build()).build();
		geRepo.save(entity);
		
	}

	@Override
	public void delete02(long bno) {
		geRepo.deleteById(bno);
	}

	@Override
	public void update02(long bno, GenBoardUpdateDTO dto) {
		Optional<GeneralBoardEntity> result= geRepo.findById(bno);
		
		//존재하면 수정
		if(result.isPresent()) {
			GeneralBoardEntity entity=result.get();
			entity.update(dto);
			//업데이트 반영
			geRepo.save(entity);//이미 존재하는 Pk이면 수정됨
		}
		
	}
	
	//자유조회수
    @Transactional
    public int genUpdateReadCount(Long bno) {
        return geRepo.genUpdateReadCount(bno);
    }
    
	//자유 검색
	@Transactional
	@Override
	public void search02(String keyword, Model model, int page) {
		/* 검색만 할 때
		 * 
		 * List<GenBoardListDTO> searchResult= repo.findByTitleContaining(keyword)
		 * .stream().map(GenBoardListDTO::new).collect(Collectors.toList());
		 * model.addAttribute("searchResult", searchResult);
		 */
		
		int size=5;
		Sort sort= Sort.by(Direction.DESC, "bno");
		Pageable pageable= PageRequest.of(page-1, size, sort);
		Page<GeneralBoardEntity> result = geRepo.findByTitleContaining(keyword, pageable);
		
		int nowPage=result.getNumber()+1;
		int startPage=Math.max(nowPage-3, 1);
		int endPage=Math.min(nowPage+3, result.getTotalPages());
		int totPage= result.getTotalPages();
		
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("totPage",totPage);
		model.addAttribute("keyword", keyword);
		
		model.addAttribute("p", result);
		model.addAttribute("searchList", result.stream().map(GenBoardListDTO::new).collect(Collectors.toList()));
		
	

	}
	
	

    
	//mypage에 띄우는 리스트
    
	@Transactional
	@Override
	public void myGetListAll(int page, Model model) {
		
		int size=5;
		Sort sort=Sort.by(Direction.DESC, "bno");
		
		Pageable pageable=PageRequest.of(page-1, size ,sort);
		Page<BoardEntity> result=repository.findAll(pageable);
		
		
		model.addAttribute("list", result.stream().map(BoardListDTO::new).collect(Collectors.toList()));
		
	}

	@Transactional
	@Override
	public void myGetListAll02(Model model,long mno) {
		
//		List<GeneralBoardEntity> board=repo.findByMnoOrderByBnoDesc(mno);
//		System.out.println(" >>>> : "+board.size());
		
		List<GenBoardListDTO> result=geRepo.findByMnoOrderByBnoDesc(mno).stream()
				.map(GenBoardListDTO::new).collect(Collectors.toList());
		
		Optional<MemberEntity> writerMem = memRepo.findById(mno);
		
		System.out.println(" >>>>리이이이이절트 : "+result.size());
		
		for(GenBoardListDTO bo : result) {
			System.out.println(">>>>>>>>>>>>>>>>"+bo.getTitle());
			System.out.println(">>>>>>>>>>>>>>>>"+bo.getWriterId());
		}
		
		model.addAttribute("list2", result);
		model.addAttribute("writerName",writerMem.get().getName());
	}

}
