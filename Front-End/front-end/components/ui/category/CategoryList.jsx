export default function CategoryList({ categories }) {
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
                        {categories.map((category) => (
                            <tr key={category.categoryName}></tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </>
    )
}