import React from "react";

const Header = ({toggleModal, numberOfBooks}) => {
    const setToggleToTrue = () => {
        toggleModal(true)
    }
    return (
        <header className="header">
            <div className="container">
                <h3>Books list ({numberOfBooks})</h3>
                <button onClick={setToggleToTrue}>Add new book</button>
            </div>
        </header>
    );
}

export default Header;