package cn.edu.bupt.webapi.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@TableName("books")
public class Book {
    //主键注解,数据库 ID 自增
    @TableId(type= IdType.AUTO)
    private Long id;
    @NotBlank
    @Size(min = 0, max = 255)
    private String title;
    private String author;
    private String isbn;
    private Integer pageCount;
}
