package cn.edu.bupt.webapi.demo.controller;

import cn.edu.bupt.webapi.demo.entity.Book;
import cn.edu.bupt.webapi.demo.exception.NotFoundException;
import cn.edu.bupt.webapi.demo.mapper.BookMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
//@Tag注解用来定义一组API
@Tag(name = "BookController v1", description = "利用HTTP状态码表示操作结果状态的控制器")
@RequestMapping("/api/v1/books")
public class BookController {

    @Resource
    BookMapper bookMapper;

    //@Operation用来对API添加说明
    @Operation(summary = "获取booklist", description = "返回值为全部的booklist")
    @GetMapping(path = "/", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Book> listAll(){
        List<Book> books = bookMapper.selectList(null);
        return books;
    }

    @Operation(summary = "根据id获取相应的book", description = "如果不存在，返回状态码为404")
    @GetMapping(path = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Book getById(@PathVariable("id") Long id) {
        Book book = bookMapper.selectById(id);
        if (book == null) {
            throw new NotFoundException();
        }
        return book;
    }

    @Operation(summary = "添加新的book", description = "创建成功，返回状态码201")
    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        bookMapper.insert(book);
        return book;
    }

    @Operation(summary = "根据id删除book")
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        if(bookMapper.deleteById(id) <=0){
            throw new NotFoundException();
        }
        return;
    }

    @Operation(summary = "更新book", description = "如果不存在，则返回状态码404")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public Book update(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        if (bookMapper.updateById(book) <= 0) {
            throw new NotFoundException();
        }
        return book;
    }
}
