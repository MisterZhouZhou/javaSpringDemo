package com.zw.kotlinjpa.controller

import com.alibaba.fastjson.JSONObject
import com.zw.kotlinjpa.entify.Author
import com.zw.kotlinjpa.service.AuthorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping(value = "/authors")
class AuthorController {
    @Autowired
    private lateinit var authorService: AuthorService


    /**
     * 查询用户列表
     */
    @RequestMapping(value = "/allAuthor",method = [RequestMethod.GET])
    fun getAuthorList(request: HttpServletRequest): Map<String, Any> {
        val authorList = this.authorService.findAuthorList()
        val param = HashMap<String, Any>()
        param["total"] = authorList.size
        param["rows"] = authorList
        return param
    }


    /**
     * 查询用户信息
     */
    //请求方式    http://localhost:8080/authors/getAuthor/1
    @RequestMapping(value = "/getAuthor/{userId:\\d+}", method = [RequestMethod.GET])
    fun getAuthor(@PathVariable userId: Long, request: HttpServletRequest): Author {
        return authorService.findAuthor(userId) ?: throw RuntimeException("查询错误")
    }

    /**d
     * 查询用户信息
     */
    //请求方式    http://localhost:8080/authors/getAuthor?id=1
    @RequestMapping(value = "/getAuthor", method = [RequestMethod.GET])
    fun getAuthor(id: Long): Author {
        return authorService.findAuthor(id) ?: throw RuntimeException("查询错误")
    }


    /**
     * 新增方法
     */
    @RequestMapping(value = "/addAuthor", method = [RequestMethod.POST])
    fun addAuthor(@RequestBody jsonObject: JSONObject): String {
        //  val userId = jsonObject.getString("user_id")
        val realName = jsonObject.getString("real_name")
        val nickName = jsonObject.getString("nick_name")

        val author = Author()
        //  author.id = java.lang.Long.valueOf(userId)
        author.realName = realName
        author.nickName = nickName
        try {
            this.authorService.add(author)
            return "success"
        } catch (e: Exception) {
            throw RuntimeException("新增错误")
        }
    }


    /**
     * 更新方法
     */
    @RequestMapping(value = "/updateAuthor/{userId:\\d+}", method = [RequestMethod.PUT])
    fun updateAuthor(@PathVariable userId: Long, @RequestBody jsonObject: JSONObject) : String {
        var author = this.authorService.findAuthor(userId)
        val realName = jsonObject.getString("real_name")
        val nickName = jsonObject.getString("nick_name")
        try {
            if (author != null) {
                author.realName = realName
                author.nickName = nickName
                this.authorService.update(author)
            }else{
                return "没有改用户"
            }
            return "success"
        } catch (e: Exception) {
            throw RuntimeException("更新错误")
        }
    }



    /**
     * 删除方法
     */
    @RequestMapping(value = "deleteAuthor/{userId:\\d+}", method = [RequestMethod.DELETE])
    fun deleteAuthor(@PathVariable userId: Long) : String {
        try {
            this.authorService.delete(userId)
            return "success"
        } catch (e: Exception) {
            throw RuntimeException("删除错误")
        }
    }


}