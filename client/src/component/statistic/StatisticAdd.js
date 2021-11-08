import React, {useEffect, useState} from "react";
import {Button, Container, Row, Col, Spinner} from "reactstrap";
import 'bootstrap/dist/css/bootstrap.min.css';
import StatisticAddForm from "./StatisticAddForm";

const StatisticAdd = ({onPlayerAdd, loading, error}) => {
    const [showAdd, setShowAdd] = useState(false);

    useEffect(() => {
        if (!loading) {
            setShowAdd(error);
        }
    }, [loading]);

    return (
        <Container className='py-2'>
            <Container>
                <Row>
                    <Col xs={{span: 1, offset: 11}}>
                        <Button
                            className='my-3 float-right'
                            color='success'
                            onClick={() => setShowAdd(!showAdd)}
                        >Add</Button>
                    </Col>
                </Row>
            </Container>
                {showAdd && <StatisticAddForm onPlayerAdd={onPlayerAdd}/>}
                {loading && <Container fluid className="d-flex justify-content-center p-3">
                    <Spinner> Loading...</Spinner> Loading...
                </Container>}
                <Container fluid className="d-flex justify-content-center p-3">
                    <p className='text-danger'>{error}</p>
                </Container>

        </Container>
    );

}

export default StatisticAdd;