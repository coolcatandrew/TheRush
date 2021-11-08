import React, {useState, useEffect} from "react";
import {Form, FormGroup, Input, Button, Container} from 'reactstrap';
import StatisticDisplay from "./statistic/StatisticDisplay";
import Error from "./Error";

const Upload = () => {
    const [file, setFile] = useState(null);
    const [statistics, setStatistics] = useState([]);
    const [loading, setLoading] = useState(false);
    const [showSubmit, setShowSubmit] = useState(false);
    const [error, setError] = useState('');

    useEffect(() => {
        setShowSubmit(file && file.type === 'application/json');
    }, [file]);

    const onFileSubmit = async (e) => {
        setLoading(true);
        e.preventDefault();

        const formData = new FormData();
        formData.append("file", file);

        const requestOptions = {
            method: 'POST',
            body: formData
        };

        const response = await fetch('/rushing/upload', requestOptions);
        if (response.status === 200) {
            setStatistics(await response.json());
            setError('');
        } else {
            setError((response.statusText ? response.statusText : 'Error') + " Uploading file.");
        }
        setFile(null);
        setLoading(false);
    };

    return (
        <div>
            <Container fluid="sm">
                <Form onSubmit={(e) => onFileSubmit(e)}>
                    <FormGroup className="input-group">
                        <Input type="file" accept="application/JSON" name="file"
                               onChange={(e) => setFile(e.target.files[0])}/>
                        {showSubmit ? <Button color="primary" type="submit">Upload</Button> : null}
                    </FormGroup>
                </Form>
            </Container>
            {error ? <Error message={error}/> : <StatisticDisplay statistics={statistics} loading={loading}/>}
        </div>
    );

}

export default Upload;
