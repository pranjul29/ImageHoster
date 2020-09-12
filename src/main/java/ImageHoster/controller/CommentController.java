package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {

  @Autowired private CommentService commentService;

  @Autowired private ImageService imageService;

  @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
  public String addComment(
      @PathVariable("imageTitle") String imageTitle,
      @PathVariable("imageId") String imageId,
      Model model,
      @RequestParam("comment") String comment,
      HttpSession session,
      Comment newComment) {
    newComment.setCreatedDate(new Date());
    newComment.setText(comment);
    User user = (User) session.getAttribute("loggeduser");
    newComment.setUser(user);
    Image image = imageService.getImage(Integer.parseInt(imageId));
    newComment.setImage(image);

    newComment = commentService.addComment(newComment);

    List<Comment> allComments = commentService.getAllCommentsForImage(image);
    model.addAttribute("comments", allComments);
    model.addAttribute("tags", image.getTags());
    return "redirect:/images" + "/" + imageId + "/" + imageTitle;
  }
}
