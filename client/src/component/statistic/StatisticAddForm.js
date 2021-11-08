import React, {useState} from "react";
import {Form, Button, Row, Col} from "reactstrap";
import "bootstrap/dist/css/bootstrap.min.css";

const StatisticAddForm = ({onPlayerAdd}) => {
    const [name, setName] = useState('');
    const [team, setTeam] = useState('');
    const [position, setPosition] = useState('');
    const [attempts, setAttempts] = useState('');
    const [averageAttempts, setAverageAttempts] = useState('');
    const [yards, setYards] = useState('');
    const [averageYards, setAverageYards] = useState('');
    const [yardsPerGame, setYardsPerGame] = useState('');
    const [touchdowns, setTouchdowns] = useState('');
    const [longest, setLongest] = useState('');
    const [firstDowns, setFirstDowns] = useState('');
    const [firstDownPercentage, setFirstDownPercentage] = useState('');
    const [twentyPlusYards, setTwentyPlusYards] = useState('');
    const [fortyPlusYards, setFortyPlusYards] = useState('');
    const [fumbles, setFumbles] = useState('');

    return (
        <div>
            <Form onSubmit={(e) => onPlayerAdd(e)}>
                <Row>
                    <Col xs={6}>
                        <input maxLength='100' name='name' className='m-1' type='text' placeholder='Name' value={name} style={{width:'83%'}}
                               required='required'
                               onChange={(e) => setName(e.target.value)}/>
                    </Col>
                    <Col xs={3}>
                        <input maxLength='3' name='team' className='m-1' type='text' placeholder='Team' value={team}
                               onChange={(e) => setTeam(e.target.value)}/>
                    </Col>
                    <Col xs={3}>
                        <input maxLength='2' name='position' className='m-1' type='text' placeholder='Position'
                               value={position}
                               onChange={(e) => setPosition(e.target.value)}/>
                    </Col>
                </Row>
                <Row>
                    <Col xs={3}>
                        <input name='attempts' className='m-1' type='number' placeholder='Attempts' value={attempts}
                               onChange={(e) => setAttempts(e.target.value)}/>
                    </Col>
                    <Col xs={3}>
                        <input name='averageAttempts' className='m-1' type='number' placeholder='Average Attempts'
                               value={averageAttempts}
                               onChange={(e) => setAverageAttempts(e.target.value)}/>
                    </Col>
                    <Col xs={3}>
                        <input name='yards' className='m-1' type='number' placeholder='yards' value={yards}
                               onChange={(e) => setYards(e.target.value)}/>
                    </Col>
                    <Col xs={3}>
                        <input name='averageYards' className='m-1' type='number' placeholder='Average Yards'
                               value={averageYards}
                               onChange={(e) => setAverageYards(e.target.value)}/>
                    </Col>
                </Row>
                <Row>
                    <Col xs={3}>
                        <input name='yardsPerGame' className='m-1' type='number' placeholder='Yards Per Game'
                               value={yardsPerGame}
                               onChange={(e) => setYardsPerGame(e.target.value)}/>
                    </Col>

                    <Col xs={3}>
                        <input name='touchdowns' className='m-1' type='number' placeholder='Touchdowns'
                               value={touchdowns}
                               onChange={(e) => setTouchdowns(e.target.value)}/>
                    </Col>
                    <Col xs={3}>
                        <input maxLength='5' name='longest' className='m-1' type='text' placeholder='Longest'
                               value={longest}
                               onChange={(e) => setLongest(e.target.value)}/>
                    </Col>
                    <Col xs={3}>
                        <input name='firstDowns' className='m-1' type='number' placeholder='First Downs'
                               value={firstDowns}
                               onChange={(e) => setFirstDowns(e.target.value)}/>
                    </Col>
                </Row>
                <Row>
                    <Col xs={3}>
                        <input name='firstDownPercentage' className='m-1' type='number'
                               placeholder='First Down Percentage'
                               value={firstDownPercentage}
                               onChange={(e) => setFirstDownPercentage(e.target.value)}/>
                    </Col>
                    <Col xs={3}>
                        <input name='twentyPlusYards' className='m-1' type='number' placeholder='Twenty Plus Yards'
                               value={twentyPlusYards}
                               onChange={(e) => setTwentyPlusYards(e.target.value)}/>
                    </Col>
                    <Col xs={3}>
                        <input name='fortyPlusYards' className='m-1' type='number' placeholder='Forty Plus Yards'
                               value={fortyPlusYards}
                               onChange={(e) => setFortyPlusYards(e.target.value)}/>
                    </Col>
                    <Col xs={3}>
                        <input name='fumbles' className='m-1' type='number' placeholder='Fumbles' value={fumbles}
                               onChange={(e) => setFumbles(e.target.value)}/>
                    </Col>
                </Row>
                <Row>
                    <Button color='primary' type='submit'>Save</Button>
                </Row>
            </Form>
        </div>
    );

}

export default StatisticAddForm;