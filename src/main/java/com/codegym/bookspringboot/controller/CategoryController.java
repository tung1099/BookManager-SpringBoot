package com.codegym.bookspringboot.controller;

import com.codegym.bookspringboot.model.Book;
import com.codegym.bookspringboot.model.Category;
import com.codegym.bookspringboot.service.book.IBookService;
import com.codegym.bookspringboot.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IBookService bookService;

    @GetMapping("/category")
    public ModelAndView listCategory() {
        Iterable<Category> categories = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView("category/list");
        modelAndView.addObject("category", categories);
        return modelAndView;
    }

    @GetMapping("/create-category")
    public ModelAndView showCreateCategoryForm() {
        ModelAndView modelAndView = new ModelAndView("category/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }

    @PostMapping("/create-category")
    public ModelAndView saveProvince(@ModelAttribute("category") Category category) {
        categoryService.save(category);

        ModelAndView modelAndView = new ModelAndView("category/create");
        modelAndView.addObject("category", new Category());
        modelAndView.addObject("message", "Success!!!");
        return modelAndView;
    }

    @GetMapping("/edit-category/{id}")
    public ModelAndView showEditCategoryForm(@PathVariable Long id) {
        Optional<Category> category = categoryService.findById(id);
        if (category.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("category/edit");
            modelAndView.addObject("category", category.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-category")
    public ModelAndView updateCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("category/edit");
        modelAndView.addObject("category", category);
        modelAndView.addObject("message", "Success!!!");
        return modelAndView;
    }

    @GetMapping("/delete-category/{id}")
    public ModelAndView showDeleteCategoryForm(@PathVariable Long id) {
        Optional<Category> category = categoryService.findById(id);
        if (category.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("category/delete");
            modelAndView.addObject("category", category.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-category")
    public String deleteCategory(@ModelAttribute("category") Category category) {
        categoryService.remove(category.getId());
        return "redirect:category";
    }

    @GetMapping("/view-category/{id}")
    public ModelAndView viewCategory(@PathVariable("id") Long id){
        Optional<Category> categoryOptional = categoryService.findById(id);
        if(!categoryOptional.isPresent()){
            return new ModelAndView("error.404");
        }

        Iterable<Book> books = bookService.findAllByCategory(categoryOptional.get());

        ModelAndView modelAndView = new ModelAndView("category/view");
        modelAndView.addObject("category", categoryOptional.get());
        modelAndView.addObject("book", books);
        return modelAndView;
    }

    @PostMapping("/view-category")
    public String viewCategoryDetail(@ModelAttribute("category") Category category){
        categoryService.findById(category.getId());
        return "redirect:category";
    }

}
