package com.example.Reimbursement.Request.App.DAL.CategoryDAL;

import com.example.Reimbursement.Request.App.Entities.Category;
import com.example.Reimbursement.Request.App.Utilities.DatabaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

public class CategoryDALImplementation implements CategoryDALInterface {
    public static Logger logger = LogManager.getLogger(CategoryDALImplementation.class);

    @Override
    public Category addCategory(Category category) {
        logger.info("Beginning DAL function add category with data: /n" + category);
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
            logger.info("Finishing DAL function add category with result: /n" + category);
            return category;
        } catch (SQLException error) {
            error.printStackTrace();
            logger.error("Error with DAL function add category with error: " + error.getMessage());
            return null;
        }
    }

    @Override
    public List<Category> getAllCategories() {
        return null;
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
