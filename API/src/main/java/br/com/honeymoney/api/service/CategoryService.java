package br.com.honeymoney.api.service;

import br.com.honeymoney.api.dao.CategoryDAO;
import br.com.honeymoney.api.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    public List<Category> findAll() {
        return categoryDAO.findAll();
    }




}
