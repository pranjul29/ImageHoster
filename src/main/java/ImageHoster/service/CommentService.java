package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

  @Autowired private CommentRepository commentRepository;

  public Comment addComment(Comment comment) {
    Comment newComment = commentRepository.addComment(comment);
    return newComment;
  }

  public List<Comment> getAllCommentsForImage(Image image) {
    return commentRepository.getAllCommentsForImage(image);
  }
}
