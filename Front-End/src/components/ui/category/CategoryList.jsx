'use client'

export default async function CategoryList({ sessionId }) {
    const getAllCategories = async => {
        try {
            const categories = await fetch("/api/category/handleGetAll", {
                method: 'PATCH',
                headers: {'Content-Type': 'application/json'}, 
                body: JSON.stringify({
                    'sessionId': sessionId
                })
        } catch (error) {

        }
        
    })}
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
                        <tr>
                            {categories.map()}
                        </tr>
                    </tbody>
                </table>
            </div>
        </>
    )
}