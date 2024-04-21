package cn.edu.bupt.webapi.demo.controller;

import cn.edu.bupt.webapi.demo.common.Result;
import cn.edu.bupt.webapi.demo.entity.Book;
import cn.edu.bupt.webapi.demo.mapper.BookMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Tag(name = "BookController v2", description = "利用自定义消息格式的控制器")
@RequestMapping("/api/v2/books")
public class AnotherBookController {
    @Resource
    BookMapper bookMapper;

    @GetMapping(path = "/", produces = "application/json")
    public Result<?> listAll() {
        List<Book> books = bookMapper.selectList(null);
        if (books.isEmpty()) {
            return Result.error(204, "books为空");
        }
        return Result.ok(books);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public Result<?> getById(@PathVariable("id") Long id) {
        Book book = bookMapper.selectById(id);
        if (book != null) {
            return Result.ok(book);
        } else {
            return Result.error(404, "该book不存在");
        }
    }

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public Result<?> add(@RequestBody Book book) {
        bookMapper.insert(book);
        return Result.ok(book);
    }


    @DeleteMapping(path = "/{id}")
    public Result<?> delete(@PathVariable Long id) {
        if (bookMapper.deleteById(id) >0) {
            return Result.ok();
        } else {
            return Result.error(204, "该book不存在，删除失败");
        }
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public Result<?> update(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        if (bookMapper.updateById(book) > 0) {
            return Result.ok(book);
        } else {
            return Result.error(404, "该book不存在");
        }
    }
}
