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

    public void accessCategoryTable(String sql) {
        logger.info("Beginning DAL method access category table with sql statement: " + sql);
        try (Connection connection = DatabaseConnection.createConnection()) {
            PreparedStatement ps = null;
            if (connection != null) {
                ps = connection.prepareStatement(sql);
                ps.execute();
            }
            logger.info("Finishing DAL method access employee table");
        } catch (SQLException error) {
            error.printStackTrace();
            logger.error("Error with DAL method access category table with error: " + error.getMessage());
        }
    }

    @Override
    public Category addCategory(Category category) {
        logger.info("Beginning DAL method add category with data: " + category);
        try (Connection connection = DatabaseConnection.createConnection()) {
            String sql = "insert into reimbursement_request_app.categories values (0, ?);";
            PreparedStatement ps = null;
            if (connection != null) {
                ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, category.getCategoryName());
                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                category.setCategoryId(rs.getInt(1));
            }
            logger.info("Finishing DAL method add category with result: " + category);
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
        logger.info("Beginning DAL method get account by ID with data: " + categoryId);
        try (Connection connection = DatabaseConnection.createConnection()) {
            String sql = "select * from reimbursement_request_app.categories where category_id=?;";
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, categoryId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Category category = new Category(
                    rs.getInt("category_id"),
                    rs.getString("category_name")
            );
            logger.info("Finishing DAL method get account by ID with result: " + category);
            return category;
        } catch (SQLException error) {
            error.printStackTrace();
            logger.error("Error with DAL method get category by ID with error: " + error.getMessage());
            return null;
        }
    }

    @Override
    public Category updateCategory(Category category) {
        logger.info("Beginning DAL method update category with data: " + category);
        try (Connection connection = DatabaseConnection.createConnection()) {
            String sql = "update reimbursement_request_app.categories set category_name=? where category_id=?;";
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, category.getCategoryName());
            ps.setInt(2, category.getCategoryId());
            ps.executeUpdate();
            logger.info("Finishing DAL method update category with result: " + category);
            return category;
        } catch (SQLException error) {
            error.printStackTrace();
            logger.error("Error with DAL method update category with error: " + error.getMessage());
            return null;
        }
    }

    @Override
    public int deleteCategory(int categoryId) {
        logger.info("Beginning DAL method delete category with data: " + categoryId);
        try (Connection connection = DatabaseConnection.createConnection()) {
            String sql = "DELETE FROM reimbursement_request_app.categories WHERE category_id=?;";
            assert connection != null;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, categoryId);
            int result = ps.executeUpdate();
            logger.info("Finishing DAL method delete category");
            return result;
        } catch (SQLException error) {
            error.printStackTrace();
            logger.error("Error with DAL method delete category with error: " + error.getMessage());
            return 0;
        }
    }
}
