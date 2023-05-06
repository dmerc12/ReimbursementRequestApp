import { AiOutlinePlus } from "react-icons/ai";

export default function AddCategoryButton() {
    return (
        <>
            <div className="add-category-component">
                <button className="add-category-btn">Add New Category <AiOutlinePlus className="plus-icon" size={15} /></button>
            </div>
        </>
    )
}