'use client'



export default function DeleteEmployeeInformation() {

    const onSubmit = async (event) => {
        event.preventDefault();
        
    }

    return (
        <>
            <h1>Confirm Deletion Below</h1>
            <p> Any requests or subsequent categories you are associated with will also be deleted. Are you sure you would like to delete your information?</p>
            
            <form className="form" onSubmit={onSubmit}>
                <button className="form-btn-2">Yes, Please Delete My Infomation</button>
            </form>
        </>
    )
}