export default function CategoryList({ categories }) {
    let categoryRows = [];
    if (categories.success) {
        for (let i=0; i<categories.success.length; i++) {
            const category = categories.success[i];
            console.log(category)
            categoryRows.unshift(
                <tr key={category.categoryId}>
                    <td className="category-table-data">{category.categoryName}</td>
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