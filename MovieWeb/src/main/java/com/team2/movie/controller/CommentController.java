package com.team2.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.team2.movie.dao.api.CommentDao;
import com.team2.movie.dao.dto.CommentBoard;

@Controller
public class CommentController {
   
   @Autowired
   private CommentDao commentDao;
   


//   @GetMapping("/main")
//   public String list(Model model) {
//      List<CommentBoard> commentBoardList = commentDao.findAll();
//      model.addAttribute("commentBoardList", commentBoardList);
//      return "/main";
//   }
   
   @GetMapping("/main")
   public String commentform(Model model, int commentId, String kakaoId){
      List<CommentBoard> commentBoardList = commentDao.findAll();
//      if(kakaoId==null) {
//         model.addAttribute("commentBoard", new CommentBoard());
//      } else {
//         CommentBoard commentBoard = commentDao.findById(kakaoId).orElse(null);
//         model.addAttribute("commentBoard", commentBoard);
//      }
      model.addAttribute("commentBoardList", commentBoardList);
      return "/main";
   }
   
   @PostMapping("/main")
   public String commentSubmit(@ModelAttribute CommentBoard commentBoard) {
      commentDao.save(commentBoard);
      
      
      return "redirect:/main";
   
      
      
      
//   @RequestMapping("/main")
//   public String view(Model model) {
//      List<CommentBoard> commentList = commentDao.findAll();
//      model.addAttribute("CommentList", commentList);
//      return "/main";}
//   
//   @RequestMapping("/main/Insert")
//   public String insert(
//         Model model,
//         HttpServletRequest request, @RequestParam("content") String content
//         ) {CommentBoard commentBoard = new CommentBoard();
//         commentBoard.setContent(content);
//         commentDao.save(commentBoard);
//         
//         return "redirect:/main";
//   
   

   
      
      
      
//   @GetMapping("/board")
//   public String Board(Model model) {
//      List<CommentBoard> board = commentDao.findAll();
//      model.addAttribute("board", board);
//      return "/comment";
//   }
      
   }
}