package SAL.CategorySAL;

import DAL.CategoryDAL.CategoryDALImplementation;
import Entities.CustomExceptions.GeneralError;
import Entities.Data.Category;
import SAL.EmployeeSAL.EmployeeSALImplementation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CategorySALImplementation implements CategorySALInterface{
    private final CategoryDALImplementation categoryDAO;
    private final EmployeeSALImplementation employeeSAO;
    public static Logger logger = LogManager.getLogger(CategorySALImplementation.class);
    public CategorySALImplementation(CategoryDALImplementation companyDAO, EmployeeSALImplementation employeeSAO) {
        this.categoryDAO = companyDAO;
        this.employeeSAO = employeeSAO;
    }
    @Override
    public Category addCategory(Category category) {
        logger.info("Beginning SAL method add category with category ID: " + category.getCategoryId() +
                ", employee ID: " + category.getEmployeeId() + ", category name: " + category.getCategoryName());
        if (category.getCategoryName().equals("")) {
            logger.warn("SAL method add category, category name left empty");
            throw new GeneralError("Category name field cannot be left empty, please try again!");
        } else if (category.getCategoryName().length() > 60) {
            logger.warn("SAL method add category, category name too long");
            throw new GeneralError("Category name field cannot exceed 60 characters, please try again!");
        } else {
            employeeSAO.getEmployeeById(category.getEmployeeId());
            List<Category> categoryList = categoryDAO.getAllCategories(category.getEmployeeId());
            boolean exists = false;
            for (Category existingCategory: categoryList) {
                if (existingCategory.getCategoryName().equals(category.getCategoryName())) {
                    exists = true;
                    break;
                }
            } if (exists) {
                logger.warn("SAL method update category, category already exists");
                throw new GeneralError("Category with this name already exists, please try again!");
            } else {
                Category result = categoryDAO.addCategory(category);
                logger.info("Finishing SAL method add category with category ID: " + category.getCategoryId() +
                        ", category name: " + category.getCategoryName());
                return result;
            }
        }
    }

    @Override
    public List<Category> getAllCategories(int employeeId) {
        logger.info("Beginning SAL method get all categories with employee ID: " + employeeId);
        employeeSAO.getEmployeeById(employeeId);
        List<Category> categoryList = categoryDAO.getAllCategories(employeeId);
        if (categoryList.size() <= 1) {
            logger.warn("SAL method get all categories, category list empty");
            throw new GeneralError("No categories found, please try again!");
        } else {
            logger.info("Finishing SAL method get all categories");
            return categoryList;
        }
    }

    @Override
    public Category getCategory(int categoryId) {
        logger.info("Beginning SAL method get category with category ID: " + categoryId);
        Category result = categoryDAO.getCategoryById(categoryId);
        if (result == null) {
            logger.warn("SAL method get category, no category found");
            throw new GeneralError("Category not found, please try again!");
        } else {
            logger.info("Finishing SAL method get category with category ID: " + result.getCategoryId() +
                    ", employee ID: " + result.getEmployeeId() + ", category name: " + result.getCategoryName());
            return result;
        }
    }

    @Override
    public Category updateCategory(Category category) {
        logger.info("Beginning SAL method update category with category ID: " + category.getCategoryId() +
                ", employee ID: " + category.getEmployeeId() + ", category name: " + category.getCategoryName());
        if (category.getCategoryName().equals("")) {
            logger.warn("SAL method update category, category name left empty");
            throw new GeneralError("Category name field cannot be left empty, please try again!");
        } else if (category.getCategoryName().length() > 60) {
            logger.warn("SAL method update category, category name too long");
            throw new GeneralError("Category name field cannot exceed 60 characters, please try again!");
        } else {
            employeeSAO.getEmployeeById(category.getEmployeeId());
            List<Category> categoryList = categoryDAO.getAllCategories(category.getEmployeeId());
            boolean exists = false;
            Category existingCategoryInformation = getCategory(category.getCategoryId());
            if (category.getCategoryName().equals(existingCategoryInformation.getCategoryName())) {
                logger.warn("SAL method update category, nothing changed");
                throw new GeneralError("Nothing changed, please try again!");
            } for (Category existingCategory: categoryList) {
                if (existingCategory.getCategoryName().equals(category.getCategoryName())) {
                    exists = true;
                    break;
                }
            } if (exists) {
                logger.warn("SAL method update category, category already exists");
                throw new GeneralError("Category with this name already exists, please try again!");
            } else {
                Category result = categoryDAO.updateCategory(category);
                logger.info("Finishing SAL method update category with category ID: " + result.getCategoryId() +
                        ", category name: " + result.getCategoryName());
                return result;
            }
        }
    }

    @Override
    public int deleteCategory(int categoryId) {
        logger.info("Beginning SAL method delete category with category ID: " + categoryId);
        getCategory(categoryId);
        int result = categoryDAO.deleteCategory(categoryId);
        logger.info("Finishing SAL method delete category with result: " + result);
        return result;
    }
}
