import React from "react";
import {Container, Row} from "reactstrap";

const Error = ({message}) => {
    return (
        <div>
            <Container className='p-3 h-100'>
                <Row className="text-center">
                    <h1 className="text-center">MY BAD!</h1>
                    <div>
                        <h2>
                            Oops, there has been an error
                        </h2>
                        {message}
                    </div>
                </Row>
            </Container>
        </div>
    );
}

export default Error;