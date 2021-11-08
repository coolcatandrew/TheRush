import React from "react";
import {Navbar, NavbarText, NavbarBrand, Collapse, Nav, NavItem, NavLink} from "reactstrap";

const Header = () => {
    return (
        <div>
            <Navbar color="light" expand="xs" light>
                <NavbarBrand href="#/">Results</NavbarBrand>
                <Collapse navbar>
                    <Nav className="me-auto" navbar>
                        <NavItem>
                            <NavLink href="#/upload">Upload</NavLink>
                        </NavItem>
                    </Nav>
                    <NavbarText>
                        The Rush
                    </NavbarText>
                </Collapse>
            </Navbar>
        </div>
    );
}

export default Header;