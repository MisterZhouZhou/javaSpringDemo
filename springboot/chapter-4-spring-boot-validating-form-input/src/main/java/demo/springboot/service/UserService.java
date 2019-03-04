package demo.springboot.service;

import demo.springboot.domain.User;

import java.util.List;

/**
 * Book 业务接口层
 *
 * Created by bysocket on 27/09/2017.
 */
public interface UserService {
    /**
     * 获取所有 Book
     */
    List<User> findAll();

    /**
     * 新增 Book
     *
     * @param book {@link User}
     */
    User insertByUser(User book);

    /**
     * 更新 Book
     *
     * @param book {@link User}
     */
    User update(User book);

    /**
     * 删除 Book
     *
     * @param id 编号
     */
    User delete(Long id);

    /**
     * 获取 Book
     *
     * @param id 编号
     */
    User findById(Long id);
}
