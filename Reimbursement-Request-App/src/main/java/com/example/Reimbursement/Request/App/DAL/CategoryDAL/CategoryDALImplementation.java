package com.example.Reimbursement.Request.App.DAL.CategoryDAL;

import com.example.Reimbursement.Request.App.Entities.Category;
import com.example.Reimbursement.Request.App.Utilities.DatabaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDALImplementation implements CategoryDALInterface {
    public static Logger logger = LogManager.getLogger(CategoryDALImplementation.class);

    @Override
    public Category addCategory(Category category) {
        logger.info("Beginning DAL method add category with data: /n" + category);
        try (Connection connection = DatabaseConnection.createConnection()) {
            String sql = "insert into reimbursement_request_app.categories values (0, ?);";
            PreparedStatement ps = null;
            if (connection != null) {
                ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, category.getCategoryName());
                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                category.setCategoryId(rs.getInt("category_id"));
            }
            logger.info("Finishing DAL method add category with result: /n" + category);
            return category;
        } catch (SQLException error) {
            error.printStackTrace();
            logger.error("Error with DAL method add category with error: " + error.getMessage());
            return null;
        }
    }

    @Override
    public List<Category> getAllCategories() {
        logger.info("Beginning DAL method get all categories");
        try (Connection connection = DatabaseConnection.createConnection()) {
            String sql = "select * from reimbursement_request_app.categories;";
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Category> categoryList = new ArrayList<>();
            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("category_id"),
                        rs.getString("category_name")
                );
                categoryList.add(category);
            }
            logger.info("Finishing DAL method get all categories with result: " + categoryList);
            return categoryList;
        } catch (SQLException error) {
            error.printStackTrace();
            logger.error("Error with DAL method get all categories with error: " + error.getMessage());
            return null;
        }
    }

    @Override
    public Category getCategoryById(int categoryId) {
        return null;
    }

    @Override
    public Category updateCategory(Category category) {
        return null;
    }

    @Override
    public boolean deleteCategory(int categoryId) {
        return false;
    }
}
