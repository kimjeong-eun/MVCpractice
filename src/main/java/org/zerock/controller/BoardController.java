package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller //spring이 관여
@Log4j2
@RequestMapping("/board/*") //http://localhost:80/board/????
@AllArgsConstructor // 스프링이 알아서 생성자 필요시 만들어준다.
public class BoardController {

	//만약 생성자를 만들지 않을겨우 @setter 어노테이션으로 자동 주입하면 됨.
	private BoardService service; //boardcontroller가 동작하면 자동으로 서비스도 만들어짐
	
	@GetMapping("/list") //http://localhost/board/list
	public void list(Model model){
		
		log.info("=======================");
		log.info("list컨트롤러 메서드 실행 중");
		log.info("=======================");
		
		model.addAttribute("list", service.getList());
		//모델 영역에 list라는 이름으로 결과를 저장한다. (select * from 테이블 bno>0)		
	} //리턴타입이 void이면 매핑명으로 된 jsp파일을 찾는다. (/WEB-INF/views/board/list.jsp
	
	@PostMapping("/register") //http://localhost/board/register
	public String register(BoardVO board , RedirectAttributes rttr) {
		// RedirectAttributes rttr 1회용으로 값 저장용
		// BoardVO board : form에서 넘어온 객체
		log.info("register 컨트롤러 메서드 실행..." + board);
		service.register(board); // 서비스 계층을 통해서 매퍼로 값이 전달
		
		rttr.addFlashAttribute("result", board.getBno()); //1회용 값으로 jsp에게 전달
								//key   ,   value
		return "redirect:/board/list"; // 메서드 실행후 경로를 지정한다.	
		
	}
	@GetMapping("/get") //http://localhost/board/get?bno=1
	public void get(@RequestParam("bno") Long bno, Model model) {
		//@RequestParam("bno") Long bno -> url로 넘어온 bno를 롱타입이 bno변수에 넣음
		//Model model -> Spring이 관리하는 메모리 영역에 값을 가져옴

		log.info("/get 컨트롤러 메서드 실행 .... bno : " + bno);
		model.addAttribute("board",service.get(bno));
		//스프링이 관리하는 모델 영역에 board라는 이름으로 값(BoardVO)을 추가한다.
	}
	
	@PostMapping("/modify") //http://localhost/board/modify
	public String modify(BoardVO board , RedirectAttributes rttr) {
		
		log.info("modify 컨트롤러 메서드 실행중 ....... " + board);
		if(service.modify(board)) { //true일 때 (mapper에서는 1을 넘겨주고 1이면 service에서 true로 보냄)
			rttr.addFlashAttribute("result","success");
		}
		return "redirect:/board/list"; //처리 후 돌아갈 페이지
	}
	
	@PostMapping("/remove") // http://localhost/board/remove? bno = 2
	public String remove(@RequestParam("bno") Long bno , RedirectAttributes rttr ) {
		
		log.info("remove 컨트롤러 메서드 실행 중......... 받은 번호는 ? : " + bno);
		if(service.remove(bno)) { //삭제가 성공시 true가 넘어옴
			rttr.addFlashAttribute("result","success"); // 1회용 값으로 전달됨
		}
		
		return "redirect:/board/list";
		
	}
}
