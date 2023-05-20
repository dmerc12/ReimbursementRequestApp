import UpdateCategoryComponent from './UpdateCategoryComponent';
import DeleteCategoryComponent from './DeleteCategoryComponent';

export default function CategoryList({ categories }) {
    let categoryRows = [];
    if (categories.success) {
        for (let i=0; i<categories.success.length; i++) {
            const category = categories.success[i];
            console.log(category)
            categoryRows.unshift(
                <tr key={category.categoryId}>
                    <td className="category-table-data w-full">{category.categoryName}</td>
                    <td className="category-table-data flex gap-5">
                        <UpdateCategoryComponent />
                        <DeleteCategoryComponent />
                    </td>
                </tr>
            )
            console.log(categoryRows)
        }
    }

    return (
        <>
            <div className="category-list overflow-x-auto">
                <table className="table w-full">
                    <thead>
                        <tr>
                            <th  className="category-table-head">Category Name</th>
                            <th className="category-table-head">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {categoryRows}
                    </tbody>
                </table>
            </div>
        </>
    )
}